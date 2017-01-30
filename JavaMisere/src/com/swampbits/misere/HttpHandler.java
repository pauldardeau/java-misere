/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.KeyValuePairs;


/**
 *
 * @author paul
 */
public interface HttpHandler {

   /**
    * Initialize the handler with the indicated path and arguments.
    * @param path the path associated with this handler.
    * @param kvpArguments the configuration key-value arguments.
    * @see KeyValuePairs()
    * @return boolean indicating if initialization of handler succeeded
    */
   boolean init(String path, KeyValuePairs kvpArguments);
   
   /**
    * The destroy method is called as part of cleanup operations when the server
    * is being terminated.
    */
   void destroy();
   
   /**
    * The serviceRequest method is called to satisfy an HTTP request on the
    * path associated with this handler.
    * @param request the HTTP request
    * @param response the HTTP response
    * @see HttpRequest()
    * @see HttpResponse()
    */
   void serviceRequest(HttpRequest request, HttpResponse response);
   
   /**
    * The isAvailable method is called by the server prior to handing off a request
    * for processing to determine if the handler is currently available.
    * @return boolean indicating whether the handler is currently available for handing requests.
    */
   boolean isAvailable();
}
