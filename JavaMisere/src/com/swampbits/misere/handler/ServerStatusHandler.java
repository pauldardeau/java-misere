/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere.handler;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import com.swampbits.misere.HttpRequest;
import com.swampbits.misere.HttpResponse;

/**
 *
 * @author paul
 */
public class ServerStatusHandler extends AbstractHandler {

   @Override
   public void serviceRequest(HttpRequest request,
                              HttpResponse response) {
      OperatingSystemMXBean osMXBean =
              ManagementFactory.getOperatingSystemMXBean();
      StringBuilder body = new StringBuilder("<html><body>");
      body.append("<table border=\"1\">");
      body.append("<tr><th align=\"left\">Metric</th><th>Value</th></tr>");
      
      body.append("<tr><td>Arch</td>");
      body.append("<td>");
      body.append(osMXBean.getArch());
      body.append("</td></tr>");

      body.append("<tr><td>Processors</td>");
      body.append("<td>");
      body.append(osMXBean.getAvailableProcessors());
      body.append("</td></tr>");

      body.append("<tr><td>OS Name</td>");
      body.append("<td>");
      body.append(osMXBean.getName());
      body.append("</td></tr>");

      body.append("<tr><td>OS Version</td>");
      body.append("<td>");
      body.append(osMXBean.getVersion());
      body.append("</td></tr>");

      body.append("<tr><td>System Load Average</td>");
      body.append("<td>");
      body.append(osMXBean.getSystemLoadAverage());
      body.append("</td></tr>");

      body.append("</table></body></html>");
      
      response.setBody(body.toString());
   }

}
