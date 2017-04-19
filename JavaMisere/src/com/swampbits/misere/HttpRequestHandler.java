/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import java.util.HashMap;

import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.RequestHandler;
import com.swampbits.chaudiere.Socket;
import com.swampbits.chaudiere.SocketRequest;

/**
 *
 * @author paul
 */
public class HttpRequestHandler extends RequestHandler implements Runnable {
   
   public static final String HTTP_ACCEPT_ENCODING = "accept-encoding";
   public static final String HTTP_CONNECTION      = "Connection:";
   public static final String HTTP_CONTENT_LENGTH  = "content-length:";
   public static final String HTTP_CONTENT_TYPE    = "content-type:";
   public static final String HTTP_DATE            = "date:";
   public static final String HTTP_SERVER          = "server:";
   public static final String HTTP_USER_AGENT      = "User-Agent";

   public static final String CONNECTION_CLOSE     = "close";

   public static final String ZERO                 = "0";
   public static final String FAVICON_ICO          = "/favicon.ico";

   public static final String CONTENT_TYPE_HTML    = "text/html";

   public static final String COUNT_PATH           = "path";
   public static final String COUNT_USER_AGENT     = "user_agent";

   public static final String QUESTION_MARK        = "?";

   public static final String GZIP                 = "gzip";


   private final HttpServer m_server;
   
   /**
    * Constructs a HttpRequestHandler using a SocketRequest for use by a KernelEventServer
    * @param server the HttpServer that is being run
    * @param socketRequest the SocketRequest for processing using KernelEventServer
    * @see HttpServer()
    * @see SocketRequest()
    */
   public HttpRequestHandler(HttpServer server, SocketRequest socketRequest) {
      super(socketRequest);
      m_server = server;
   }
   
   /**
    * Constructs a HttpRequestHandler using a Socket
    * @param server the HttpServer that is being run
    * @param socket the Socket for handling the request
    * @see HttpServer()
    * @see Socket()
    */
   public HttpRequestHandler(HttpServer server, Socket socket) {
      super(socket);
      m_server = server;
   }
   
   /**
    * Process the HTTP request from beginning to end
    */
   @Override
   public void run() {
      Socket socket = getSocket();
   
      if (null == socket) {
         Logger.error("no socket or socket request present in RequestHandler");
         return;
      }
   
      socket.setTcpNoDelay(true);
      socket.setSendBufferSize(m_server.getSocketSendBufferSize());
      socket.setReceiveBufferSize(m_server.getSocketReceiveBufferSize());

      boolean isLoggingDebug = Logger.isLogging(Logger.LogLevel.Debug);
      if (isLoggingDebug) {
         Logger.debug("starting parse of HttpRequest");
      }
   
      HttpRequest request;
      try {
         request = new HttpRequest(socket);
      } catch (HttpException he) {
         //TODO: log
         System.out.println("Exception caught constructing HttpRequest");
         he.printStackTrace(System.err);
         request = null;
      } catch (Throwable t) {
         //TODO: log
         System.out.println("Exception caught constructing HttpRequest");
         t.printStackTrace(System.err);
         request = null;
      }

      if ((request != null) && request.isInitialized()) {
         if (isLoggingDebug) {
            Logger.debug("ending parse of HttpRequest");
         }
   
         String method = request.getMethod();
         String protocol = request.getProtocol();
         String path = request.getPath();
         String routingPath = path;
         String clientIPAddress = socket.getPeerIPAddress();
      
         if (path.contains(QUESTION_MARK)) {
            // strip arguments from path
            int posQuestionMark = path.indexOf(QUESTION_MARK);
            if (posQuestionMark > -1) {
               routingPath = path.substring(0, posQuestionMark);
            }
         }

         Logger.countOccurrence(COUNT_PATH, routingPath);
      
         if (request.hasHeaderValue(HTTP_USER_AGENT)) {
            Logger.countOccurrence(COUNT_USER_AGENT,
                                    request.getHeaderValue(HTTP_USER_AGENT));
         }
   
         HttpHandler handler = m_server.getPathHandler(routingPath);
         boolean handlerAvailable = false;
   
         if (handler == null) {
            Logger.info("no handler for request: " + routingPath);
         }
   
         // assume the worst
         String responseCode = HTTP.HTTP_RESP_SERV_ERR_INTERNAL_ERROR;
         String systemDate = m_server.getSystemDateGMT();
         HashMap<String, String> mapHeaders = new HashMap<>();
         mapHeaders.put(HTTP_CONNECTION, CONNECTION_CLOSE);
         String serverString = m_server.getServerId();
   
         if (!serverString.isEmpty()) {
            mapHeaders.put(HTTP_SERVER, serverString);
         }
   
         mapHeaders.put(HTTP_DATE, systemDate);
         //mapHeaders[HTTP_CONTENT_TYPE] = CONTENT_TYPE_HTML;
   
         if (!protocol.equals(HTTP.HTTP_PROTOCOL1_0) &&
             !protocol.equals(HTTP.HTTP_PROTOCOL1_1)) {
            responseCode = HTTP.HTTP_RESP_SERV_ERR_HTTP_VERSION_UNSUPPORTED;
            Logger.warning("unsupported protocol: " + protocol);
         } else if (null == handler) { // path recognized?
            responseCode = HTTP.HTTP_RESP_CLIENT_ERR_NOT_FOUND;
            //Logger::warning("bad request: " + path);
         } else if (!handler.isAvailable()) { // is our handler available?
            responseCode = HTTP.HTTP_RESP_SERV_ERR_SERVICE_UNAVAILABLE;
            Logger.warning("handler not available: " + routingPath);
         } else {
            handlerAvailable = true;
         }
   
         String httpHeader = request.getRawHeader();
         String httpBody = request.getBody();
   
         if (isLoggingDebug) {
            Logger.debug("HttpServer method: " + method);
            Logger.debug("HttpServer path: " + routingPath);
            Logger.debug("HttpServer protocol: " + protocol);
      
            Logger.debug("HttpServer header:");
            Logger.debug(httpHeader);
      
            Logger.debug("HttpServer body:");
            Logger.debug(httpBody);
         }
   
         int contentLength = 0;
         HttpResponse response = new HttpResponse();
   
         if ((null != handler) && handlerAvailable) {
            try {
               handler.serviceRequest(request, response);
               responseCode = "" + response.getStatusCode();
               String responseBody = response.getBody();
               contentLength = responseBody.length();

               /*
               //TODO: add support for compressed (gzipped) response
               if ((contentLength > 0) && !response.hasContentEncoding()) {
                  if (request.hasAcceptEncoding()) {
                     String acceptEncoding = request.getAcceptEncoding();
                  
                     if (acceptEncoding.contains(GZIP) &&
                         m_server.compressionEnabled() &&
                         m_server.compressResponse(response.getContentType()) &&
                         contentLength >= m_server.minimumCompressionSize()) {
                     
                        try {
                           String compressedResponseBody =
                              StrUtils.gzipCompress(responseBody);
                           contentLength = compressedResponseBody.size();
                           response.setBody(compressedResponseBody);
                           response.setContentEncoding(GZIP);
                        } catch (Throwable t) {
                           Logger.error("unable to compress response");
                        }
                     }
                  }
               }
               */
         
               response.populateWithHeaders(mapHeaders);
            } catch (Exception e) {
               responseCode = HTTP.HTTP_RESP_SERV_ERR_INTERNAL_ERROR;
               e.printStackTrace();
               Logger.error("exception handling request: " + e.getMessage());
            } catch (Throwable t) {
               responseCode = HTTP.HTTP_RESP_SERV_ERR_INTERNAL_ERROR;
               t.printStackTrace();
               Logger.error("unknown exception handling request");
            }
         }

         if (contentLength > 0) {
            mapHeaders.put(HTTP_CONTENT_LENGTH, "" + contentLength);
         } else {
            mapHeaders.put(HTTP_CONTENT_LENGTH, ZERO);
         }
   
         // log the request
         if (isThreadPooling()) {
            String runByWorkerThreadId = getRunByThreadWorkerId();
      
            if (!runByWorkerThreadId.isEmpty()) {
               m_server.logRequest(clientIPAddress,
                                   request.getRequestLine(),
                                   responseCode,
                                   runByWorkerThreadId);
            } else {
               m_server.logRequest(clientIPAddress,
                                   request.getRequestLine(),
                                   responseCode);
            }
         } else {
            m_server.logRequest(clientIPAddress,
                                request.getRequestLine(),
                                responseCode);
         }
   
         String responseAsString =
            m_server.buildHeader(responseCode, mapHeaders);
   
         if (contentLength > 0) {
            responseAsString += response.getBody();
         }

         socket.write(responseAsString);
   
         /*
         if (isLoggingDebug) {
            Logger::debug("response written, calling read so that client can close first");
         }
   
         // invoke a read to give the client the chance to close the socket
         // first. this also lets us easily detect the close on the client
         // end of the connection.  we won't actually read any data here,
         // this is just a wait to allow the client to close first
         char readBuffer[5];
         socket->read(readBuffer, 4);
   
         //if (m_socketRequest != nullptr) {
         //   m_socketRequest->requestComplete();
         //}
         */
      } else {
         System.out.println("error: unable to initialize HttpRequest");
      }      
   }
}
