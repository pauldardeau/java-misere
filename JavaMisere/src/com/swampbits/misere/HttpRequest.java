/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import java.util.List;
import java.util.StringTokenizer;

import com.swampbits.chaudiere.KeyValuePairs;
import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.Socket;


/**
 *
 * @author paul
 */
public class HttpRequest extends HttpTransaction {
   
   public static final String ENC_DOUBLE_QUOTE  = "%22";   // "
   public static final String ENC_SINGLE_QUOTE  = "%27";   // '
   public static final String ENC_AMPERSAND     = "%26";   // &
   public static final String ENC_PERCENT       = "%25";   // %
   public static final String ENC_ATSIGN        = "%40";   // @
   public static final String ENC_DOLLAR        = "%24";   // $
   public static final String ENC_POUND         = "%23";   // #
   public static final String ENC_EXCLAMATION   = "%21";   // !
   public static final String ENC_TILDE         = "%7E";   // ~
   public static final String ENC_CARET         = "%5E";   // ^
   public static final String ENC_OPEN_PAREN    = "%28";   // (
   public static final String ENC_CLOSE_PAREN   = "%29";   // )
   public static final String ENC_PLUS          = "%2B";   // +
   public static final String ENC_BACKTICK      = "%60";   // `
   public static final String ENC_EQUAL         = "%3D";   // =
   public static final String ENC_OPEN_BRACE    = "%7B";   // {
   public static final String ENC_CLOSE_BRACE   = "%7D";   // }
   public static final String ENC_VERT_BAR      = "%7C";   // |
   public static final String ENC_OPEN_BRACKET  = "%5B";   // [
   public static final String ENC_CLOSE_BRACKET = "%5D";   // ]
   public static final String ENC_BACKSLASH     = "%5C";   //
   public static final String ENC_COLON         = "%3A";   // :
   public static final String ENC_SEMICOLON     = "%3B";   // ;
   public static final String ENC_LESS_THAN     = "%3C";   // <
   public static final String ENC_GREATER_THAN  = "%3E";   // >
   public static final String ENC_QUESTION      = "%3F";   // ?
   public static final String ENC_COMMA         = "%2C";   // ,
   public static final String ENC_SLASH         = "%2F";   // /

   public static final String DOUBLE_QUOTE  = "\"";
   public static final String SINGLE_QUOTE  = "'";
   public static final String AMPERSAND     = "&";
   public static final String PERCENT       = "%";
   public static final String ATSIGN        = "@";
   public static final String DOLLAR        = "$";
   public static final String POUND         = "#";
   public static final String EXCLAMATION   = "!";
   public static final String TILDE         = "~";
   public static final String CARET         = "^";
   public static final String OPEN_PAREN    = "(";
   public static final String CLOSE_PAREN   = ")";
   public static final String PLUS          = "+";
   public static final String BACKTICK      = "`";
   public static final String EQUAL         = "=";   // =
   public static final String OPEN_BRACE    = "{";   // {
   public static final String CLOSE_BRACE   = "}";   // }
   public static final String VERT_BAR      = "|";   // |
   public static final String OPEN_BRACKET  = "[";   // [
   public static final String CLOSE_BRACKET = "]";   // ]
   public static final String BACKSLASH     = "\\";   //
   public static final String COLON         = ":";   // :
   public static final String SEMICOLON     = ";";   // ;
   public static final String LESS_THAN     = "<";   // <
   public static final String GREATER_THAN  = ">";   // >
   public static final String QUESTION      = "?";   // ?
   public static final String COMMA         = ",";   // ,
   public static final String SLASH         = "/";   // /
   public static final String SPACE         = " ";

   private String m_method;
   private String m_path;
   private KeyValuePairs m_arguments;
   private boolean m_initialized = false; 

   
   /**
    * Constructs and initializes an HttpRequest object from a socket
    * @param socket the socket to read for initializing the object
    * @throws com.swampbits.misere.HttpException
    * @see Socket()
    */
   public HttpRequest(Socket socket) throws HttpException {
      //Logger.logInstanceCreate("HttpRequest");
      m_initialized = streamFromSocket(socket);
   }
   
   /**
    * Initializes HTTP request by reading from socket
    * @param socket the socket to read from
    * @throws com.swampbits.misere.HttpException
    * @see Socket()
    * @return boolean indicating whether reading from the socket succeeded
    */
   @Override
   public boolean streamFromSocket(Socket socket) throws HttpException {
      boolean isLoggingDebug = Logger.isLogging(Logger.LogLevel.Debug);
   
      if (isLoggingDebug) {
         Logger.debug("==== start of HttpRequest.streamFromSocket");
      }
   
      boolean streamSuccess = false;
   
      if (super.streamFromSocket(socket)) {
         if (isLoggingDebug) {
            Logger.debug("calling getRequestLineValues");
         }
      
         List<String> vecRequestLineValues =
            getRequestLineValues();

         if (3 == vecRequestLineValues.size()) {
            m_method = vecRequestLineValues.get(0);
            m_path = vecRequestLineValues.get(1);
            setProtocol(vecRequestLineValues.get(2));
         
            if (isLoggingDebug) {
               Logger.debug("HttpRequest: calling parseBody");
            }
         
            parseBody();
            streamSuccess = true;
         } else {
            if (Logger.isLogging(Logger.LogLevel.Warning)) {
               String msg = "number of tokens: " +
                             vecRequestLineValues.size();
               Logger.warning(msg);
            
               for (String s : vecRequestLineValues) {
                  Logger.warning(s);
               }
            }
         
            //throw BasicException("unable to parse request line");
         }

      } else {
         //throw BasicException("unable to parse headers");
         System.out.println("HttpRequest.streamFromSocket failed");
      }
   
      return streamSuccess;
   }
   
   /**
    * Determines if the request object has been successfully initialized
    * @return boolean indicating whether the object was initialized
    */
   public boolean isInitialized() {
      return m_initialized;
   }

   /**
    * Retrieves the request line for the request
    * @return the request line value
    */
   public String getRequest() {
      return getRequestLine();
   }
   
   /**
    * Retrieves the HTTP method for the request
    * @return the method value
    */
   public String getMethod() {
      return m_method;
   }
   
   /**
    * Retrieves the path for the request
    * @return the path value
    */
   public String getPath() {
      return m_path;
   }

   /**
    * Determines if the specified key exists in the arguments
    * @param key the key whose existence is being tested
    * @return boolean indicating whether the specified key exists in the arguments
    */
   public boolean hasArgument(String key) {
      return m_arguments.hasKey(key);
   }
   
   /**
    * Retrieves the value associated with the specified argument key
    * @param key the key whose value is being retrieved
    * @throw InvalidKeyException
    * @return the value for the associated key
    */
   public String getArgument(String key) {
      return m_arguments.getValue(key);
   }
   
   /**
    * Retrieves the keys for all arguments given with the request
    * @return list of argument keys
    */
   public List<String> getArgumentKeys() {
      return m_arguments.getKeys();
   }

   /**
    * Determines if the Accept header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasAccept() {
      return hasHeaderValue(HTTP.HTTP_ACCEPT);
   }
   
   /**
    * Determines if the Accept-encoding header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasAcceptEncoding() {
      return hasHeaderValue(HTTP.HTTP_ACCEPT_ENCODING);
   }
   
   /**
    * Determines if the Accept-language header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasAcceptLanguage() {
      return hasHeaderValue(HTTP.HTTP_ACCEPT_LANGUAGE);
   }
   
   /**
    * Determines if the Connection header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasConnection() {
      return hasHeaderValue(HTTP.HTTP_CONNECTION);
   }
   
   /**
    * Determines if the Do Not Track (dnt) header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasDNT() {
      return hasHeaderValue("dnt");
   }
   
   /**
    * Determines if the Host header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasHost() {
      return hasHeaderValue(HTTP.HTTP_HOST);
   }
   
   /**
    * Determines if the User-agent header is present
    * @return boolean indicating if the header value is present
    */
   public boolean hasUserAgent() {
      return hasHeaderValue(HTTP.HTTP_USER_AGENT);
   }

   /**
    * Retrieves the value associated with the Accept header
    * @return the specified HTTP header value
    */
   public String getAccept() {
      return getHeaderValue(HTTP.HTTP_ACCEPT);
   }
   
   /**
    * Retrieves the value associated with the Accept-encoding header
    * @return the specified HTTP header value
    */
   public String getAcceptEncoding() {
      return getHeaderValue(HTTP.HTTP_ACCEPT_ENCODING);
   }
   
   /**
    * Retrieves the value associated with the Accept-language header
    * @return the specified HTTP header value
    */
   public String getAcceptLanguage() {
      return getHeaderValue(HTTP.HTTP_ACCEPT_LANGUAGE);
   }
   
   /**
    * Retrieves the value associated with the Connection header
    * @return the specified HTTP header value
    */
   public String getConnection() {
      return getHeaderValue(HTTP.HTTP_CONNECTION);
   }
   
   /**
    * Retrieves the value associated with the Do Not Track (dnt) header
    * @return the specified HTTP header value
    */
   public String getDNT() {
      return getHeaderValue("dnt");
   }
   
   /**
    * Retrieves the value associated with the Host header
    * @return the specified HTTP header value
    */
   public String getHost() {
      return getHeaderValue(HTTP.HTTP_HOST);
   }
   
   /**
    * Retrieves the value associated with the User-agent header
    * @return the specified HTTP header value
    */
   public String getUserAgent() {
      return getHeaderValue(HTTP.HTTP_USER_AGENT);
   }

   /**
    * Parse the body (if present in the request)
    */
   protected void parseBody() {
      String body = getBody();
   
      if ((null != body) && !body.isEmpty() && body.contains(AMPERSAND)) {
         StringTokenizer st1 = new StringTokenizer(body, AMPERSAND);

         while (st1.hasMoreTokens()) {
            String pair = st1.nextToken();

            pair = pair.replace(PLUS, SPACE);
            pair = pair.replace(ENC_DOUBLE_QUOTE, DOUBLE_QUOTE);
            pair = pair.replace(ENC_SINGLE_QUOTE, SINGLE_QUOTE);
            pair = pair.replace(ENC_AMPERSAND, AMPERSAND);
            pair = pair.replace(ENC_PERCENT, PERCENT);
            pair = pair.replace(ENC_ATSIGN, ATSIGN);
            pair = pair.replace(ENC_DOLLAR, DOLLAR);
            pair = pair.replace(ENC_POUND, POUND);
            pair = pair.replace(ENC_EXCLAMATION, EXCLAMATION);
            pair = pair.replace(ENC_TILDE, TILDE);
            pair = pair.replace(ENC_CARET, CARET);
            pair = pair.replace(ENC_OPEN_PAREN, OPEN_PAREN);
            pair = pair.replace(ENC_CLOSE_PAREN, CLOSE_PAREN);
            pair = pair.replace(ENC_PLUS, PLUS);
            pair = pair.replace(ENC_BACKTICK, BACKTICK);
            pair = pair.replace(ENC_OPEN_BRACE, OPEN_BRACE);
            pair = pair.replace(ENC_CLOSE_BRACE, CLOSE_BRACE);
            pair = pair.replace(ENC_VERT_BAR, VERT_BAR);
            pair = pair.replace(ENC_OPEN_BRACKET, OPEN_BRACKET);
            pair = pair.replace(ENC_CLOSE_BRACKET, CLOSE_BRACKET);
            pair = pair.replace(ENC_BACKSLASH, BACKSLASH);
            pair = pair.replace(ENC_COLON, COLON);
            pair = pair.replace(ENC_SEMICOLON, SEMICOLON);
            pair = pair.replace(ENC_LESS_THAN, LESS_THAN);
            pair = pair.replace(ENC_GREATER_THAN, GREATER_THAN);
            pair = pair.replace(ENC_QUESTION, QUESTION);
            pair = pair.replace(ENC_COMMA, COMMA);
            pair = pair.replace(ENC_SLASH, SLASH);

            int posEqual = pair.indexOf('=');
            if (posEqual > -1) {
               String key = pair.substring(0, posEqual);
               String value = pair.substring(posEqual + 1);
               value = value.replace(ENC_EQUAL, EQUAL);
               m_arguments.addPair(key, value);
            }
         }
      }
   }

}
