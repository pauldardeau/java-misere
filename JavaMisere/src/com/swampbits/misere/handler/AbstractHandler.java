/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere.handler;

import com.swampbits.misere.HttpHandler;
import com.swampbits.misere.HttpRequest;
import com.swampbits.misere.HttpResponse;
import com.swampbits.chaudiere.KeyValuePairs;
import com.swampbits.chaudiere.Logger;

/**
 *
 * @author paul
 */
public class AbstractHandler implements HttpHandler {
   
   @Override
   public boolean init(String path,
                       KeyValuePairs kvpArguments) {
      return true;
   }
   
   @Override
   public void serviceRequest(HttpRequest request,
                              HttpResponse response) {
      Logger.debug("serviceRequest called on AbstractHandler. did you forget to override serviceRequest?");
   }
   
   @Override
   public boolean isAvailable() {
      return true;
   }
   
   @Override
   public void destroy() {
      
   }
}
