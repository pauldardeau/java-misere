/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import java.util.List;

import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.Socket;


/**
 *
 * @author paul
 */
public class HttpResponse extends HttpTransaction {
   
   private String m_statusCode;
   private String m_reasonPhrase;
   private int m_statusCodeAsInteger;

   
   /**
    * Default constructor
    */
   HttpResponse() {
      m_statusCodeAsInteger = 200;
      setContentType("text/html");
   }
   
   /**
    * Constructs and HttpResponse by reading from socket
    * @param socket the socket to read from
    * @see Socket()
    * @throws HttpException
    * @throws Exception
    */
   public HttpResponse(Socket socket) throws Exception {  
      if (!streamFromSocket(socket)) {
         throw new Exception("unable to construct HttpResponse from Socket");
      }
   }
   
   /**
    * Initializes HttpResponse instance by reading data from socket
    * @param socket the socket to read from for initialization
    * @return boolean indicating whether initialization succeeded
    * @throws com.swampbits.misere.HttpException
    */
   @Override
   public boolean streamFromSocket(Socket socket) throws HttpException {
      if (Logger.isLogging(Logger.LogLevel.Debug)) {
         Logger.debug("******** start of HttpResponse::streamFromSocket");
      }
   
      boolean streamSuccess = false;

      if (super.streamFromSocket(socket)) {
         List<String> vecRequestLineValues =
            getRequestLineValues();

         if (3 == vecRequestLineValues.size()) {
            setProtocol(vecRequestLineValues.get(0));
            m_statusCode = vecRequestLineValues.get(1);
            m_reasonPhrase = vecRequestLineValues.get(2);
            m_statusCodeAsInteger = Integer.parseInt(m_statusCode);

            if (0 == m_statusCodeAsInteger) {
               Logger.error("unable to parse status code");
               return false;
            } else if (m_statusCodeAsInteger >= 500) {
               String reasonPhrase = "";

               switch (m_statusCodeAsInteger) {
                  case 500:
                     reasonPhrase = "Internal Server Error";
                     break;

                  case 501:
                     reasonPhrase = "Not Implemented";
                     break;

                  case 502:
                     reasonPhrase = "Bad Gateway";
                     break;

                  case 503:
                     reasonPhrase = "Service Unavailable";
                     break;

                  case 504:
                     reasonPhrase = "Gateway Timeout";
                     break;

                  case 505:
                     reasonPhrase = "HTTP Version not supported";
                     break;
               }

               throw new HttpException(m_statusCodeAsInteger, reasonPhrase);

            } else if ((m_statusCodeAsInteger >= 400) &&
                       (m_statusCodeAsInteger < 500)) {
               String reasonPhrase = "";

               switch(m_statusCodeAsInteger) {
                  case 400:
                     reasonPhrase = "Bad Request";
                     break;

                  case 401:
                     reasonPhrase = "Unauthorized";
                     break;

                  case 402:
                     reasonPhrase = "Payment Required";
                     break;

                  case 403:
                     reasonPhrase = "Forbidden";
                     break;

                  case 404:
                     reasonPhrase = "Not Found";
                     break;

                  case 405:
                     reasonPhrase = "Method Not Allowed";
                     break;

                  case 406:
                     reasonPhrase = "Not Acceptable";
                     break;

                  case 407:
                     reasonPhrase = "Proxy Authentication Required";
                     break;

                  case 408:
                     reasonPhrase = "Request Timeout";
                     break;

                  case 409:
                     reasonPhrase = "Conflict";
                     break;

                  case 410:
                     reasonPhrase = "Gone";
                     break;

                  case 411:
                     reasonPhrase = "Length Required";
                     break;

                  case 412:
                     reasonPhrase = "Precondition Failed";
                     break;

                  case 413:
                     reasonPhrase = "Request Entity Too Large";
                     break;

                  case 414:
                     reasonPhrase = "Request-URI Too Large";
                     break;

                  case 415:
                     reasonPhrase = "Unsupported Media Type";
                     break;

                  case 416:
                     reasonPhrase = "Request range not satisfiable";
                     break;

                  case 417:
                     reasonPhrase = "Expectation Failed";
                     break;
               }

               throw new HttpException(m_statusCodeAsInteger, reasonPhrase);
            }
         }
      
         streamSuccess = true;
      } else {
         Logger.error("unable to parse headers");
      }
   
      return streamSuccess;      
   }

   /**
    * Retrieves the HTTP status code
    * @return HTTP status code value
    */
   public int getStatusCode() {
      return m_statusCodeAsInteger;
   }
   
   /**
    * Sets the HTTP status code
    * @param statusCode HTTP status code value
    */
   public void setStatusCode(int statusCode) {
      m_statusCodeAsInteger = statusCode;
   }
   
   /**
    * Retrieves the textual description of the HTTP status
    * @return textual description of HTTP status
    */
   public String getReasonPhrase() {
      return m_reasonPhrase;
   }
   
   /**
    * Determines if Content-encoding header field value exists
    * @return boolean indicating if header field exists
    */
   public boolean hasContentEncoding() {
      return hasHeaderValue(HTTP.HTTP_CONTENT_ENCODING);
   }
   
   /**
    * Determines if Content-type header field value exists
    * @return boolean indicating if header field exists
    */
   public boolean hasContentType() {
      return hasHeaderValue(HTTP.HTTP_CONTENT_TYPE);
   }
   
   /**
    * Retrieves the value for the Content-encoding HTTP header field
    * @return the value for the Content-encoding field
    */
   public String getContentEncoding() {
      return getHeaderValue(HTTP.HTTP_CONTENT_ENCODING);
   }
   
   /**
    * Retrieves the value for the Content-type HTTP header field
    * @return the value for the Content-type field
    */
   public String getContentType() {
      return getHeaderValue(HTTP.HTTP_CONTENT_TYPE);
   }
   
   /**
    * Sets the Content-encoding HTTP header field
    * @param contentEncoding the value to use for Content-encoding
    */
   public void setContentEncoding(String contentEncoding) {
      setHeaderValue(HTTP.HTTP_CONTENT_ENCODING, contentEncoding);
   }
   
   /**
    * Sets the Content-type HTTP header field
    * @param contentType the value to use for Content-type
    */
   public void setContentType(String contentType) {
      setHeaderValue(HTTP.HTTP_CONTENT_TYPE, contentType);
   }
   
}
