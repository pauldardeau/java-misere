/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere.handler;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.swampbits.misere.HttpRequest;
import com.swampbits.misere.HttpResponse;
import java.util.TimeZone;

/**
 *
 * @author paul
 */
public class GMTDateTimeHandler extends AbstractHandler {

   private final DateFormat df;
   
   public GMTDateTimeHandler() {
      df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      df.setTimeZone(TimeZone.getTimeZone("GMT"));
   }

   @Override
   public void serviceRequest(HttpRequest request,
                              HttpResponse response) {
      StringBuilder body = new StringBuilder("<html><body>");
      body.append(df.format(new Date()));
      body.append("</body></html>");
      response.setBody(body.toString());
   }

}
