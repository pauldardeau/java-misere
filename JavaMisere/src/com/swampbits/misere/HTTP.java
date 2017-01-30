/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

/**
 *
 * @author paul
 */
public class HTTP {
   // protocol
   public static final String HTTP_PROTOCOL1_0    = "HTTP/1.0";
   public static final String HTTP_PROTOCOL1_1    = "HTTP/1.1";

   // methods
   public static final String HTTP_METHOD_CONNECT = "CONNECT";
   public static final String HTTP_METHOD_DELETE  = "DELETE";
   public static final String HTTP_METHOD_GET     = "GET";
   public static final String HTTP_METHOD_HEAD    = "HEAD";
   public static final String HTTP_METHOD_OPTIONS = "OPTIONS";
   public static final String HTTP_METHOD_POST    = "POST";
   public static final String HTTP_METHOD_PUT     = "PUT";
   public static final String HTTP_METHOD_TRACE   = "TRACE";


   // general header fields
   public static final String HTTP_CACHE_CONTROL        = "Cache-Control";
   public static final String HTTP_CONNECTION           = "Connection";
   public static final String HTTP_DATE                 = "Date";
   public static final String HTTP_PRAGMA               = "Pragma";
   public static final String HTTP_TRAILER              = "Trailer";
   public static final String HTTP_TRANSFER_ENCODING    = "Transfer-Encoding";
   public static final String HTTP_UPGRADE              = "Upgrade";
   public static final String HTTP_VIA                  = "Via";
   public static final String HTTP_WARNING              = "Warning";

   // request header fields
   public static final String HTTP_ACCEPT               = "Accept";
   public static final String HTTP_ACCEPT_CHARSET       = "Accept-Charset";
   public static final String HTTP_ACCEPT_ENCODING      = "Accept-Encoding";
   public static final String HTTP_ACCEPT_LANGUAGE      = "Accept-Language";
   public static final String HTTP_AUTHORIZATION        = "Authorization";
   public static final String HTTP_EXPECT               = "Expect";
   public static final String HTTP_FROM                 = "From";
   public static final String HTTP_HOST                 = "Host";
   public static final String HTTP_IF_MATCH             = "If-Match";
   public static final String HTTP_IF_MODIFIED_SINCE    = "If-Modified-Since";
   public static final String HTTP_IF_NONE_MATCH        = "If-None-Match";
   public static final String HTTP_IF_RANGE             = "If-Range";
   public static final String HTTP_IF_UNMODIFIED_SINCE  = "If-Unmodified-Since";
   public static final String HTTP_MAX_FORWARDS         = "Max-Forwards";
   public static final String HTTP_PROXY_AUTHORIZATION  = "Proxy-Authorization";
   public static final String HTTP_RANGE                = "Range";
   public static final String HTTP_REFERER              = "Referer";
   public static final String HTTP_TE                   = "TE";
   public static final String HTTP_USER_AGENT           = "User-Agent";


   // response header fields
   public static final String HTTP_ACCEPT_RANGES        = "Accept-Ranges";
   public static final String HTTP_AGE                  = "Age";
   public static final String HTTP_ETAG                 = "ETag";
   public static final String HTTP_LOCATION             = "Location";
   public static final String HTTP_PROXY_AUTHENTICATE   = "Proxy-Authenticate";
   public static final String HTTP_RETRY_AFTER          = "Retry-After";
   public static final String HTTP_SERVER               = "Server";
   public static final String HTTP_VARY                 = "Vary";
   public static final String HTTP_WWW_AUTHENTICATE     = "WWW-Authenticate";


   // entity header fields
   public static final String HTTP_ALLOW                = "Allow";
   public static final String HTTP_CONTENT_ENCODING     = "Content-Encoding";
   public static final String HTTP_CONTENT_LANGUAGE     = "Content-Language";
   public static final String HTTP_CONTENT_LENGTH       = "Content-Length";
   public static final String HTTP_CONTENT_LOCATION     = "Content-Location";
   public static final String HTTP_CONTENT_MD5          = "Content-MD5";
   public static final String HTTP_CONTENT_RANGE        = "Content-Range";
   public static final String HTTP_CONTENT_TYPE         = "Content-Type";
   public static final String HTTP_EXPIRES              = "Expires";
   public static final String HTTP_LAST_MODIFIED        = "Last-Modified";


   // Informational 1xx
   public static final String HTTP_RESP_INFO_CONTINUE                        = "100 Continue";
   public static final String HTTP_RESP_INFO_SWITCH_PROTOCOLS                = "101 Switching Protocols";


   // Successful 2xx
   public static final String HTTP_RESP_SUCCESS_OK                           = "200 OK";
   public static final String HTTP_RESP_SUCCESS_CREATED                      = "201 Created";
   public static final String HTTP_RESP_SUCCESS_ACCEPTED                     = "202 Accepted";
   public static final String HTTP_RESP_SUCCESS_NO_CONTENT                   = "204 No Content";
   public static final String HTTP_RESP_SUCCESS_RESET_CONTENT                = "205 Reset Content";
   public static final String HTTP_RESP_SUCCESS_PARTIAL_CONTENT              = "206 Partial Content";


   // Redirection 3xx
   public static final String HTTP_RESP_REDIRECT_MULTIPLE_CHOICES            = "300 Multiple Choices";
   public static final String HTTP_RESP_REDIRECT_MOVED_PERMANENTLY           = "301 Moved Permanently";
   public static final String HTTP_RESP_REDIRECT_FOUND                       = "302 Found";
   public static final String HTTP_RESP_REDIRECT_SEE_OTHER                   = "303 See Other";
   public static final String HTTP_RESP_REDIRECT_NOT_MODIFIED                = "304 Not Modified";
   public static final String HTTP_RESP_REDIRECT_USE_PROXY                   = "305 Use Proxy";
   public static final String HTTP_RESP_REDIRECT_TEMPORARY_REDIRECT          = "307 Temporary Redirect";


   // Client Errors 4xx
   public static final String HTTP_RESP_CLIENT_ERR_BAD_REQUEST               = "400 Bad Request";
   public static final String HTTP_RESP_CLIENT_ERR_UNAUTHORIZED              = "401 Unauthorized";
   public static final String HTTP_RESP_CLIENT_ERR_FORBIDDEN                 = "403 Forbidden";
   public static final String HTTP_RESP_CLIENT_ERR_NOT_FOUND                 = "404 Not Found";
   public static final String HTTP_RESP_CLIENT_ERR_METHOD_NOT_ALLOWED        = "405 Method Not Allowed";
   public static final String HTTP_RESP_CLIENT_ERR_NOT_ACCEPTABLE            = "406 Not Acceptable";
   public static final String HTTP_RESP_CLIENT_ERR_PROXY_AUTH_REQUIRED       = "407 Proxy Authentication Required";
   public static final String HTTP_RESP_CLIENT_ERR_REQUEST_TIMEOUT           = "408 Request Timeout";
   public static final String HTTP_RESP_CLIENT_ERR_CONFLICT                  = "409 Conflict";
   public static final String HTTP_RESP_CLIENT_ERR_GONE                      = "410 Gone";
   public static final String HTTP_RESP_CLIENT_ERR_LENGTH_REQUIRED           = "411 Length Required";
   public static final String HTTP_RESP_CLIENT_ERR_PRECONDITION_FAILED       = "412 Precondition Failed";
   public static final String HTTP_RESP_CLIENT_ERR_REQUEST_TOO_LARGE         = "413 Request Entity Too Large";
   public static final String HTTP_RESP_CLIENT_ERR_REQUEST_URI_TOO_LONG      = "414 Request-URI Too Long";
   public static final String HTTP_RESP_CLIENT_ERR_REQUEST_UNSUPPORTED_MEDIA = "415 Unsupported Media Type";
   public static final String HTTP_RESP_CLIENT_ERR_REQUESTED_RANGE           = "416 Requested Range Not Satisfiable";
   public static final String HTTP_RESP_CLIENT_ERR_EXPECTATION_FAILED        = "417 Expectation Failed";


   // Server Errors 5xx
   public static final String HTTP_RESP_SERV_ERR_INTERNAL_ERROR              = "500 Internal Server Error";
   public static final String HTTP_RESP_SERV_ERR_NOT_IMPLEMENTED             = "501 Not Implemented";
   public static final String HTTP_RESP_SERV_ERR_BAD_GATEWAY                 = "502 Bad Gateway";
   public static final String HTTP_RESP_SERV_ERR_SERVICE_UNAVAILABLE         = "503 Service Unavailable";
   public static final String HTTP_RESP_SERV_ERR_GATEWAY_TIMEOUT             = "504 Gateway Timeout";
   public static final String HTTP_RESP_SERV_ERR_HTTP_VERSION_UNSUPPORTED    = "505 HTTP Version Not Supported";   
}
