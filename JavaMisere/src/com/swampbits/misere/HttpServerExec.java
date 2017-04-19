/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.StdLogger;
import static com.swampbits.misere.HttpServer.CFG_FILE_NAME;
import static com.swampbits.misere.HttpServer.ENV_VAR_CFG_PATH;

/**
 *
 * @author paul
 */
public class HttpServerExec {
   public static void main(String[] args) {
      String configFilePath = "";

      if (args.length > 0) {
         configFilePath = args[0];
      } else {
         String configPath = System.getenv(ENV_VAR_CFG_PATH);
         if (null != configPath) {
            configFilePath = configPath;
            if (!configFilePath.endsWith("/")) {
               configFilePath += "/";
            }
            configFilePath += CFG_FILE_NAME;
         }
      }
      
      configFilePath = "/Users/paul/misere.ini";

      Logger.setLogger(new StdLogger(Logger.LogLevel.Warning));

      if (configFilePath.isEmpty()) {
         Logger.error("no config file provided");
         System.exit(1);
      }

      try {
         HttpServer server = new HttpServer(configFilePath);
         server.run();
      } catch (Exception e) {
         Logger.critical("exception running HttpServer: " + e.getMessage());
         e.printStackTrace(System.err);
         System.exit(1);
      } catch (Throwable t) {
         Logger.critical("exception running HttpServer: " + t.getMessage());
         t.printStackTrace(System.err);
         System.exit(1);
      }
   }
   
}
