/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere.handler;

import java.util.Map;

import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.StdLogger;
import com.swampbits.misere.HttpRequest;
import com.swampbits.misere.HttpResponse;

/**
 *
 * @author paul
 */
public class ServerStatsHandler extends AbstractHandler {

   @Override
   public void serviceRequest(HttpRequest request,
                              HttpResponse response) {
      StringBuilder body = new StringBuilder("<html><body>");
      Logger logger = Logger.getLogger();

      if (logger != null) {
         Logger loggerInstance = logger;
         if (loggerInstance instanceof StdLogger) {
            StdLogger stdLogger = (StdLogger) loggerInstance;
            Map<String, Long> mapLogLevelOccurrences
                    = stdLogger.populateLogLevelOccurrences();

            if ((null != mapLogLevelOccurrences) && !mapLogLevelOccurrences.isEmpty()) {
               body.append("<table border=\"1\">");
               body.append("<tr><th align=\"left\">Log Level</th><th>Occurrences</th></tr>");

               for (String logLevel : mapLogLevelOccurrences.keySet()) {
                  body.append("<tr><td>");
                  body.append(logLevel);
                  body.append("</td><td>");
                  body.append(mapLogLevelOccurrences.get(logLevel));
                  body.append("</td></tr>");
               }

               body.append("</table>");
            } else {
               body.append("No log level stats available");
            }
         } else {
            body.append("No log level stats available (must use StdLogger)");
         }

         body.append("</body></html>");

         response.setBody(body.toString());
      }
   }

}
