/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere.handler;

import java.util.ArrayList;

import com.swampbits.misere.HttpRequest;
import com.swampbits.misere.HttpResponse;

/**
 *
 * @author paul
 */
public class EchoHandler extends AbstractHandler {

   @Override
   public void serviceRequest(HttpRequest request,
                              HttpResponse response) {
      StringBuilder body = new StringBuilder();
      body.append("<html><body>");
   
      body.append(request.getRequestMethod());
      body.append(" ");
      body.append(request.getRequestPath());
      body.append(" ");
      body.append(request.getProtocol());
      body.append("<br/>");

      ArrayList<String> headerKeys = new ArrayList<>();
      request.getHeaderKeys(headerKeys);
   
      if (!headerKeys.isEmpty()) {
         for (String headerKey : headerKeys) {
            String headerValue = request.getHeaderValue(headerKey);
            body.append(headerKey);
            body.append(": ");
            body.append(headerValue);
            body.append("<br/>");
         }
      } else {
         body.append("no headers found!");
      }
   
      body.append("<br/>");
   
      String requestBody = request.getBody();
   
      if ((requestBody != null) && !requestBody.isEmpty()) {
         body.append(requestBody);
      } else {
         body.append("*** no body in request ***<br/>");
      }

      body.append("<br/>");
      body.append("</body></html>");
   
      response.setBody(body.toString());      
   }

}
