/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.Socket;


/**
 *
 * @author paul
 */
public class HttpTransaction {
   
   public static final String COLON  = ":";
   public static final String EOL_NL = "\n";

   private final ArrayList<String> m_vecHeaderLines = new ArrayList<>();
   private final ArrayList<String> m_vecRequestLineValues = new ArrayList<>();
   private String m_header;
   private String m_body;
   private String m_protocol;
   private String m_requestLine;
   private final HashMap<String,String> m_hashHeaders = new HashMap<>();
   private String m_method;
   private int m_contentLength = 0;

   
   /**
    * Default constructor
    */
   public HttpTransaction() {
   }
   
   /**
    * Initializes object by reading from socket
    * @param socket the socket to read the HTTP request or response
    * @throws com.swampbits.misere.HttpException
    * @see Socket()
    * @return boolean indicating whether the initialization from socket succeeded
    */
   public boolean streamFromSocket(Socket socket) throws HttpException {
      boolean isLoggingDebug = Logger.isLogging(Logger.LogLevel.Debug);
   
      boolean streamSuccess = false;
      boolean done = false;
      boolean inHeader = true;
      boolean headersParsed = false;

      StringBuilder sbInput = new StringBuilder();
      String inputLine;
      String method;
      int contentLength = -1;  // unknown

      while (!done) {
         if ((contentLength > -1) && (sbInput.length() >= contentLength)) {
            m_body = sbInput.toString();
            done = true;
            continue;
         }

         if (inHeader) {
            inputLine = socket.readLine();
            if (null != inputLine) {
               if (!inputLine.isEmpty()) {
                  sbInput.append(inputLine);
                  sbInput.append("\n");

                  if (inHeader) {
                     m_vecHeaderLines.add(inputLine);
                  }
               } else {
                  if (inHeader) {
                     inHeader = false;
                     m_header = sbInput.toString();
                     sbInput.setLength(0);
                     streamSuccess = parseHeaders();
                     headersParsed = true;
                  } else {
                     m_body = sbInput.toString();
                  }

                  if (contentLength < 1) {
                     done = true;
                  }
               }
            } else {
               done = true;
               m_body = sbInput.toString();
            }
         } else {
            // not in header anymore
            if (contentLength > 0) {
               if (isLoggingDebug) {
                  Logger.debug("contentLength=" + contentLength);
               }

               char[] buffer = new char[contentLength+1];

               try {
                  if (socket.readSocket(buffer, contentLength)) {
                     buffer[contentLength] = '\0';
                     String content = String.copyValueOf(buffer);
                  
                     if (isLoggingDebug) {
                        Logger.debug("HttpTransaction (body) socket.read: " + content);
                     }
                  
                     m_body += content;
                  }
               } catch(Exception e) {
                  Logger.error("HTTPTransaction.streamFromSocket exception caught: " + e.getMessage());
               } catch (Throwable t) {
                  Logger.error("HTTPTransaction.streamFromSocket unknown exception caught");
               }

               done = true;
            } else {
               // I don't think that this code would ever be called
               if (isLoggingDebug) {
                  Logger.debug("HttpTransaction: no content-length present, calling readLine in loop");
               }

               done = false;

               while (!done) {
                  inputLine = socket.readLine();
                  if (null != inputLine) {
                     m_body += inputLine;
                  } else {
                     done = true;
                  }
               }
            }
         }
      }
   
      if ((m_method == null) || !m_method.isEmpty() || !headersParsed) {
         streamSuccess = parseHeaders();
      }
   
      return streamSuccess;      
   }

   /**
    * Retrieves the full set of HTTP headers as a single string
    * @return HTTP headers (unparsed)
    */
   public String getRawHeader() {
      return m_header;
   }
   
   /**
    * Retrieves the body (content) associated with the request or response
    * @return the body as text (empty string if none was set)
    */
   public String getBody() {
      return m_body;
   }
   
   /**
    * Sets the body (content) associated with the request or response
    * @param body the content for the request or response
    */
   public void setBody(String body) {
      m_body = body;
   }
   
   /**
    * Determines if the specified header key exists
    * @param headerKey the key being tested for existence in HTTP headers
    * @return boolean indicating if the specified key exists
    */
   public boolean hasHeaderValue(String headerKey) {
      return m_hashHeaders.containsKey(headerKey.toLowerCase());
   }
   
   /**
    * Retrieves the header value associated with the specified key
    * @param headerKey the HTTP header key whose value is being retrieved
    * @throw InvalidKeyException
    * @return the header value associated with the specified key
    */
   public String getHeaderValue(String headerKey) {
      return m_hashHeaders.get(headerKey.toLowerCase());
   }
   
   /**
    * Retrieves the keys of all the HTTP header key/value pairs
    * @param headerKeys list that will be populated with HTTP header keys
    */
   public void getHeaderKeys(List<String> headerKeys) {
      headerKeys.addAll(m_hashHeaders.keySet());
   }
   
   /**
    * Sets HTTP header key/value pair
    * @param key the key of the HTTP header being set
    * @param value the value of the HTTP header being set
    */
   public void setHeaderValue(String key, String value) {
      m_hashHeaders.put(key.toLowerCase(), value);
   }
   
   /**
    * Retrieves the protocol (e.g., "HTTP/1.1") of the request
    * @return the protocol
    */
   public String getProtocol() {
      return m_protocol;
   }
   
   /**
    * Retrieves the HTTP method (e.g., "GET" or "POST")
    * @return the HTTP method for the request
    */
   public String getRequestMethod() {
      return m_method;
   }
   
   /**
    * Retrieves the path for the HTTP request
    * @return the HTTP request path
    */
   public String getRequestPath() {
      return m_vecRequestLineValues.get(1);
   }
   
   /**
    * Returns the first line (request line) of the HTTP request or response
    * @return the request line
    */
   public String getRequestLine() {
      return m_requestLine;
   }
   
   /**
    * Retrieves the HTTP header key/value pairs
    * @param hashTable the map to populate with HTTP header key/values
    */
   public void populateWithHeaders(Map<String, String> hashTable) {
      for (String headerKey : m_hashHeaders.keySet()) {
         hashTable.put(headerKey, m_hashHeaders.get(headerKey));
      }
   }
   
   /**
    * Retrieves the parsed values/tokens of the request line (the first line)
    * @return list of parsed tokens on request line
    */
   protected List<String> getRequestLineValues() {
      return m_vecRequestLineValues;
   }
   
   /**
    * Sets the protocol (e.g., "HTTP/1.1")
    * @param protocol the protocol being used
    */
   protected void setProtocol(String protocol) {
      m_protocol = protocol;
   }
   
   /**
    * Parse the HTTP headers
    * @return boolean indicating whether the headers were successfully parsed
    */
   protected boolean parseHeaders() {
      boolean parseSuccess = false;

      if (m_vecHeaderLines.isEmpty()) {
         return false;
      }
   
      m_requestLine = m_vecHeaderLines.get(0);
      StringTokenizer st = new StringTokenizer(m_requestLine);
      int tokenCount = st.countTokens();
   
      if (3 <= tokenCount) {
         m_vecRequestLineValues.clear();
         m_vecRequestLineValues.ensureCapacity(3);
         String thirdValue = "";
      
         for (int i = 0; i < tokenCount; ++i) {
            if (i > 1) {
               thirdValue += st.nextToken();
            } else {
               m_vecRequestLineValues.add(st.nextToken());
            }
         }
      
         m_vecRequestLineValues.add(thirdValue);
         int numHeaderLines = m_vecHeaderLines.size();
         m_method = m_vecRequestLineValues.get(0);
         m_protocol = m_vecRequestLineValues.get(2);
      
         for (int i = 1; i < numHeaderLines; ++i) {
            String headerLine = m_vecHeaderLines.get(i);
            int posColon = headerLine.indexOf(COLON);
         
            if (posColon > -1) {
               String lowerHeaderKey =
                       headerLine.substring(0, posColon).toLowerCase();
               String value = headerLine.substring(posColon + 1).trim();
               m_hashHeaders.put(lowerHeaderKey, value);
            }
         }
      
         if (hasHeaderValue(HTTP.HTTP_CONTENT_LENGTH)) {
            String contentLengthAsString =
               getHeaderValue(HTTP.HTTP_CONTENT_LENGTH);
         
            if (!contentLengthAsString.isEmpty()) {
               int length = Integer.parseInt(contentLengthAsString);            
               if (length > 0) {
                  m_contentLength = length;
               }
            }
         }
      
         parseSuccess = true;
      }
   
      return parseSuccess;      
   }

}
