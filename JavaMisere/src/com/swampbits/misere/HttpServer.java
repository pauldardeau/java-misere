/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;

import com.swampbits.chaudiere.DefaultThreadingFactory;
import com.swampbits.chaudiere.IniReader;
import com.swampbits.chaudiere.KeyValuePairs;
import com.swampbits.chaudiere.Logger;
import com.swampbits.chaudiere.SectionedConfigDataSource;
import com.swampbits.chaudiere.SocketRequest;
import com.swampbits.chaudiere.SystemInfo;
import com.swampbits.chaudiere.ThreadPoolDispatcher;
import com.swampbits.chaudiere.ThreadingFactory;

import com.swampbits.misere.handler.*;

/**
 *
 * @author paul
 */
public class HttpServer {
   
   public static final String SERVER_NAME             = "Misere";
   public static final String SERVER_VERSION          = "0.1";
   public static final String CFG_TRUE_SETTING_VALUES = "yes|true|1";

   public static final String EMPTY = "";
   public static final String SPACE = " ";
   public static final String EOL   = "\n";
   public static final String COLON = ":";

   // default settings
   public static final int CFG_DEFAULT_SEND_BUFFER_SIZE     = 8192;
   public static final int CFG_DEFAULT_RECEIVE_BUFFER_SIZE  = 8192;
   public static final int CFG_DEFAULT_PORT_NUMBER          = 9000;
   public static final int CFG_DEFAULT_THREAD_POOL_SIZE     = 4;

   // configuration sections
   public static final String CFG_SECTION_SERVER                 = "server";
   public static final String CFG_SECTION_LOGGING                = "logging";
   public static final String CFG_SECTION_HANDLERS               = "handlers";

   // logging config values
   public static final String CFG_LOGFILE_ACCESS                 = "access_log";
   public static final String CFG_LOGFILE_ERROR                  = "error_log";

   // server config values
   public static final String CFG_SERVER_PORT                    = "port";
   public static final String CFG_SERVER_THREADING               = "threading";
   public static final String CFG_SERVER_THREAD_POOL_SIZE        = "thread_pool_size";
   public static final String CFG_SERVER_LOG_LEVEL               = "log_level";
   public static final String CFG_SERVER_SEND_BUFFER_SIZE        = "socket_send_buffer_size";
   public static final String CFG_SERVER_RECEIVE_BUFFER_SIZE     = "socket_receive_buffer_size";
   public static final String CFG_SERVER_ALLOW_BUILTIN_HANDLERS  = "allow_builtin_handlers";
   public static final String CFG_SERVER_STRING                  = "server_string";
   public static final String CFG_SERVER_SOCKETS                 = "sockets";

   // socket options
   public static final String CFG_SOCKETS_SOCKET_SERVER          = "socket_server";
   public static final String CFG_SOCKETS_KERNEL_EVENTS          = "kernel_events";

   // threading options
   public static final String CFG_THREADING_PTHREADS             = "pthreads";
   public static final String CFG_THREADING_CPP11                = "c++11";
   public static final String CFG_THREADING_GCD_LIBDISPATCH      = "gcd_libdispatch";
   public static final String CFG_THREADING_NONE                 = "none";

   // logging level options
   public static final String CFG_LOGGING_CRITICAL               = "critical";
   public static final String CFG_LOGGING_ERROR                  = "error";
   public static final String CFG_LOGGING_WARNING                = "warning";
   public static final String CFG_LOGGING_INFO                   = "info";
   public static final String CFG_LOGGING_DEBUG                  = "debug";
   public static final String CFG_LOGGING_VERBOSE                = "verbose";

   // mime types
   public static final String MIME_APPLICATION_JSON  = "application/json";
   public static final String MIME_APPLICATION_XML   = "application/xml";
   public static final String MIME_TEXT_HTML         = "text/html";
   public static final String MIME_TEXT_PLAIN        = "text/plain";

   // module config values
   public static final String MODULE_DLL_NAME = "dll";
   public static final String APP_PREFIX = "app:";

   public static final int APP_PREFIX_LEN = APP_PREFIX.length();

   public static final String[] LOG_WEEKDAY_NAME = {
      "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
   };

   public static final String[] LOG_MONTH_NAME = {
      "Jan", "Feb", "Mar", "Apr", "May", "Jun",
      "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
   };

   private ServerSocket m_serverSocket;
   private ThreadPoolDispatcher m_threadPool;
   private ThreadingFactory m_threadingFactory;
   private Map<String, String> m_mapProperties;
   private Map<String, HttpHandler> m_mapPathHandlers;
   private String m_accessLogFile;
   private String m_errorLogFile;
   private String m_logLevel;
   private String m_concurrencyModel;
   private final String m_configFilePath;
   private String m_startupTime;
   private String m_serverString;
   private String m_threading;
   private String m_sockets;
   private boolean m_isDone;
   private boolean m_isThreaded;
   private boolean m_isUsingKernelEventServer;
   private boolean m_isFullyInitialized;
   private boolean m_allowBuiltInHandlers;
   private final boolean m_requireAllHandlersForStartup;
   private final boolean m_compressionEnabled;
   private int m_threadPoolSize;
   private int m_serverPort;
   private int m_socketSendBufferSize;
   private int m_socketReceiveBufferSize;
   private final int m_minimumCompressionSize;
   
   /**
    * Constructs an HttpServer with the file name/path for a configuration file
    * @param configFilePath the file name/path for configuration settings
    */
   public HttpServer(String configFilePath) {
      m_serverSocket = null;
      m_threadPool = null;
      m_threadingFactory = null;
      m_configFilePath = configFilePath;
      m_isDone = false;
      m_isThreaded = true;
      m_isUsingKernelEventServer = false;
      m_isFullyInitialized = false;
      m_allowBuiltInHandlers = false;
      m_requireAllHandlersForStartup = false;
      m_compressionEnabled = false;
      m_threadPoolSize = CFG_DEFAULT_THREAD_POOL_SIZE;
      m_serverPort = CFG_DEFAULT_PORT_NUMBER;
      m_minimumCompressionSize = 1000;
      //Logger.logInstanceCreate("HttpServer");
   }
   
      /**
       * Retrieves the current time in Greenwich Mean Time (GMT)
       * @return current time in GMT
       */
   public String getSystemDateGMT() {
      //TODO:
      return null;
   }
   
   /**
    * Retrieves the current time for server in local time
    * @return current time as local server time
    */
   public String getLocalDateTime() {
      //TODO:
      return null;
   }

   /**
    * Constructs HTTP response headers using the specified response code and
    * collection of header key/value pairs
    * @param responseCode the HTTP response code
    * @param mapHeaders collection of HTTP header key/value pairs
    * @return HTTP headers formatted as a string
    */
   public String buildHeader(String responseCode,
                             Map<String, String> mapHeaders) {

      StringBuilder sb = new StringBuilder();

      if (!responseCode.isEmpty()) {
         sb.append(HTTP.HTTP_PROTOCOL1_1);
         sb.append(SPACE);
         sb.append(responseCode);
         sb.append(EOL);
      }

      for (String headerKey : mapHeaders.keySet()) {
         sb.append(headerKey);  // header key
      
         if (!headerKey.endsWith(COLON)) {
            sb.append(COLON);
         }
      
         sb.append(SPACE);

         sb.append(mapHeaders.get(headerKey));  // header value
         sb.append(EOL);
      }

      sb.append(EOL);

      return sb.toString();
   }

   /**
    * Registers an HttpHandler for the specified path
    * @param path the path to associate with the specified handler
    * @param handler the handler to invoke when a request arrives for the specified path
    * @see HttpHandler()
    * @return boolean indicating if the handler was successfully registered
    */
   public boolean addPathHandler(String path, HttpHandler handler) {
      //TODO:
      return false;
   }
   
   /**
    * Removes the handler for the specified path
    * @param path the path whose associated handler should be removed (deregistered)
    * @return boolean indicating if a path was deregistered for the path
    */
   public boolean removePathHandler(String path) {
      //TODO:
      return false;
   }
   
   /**
    * Retrieves the handler associated with the specified path
    * @param path the path whose handler is desired
    * @return the handler associated with the path, or null if there is none
    */
   public HttpHandler getPathHandler(String path) {
      //TODO:
      return null;
   }

   /**
    * Runs the built-in socket server
    * @return exit code for the HTTP server process
    */
   public int runSocketServer() {
      int rc = 0;
   
      if (null == m_serverSocket) {
         Logger.critical("runSocketServer called with null serverSocket");
         return 1;
      }
   
      while (!m_isDone) {
         java.net.Socket javaSocket;
         
         try {
            javaSocket = m_serverSocket.accept();
         } catch (IOException ioe) {
            javaSocket = null;
         }

         if (null == javaSocket) {
            continue;
         }

         if (Logger.isLogging(Logger.LogLevel.Debug)) {
            Logger.debug("*****************************************");
            Logger.debug("client connected");
         }

         try {
            HttpRequestHandler handler =
                  new HttpRequestHandler(this,
                          new com.swampbits.chaudiere.Socket(javaSocket));

            if (m_isThreaded && (null != m_threadPool)) {
               handler.setThreadPooling(true);
               // give it to the thread pool
               m_threadPool.addRequest(handler);
            } else {
               handler.run();
            }
         } catch (Exception e) {
            rc = 1;
            Logger.error("HttpServer runServer exception caught: " +
                          e.getMessage());
         } catch (Throwable t) {
            rc = 1;
            Logger.error("HttpServer runServer unknown exception caught");
         }
      }
   
      return rc;   
   }
   
   /**
    * Runs a kernel event server (e.g., kqueue or epoll)
    * @return exit code for the HTTP server process
    */
   public int runKernelEventServer() {
      //TODO:
      return 1;
   }
   
   /**
    * Runs the HTTP server using either the built-in socket server or a kernel event server
    * @return exit code for the HTTP server process
    */
   public int run() {
      if (init(CFG_DEFAULT_PORT_NUMBER)) {
         if (!m_isFullyInitialized) {
            Logger.critical("server not initialized");
            return 1;
         } else {
            if (m_isUsingKernelEventServer) {
               return runKernelEventServer();
            } else {
               return runSocketServer();
            }
         }
      } else {
         return 1;
      }
   }

   /**
    * Logs a HTTP request
    * @param clientIPAddress the IP address of the system that generated the HTTP request
    * @param requestLine the first line of the HTTP request
    * @param responseCode the HTTP response code
    */
   public void logRequest(String clientIPAddress,
                          String requestLine,
                          String responseCode) {
      //TODO:
   }

   /**
    * Logs a HTTP request that was processed by a thread pool worker
    * @param clientIPAddress the IP address of the system that generated the HTTP request
    * @param requestLine the first line of the HTTP request
    * @param responseCode the HTTP response code
    * @param threadWorkerId the identifier of the thread pool worker that processed the request
    */
   public void logRequest(String clientIPAddress,
                          String requestLine,
                          String responseCode,
                          String threadWorkerId) {
      //TODO:
   }
   
   /**
    * Retrieves the configuration data source of configuration settings
    * @throws java.lang.Exception
    * @see SectionedConfigDataSource()
    * @return the configuration data source
    */
   public SectionedConfigDataSource getConfigDataSource() throws Exception {
      return new IniReader(m_configFilePath);
   }
   
   /**
    * Retrieves the size of the socket send buffer
    * @return size of the socket send buffers
    */
   public int getSocketSendBufferSize() {
      return m_socketSendBufferSize;
   }
   
   /**
    * Retrieves the size of the socket receive buffer
    * @return size of the socket receive buffers
    */
   public int getSocketReceiveBufferSize() {
      return m_socketReceiveBufferSize;
   }
   
   /**
    * Retrieves the identifier for the server
    * @return server identifier
    */
   public String getServerId() {
      return m_serverString;
   }
   
   /**
    * Retrieves the size in bytes of a generic (void*) pointer
    * @return platform pointer size
    */
   public int platformPointerSizeBits() {
      //TODO:
      return 0;
   }

   /**
    * Service a request for a socket when using a kernel event server
    * @param socketRequest the SocketRequest to process
    * @see SocketRequest()
    */
   public void serviceSocket(SocketRequest socketRequest) {
      HttpRequestHandler requestHandler =
            new HttpRequestHandler(this, socketRequest);

      if (null != m_threadPool) {
         // Hand off the request to the thread pool for asynchronous processing
         requestHandler.setThreadPooling(true);
         m_threadPool.addRequest(requestHandler);
      } else {
         // no thread pool available -- process it synchronously
         requestHandler.run();
      }
   }

   /**
    * Convenience method to retrieve a setting and convert it to a boolean
    * @param kvp the collection of key/value pair settings
    * @param setting the name of the setting whose value is to be retrieved and converted
    * @see KeyValuePairs()
    * @return boolean value (or false if value cannot be retrieved or converted)
    */
   public boolean hasTrueValue(KeyValuePairs kvp, String setting) {
      String value = kvp.getValue(setting);
      if (null != value) {
         value = value.toLowerCase();
         return CFG_TRUE_SETTING_VALUES.contains(value);
      }
      return false;
   }
   
   /**
    * Convenience method to retrieve a setting and convert it to an integer
    * @param kvp the collection of key/value pair settings
    * @param setting the name of the setting whose value is to be retrieved and converted
    * @see KeyValuePairs()
    * @return integer value (or -1 if value cannot be retrieved or converted)
    */
   public int getIntValue(KeyValuePairs kvp, String setting) {
      return Integer.parseInt(kvp.getValue(setting));
   }
   
   /**
    * Convenience method to replace all occurrences of keys in collection with their values
    * @param kvp the collection of key/value pairs for replacement
    * @param s the string to search and replace all variables in
    */
   public void replaceVariables(KeyValuePairs kvp, String s) {
      
   }
   
   /**
    * Determines if compression is turned on for the specified mime type
    * @param mimeType the mime type to check whether to compress
    * @return boolean indicating whether the specified mime type is to be compressed
    */
   public boolean compressResponse(String mimeType) {
      return (mimeType.equals(MIME_TEXT_HTML) ||
              mimeType.equals(MIME_TEXT_PLAIN) ||
              mimeType.equals(MIME_APPLICATION_JSON) ||
              mimeType.equals(MIME_APPLICATION_XML));
   }
   
   /**
    * Determines if gzip compression is enabled for the server
    * @return boolean indicating if gzip compression is enabled
    */
   public boolean compressionEnabled() {
      return m_compressionEnabled;
   }
   
   /**
    * Retrieves the minimum size of the response payload to be compressed
    * @return minimum size of response payload (in bytes) to be compressed
    */
   public int minimumCompressionSize() {
      return m_minimumCompressionSize;
   }

   /**
    * Initializes the HTTP server on the specified port by default by reading and
    * applying configuration values read from configuration data source
    * @param port the default port (may be overridden by configuration values)
    * @return boolean indicating whether initialization was successful
    */
   protected boolean init(int port) {
      boolean isLoggingDebug = Logger.isLogging(Logger.LogLevel.Debug);
      m_serverPort = port;
	
      SectionedConfigDataSource configDataSource = null;
      boolean haveDataSource = false;
   
      try {
         configDataSource = getConfigDataSource();
         haveDataSource = true;
      } catch (Exception e) {
         Logger.error("exception retrieving config data: " + e.getMessage());
      } catch (Throwable t) {
         Logger.error("exception retrieving config data");
      }
   
      if ((null == configDataSource) || !haveDataSource) {
         Logger.error("unable to retrieve config data");
         return false;
      }

      // start out with our default settings
      m_socketSendBufferSize = CFG_DEFAULT_SEND_BUFFER_SIZE;
      m_socketReceiveBufferSize = CFG_DEFAULT_RECEIVE_BUFFER_SIZE;

      try {
         KeyValuePairs kvpServerSettings = new KeyValuePairs();
         KeyValuePairs kvpLoggingSettings = new KeyValuePairs();
         KeyValuePairs kvpHandlerSettings = new KeyValuePairs();

         // read and process "logging" section
         if (configDataSource.hasSection(CFG_SECTION_LOGGING) &&
             configDataSource.readSection(CFG_SECTION_LOGGING,
                                        kvpLoggingSettings)) {
            if (kvpLoggingSettings.hasKey(CFG_LOGFILE_ACCESS)) {
               String accessLog =
                  kvpLoggingSettings.getValue(CFG_LOGFILE_ACCESS);
               m_accessLogFile = accessLog;
               Logger.info("access log=" + accessLog);
            }

         if (kvpLoggingSettings.hasKey(CFG_LOGFILE_ERROR)) {
            String errorLog =
               kvpLoggingSettings.getValue(CFG_LOGFILE_ERROR);
				m_errorLogFile = errorLog;
            Logger.info("error log=" + errorLog);
         }
      }

      // read and process "server" section
      if (configDataSource.hasSection(CFG_SECTION_SERVER) &&
          configDataSource.readSection(CFG_SECTION_SERVER,
                                        kvpServerSettings)) {
         
         if (kvpServerSettings.hasKey(CFG_SERVER_PORT)) {
            int portNumber =
               getIntValue(kvpServerSettings, CFG_SERVER_PORT);

            if (portNumber > 0) {
               port = portNumber;
               m_serverPort = portNumber;
               
               if (isLoggingDebug) {
                  Logger.debug("port number=" + port);
               }
            }
         }

         // defaults
         m_isThreaded = true;
         m_threading = CFG_THREADING_PTHREADS;
         m_threadPoolSize = 4;
         
         if (kvpServerSettings.hasKey(CFG_SERVER_THREADING)) {
            String threading =
               kvpServerSettings.getValue(CFG_SERVER_THREADING);
            if (!threading.isEmpty()) {
               if ((threading.equals(CFG_THREADING_PTHREADS)) ||
                   (threading.equals(CFG_THREADING_CPP11)) ||
                   (threading.equals(CFG_THREADING_GCD_LIBDISPATCH))) {
                  m_threading = threading;
                  m_isThreaded = true;
               } else if (threading.equals(CFG_THREADING_NONE)) {
                  m_isThreaded = false;
               }
            }
         }
         
         if (kvpServerSettings.hasKey(CFG_SERVER_THREAD_POOL_SIZE)) {
            int poolSize =
               getIntValue(kvpServerSettings, CFG_SERVER_THREAD_POOL_SIZE);

            if (poolSize > 0) {
               m_threadPoolSize = poolSize;
            }
         }
         
         // defaults
         m_sockets = CFG_SOCKETS_SOCKET_SERVER;
         
         if (kvpServerSettings.hasKey(CFG_SERVER_SOCKETS)) {
            String sockets =
               kvpServerSettings.getValue(CFG_SERVER_SOCKETS);
            if (sockets.equals(CFG_SOCKETS_KERNEL_EVENTS)) {
               m_isUsingKernelEventServer = true;
               m_sockets = CFG_SOCKETS_KERNEL_EVENTS;
            }
         }

         if (kvpServerSettings.hasKey(CFG_SERVER_LOG_LEVEL)) {
            m_logLevel =
               kvpServerSettings.getValue(CFG_SERVER_LOG_LEVEL);
            if (!m_logLevel.isEmpty()) {
               m_logLevel = m_logLevel.toLowerCase();
               Logger.info("log level: " + m_logLevel);
               Logger logger = Logger.getLogger();
               
               if (logger != null) {
                  if (m_logLevel.equals(CFG_LOGGING_CRITICAL)) {
                     logger.setLogLevel(Logger.LogLevel.Critical);
                  } else if (m_logLevel.equals(CFG_LOGGING_ERROR)) {
                     logger.setLogLevel(Logger.LogLevel.Error);
                  } else if (m_logLevel.equals(CFG_LOGGING_WARNING)) {
                     logger.setLogLevel(Logger.LogLevel.Warning);
                  } else if (m_logLevel.equals(CFG_LOGGING_INFO)) {
                     logger.setLogLevel(Logger.LogLevel.Info);
                  } else if (m_logLevel.equals(CFG_LOGGING_DEBUG)) {
                     logger.setLogLevel(Logger.LogLevel.Debug);
                  } else if (m_logLevel.equals(CFG_LOGGING_VERBOSE)) {
                     logger.setLogLevel(Logger.LogLevel.Verbose);
                  } else {
                     Logger.warning("unrecognized log level: '" + m_logLevel);
                  }
               }
            }
         }

         if (kvpServerSettings.hasKey(CFG_SERVER_SEND_BUFFER_SIZE)) {
            int buffSize =
               getIntValue(kvpServerSettings, CFG_SERVER_SEND_BUFFER_SIZE);

            if (buffSize > 0) {
               m_socketSendBufferSize = buffSize;
            }
         }

         if (kvpServerSettings.hasKey(CFG_SERVER_RECEIVE_BUFFER_SIZE)) {
            int buffSize =
               getIntValue(kvpServerSettings, CFG_SERVER_RECEIVE_BUFFER_SIZE);

            if (buffSize > 0) {
               m_socketReceiveBufferSize = buffSize;
            }
         }
         
         m_allowBuiltInHandlers = hasTrueValue(kvpServerSettings,
                                               CFG_SERVER_ALLOW_BUILTIN_HANDLERS);
         
         if (kvpServerSettings.hasKey(CFG_SERVER_STRING)) {
            String serverString =
               kvpServerSettings.getValue(CFG_SERVER_STRING);
            if (!serverString.isEmpty()) {
               m_serverString = serverString;

               int posDollar = serverString.indexOf("$");
               if (posDollar > -1) {
                  KeyValuePairs kvpVars = new KeyValuePairs();
                  kvpVars.addPair("$PRODUCT_NAME", SERVER_NAME);
                  kvpVars.addPair("$PRODUCT_VERSION", SERVER_VERSION);
                  kvpVars.addPair("$CFG_SOCKETS", m_sockets);
                  kvpVars.addPair("$CFG_THREADING", m_threading);
                  
                  int posDollarOS = serverString.indexOf("$OS_");
                  
                  if (posDollarOS > -1) {
                     SystemInfo systemInfo = new SystemInfo();
                     if (systemInfo.retrievedSystemInfo()) {
                        kvpVars.addPair("$OS_SYSNAME", systemInfo.sysName());
                        kvpVars.addPair("$OS_NODENAME", systemInfo.nodeName());
                        kvpVars.addPair("$OS_RELEASE", systemInfo.release());
                        kvpVars.addPair("$OS_VERSION", systemInfo.version());
                        kvpVars.addPair("$OS_MACHINE", systemInfo.machine());
                     } else {
                        Logger.warning("unable to retrieve system information to populate server string");
                     }
                  }
                  
                  replaceVariables(kvpVars, m_serverString);
               }
               
               Logger.info("setting server string: '" + m_serverString + "'");
            }
         }
      }

      m_startupTime = getLocalDateTime();

      if (m_allowBuiltInHandlers) {
         Logger.debug("adding built-in handlers");
         addBuiltInHandlers();
      }
      
      if (isLoggingDebug) {
         Logger.debug("processing handlers");
      }

      // read and process "handlers" section
      KeyValuePairs kvpHandlers = new KeyValuePairs();
      if (configDataSource.hasSection(CFG_SECTION_HANDLERS) &&
          configDataSource.readSection(CFG_SECTION_HANDLERS, kvpHandlers)) {
         List<String> vecKeys = kvpHandlers.getKeys();

         for (String path : vecKeys) {
            String moduleSection = kvpHandlers.getValue(path);

            if (isLoggingDebug) {
               Logger.debug("path='" + path + "'");
            }
            
            if (moduleSection.isEmpty()) {
               Logger.warning("nothing specified for path " + path);
               Logger.warning("Not servicing this path");
               continue;
            }

            if (configDataSource.hasSection(moduleSection)) {
               KeyValuePairs kvpModule = new KeyValuePairs();
               if (configDataSource.readSection(moduleSection, kvpModule)) {
                  if (!kvpModule.hasKey(MODULE_DLL_NAME)) {
                     Logger.error(MODULE_DLL_NAME +
                                   " not specified for module " +
                                   moduleSection);
                  }

                  String dllName = kvpModule.getValue(MODULE_DLL_NAME);
                  HttpHandler handler = null;
                  
                  if (isLoggingDebug) {
                     Logger.debug("trying to load dynamic library='" +
                                   dllName +
                                   "'");
                  }
                  
                  /*
                  //TODO: implement dynamic loading of path handlers
                  
                  DynamicLibrary dll(dllName);

                  // load the dll
                  try {
                     void* pfn = dll.resolve("CreateHandler");
                     if (pfn == null) {
                        Logger.error("unable to find module library entry point");
                     } else {
                        if (isLoggingDebug) {
                           Logger.debug("dynamic library loaded");
                        }
                     }
                     dll.close();

                     PFN_CREATE_HANDLER pfnCreateHandler = (PFN_CREATE_HANDLER) pfn;
                     pHandler = (*pfnCreateHandler)();
                  } catch (Exception e) {
                     Logger.error("exception caught trying to load module library " +
                                   dllName);
                     Logger.error(e.getMessage());
                  } catch (Throwable t) {
                     Logger.error("unable to load module library " +
                                   dllName);
                  }
                  */

                  // continue loading application specific parameters for the module
                  List<String> vecModuleKeys = kvpModule.getKeys();

                  KeyValuePairs kvpApp = new KeyValuePairs();

                  for (String moduleKey : vecModuleKeys) {
                     // starts with app prefix?
                     if (moduleKey.startsWith(APP_PREFIX)) {
                        if (moduleKey.length() > APP_PREFIX_LEN) {
                           kvpApp.addPair(moduleKey.substring(APP_PREFIX_LEN),
                                          kvpModule.getValue(moduleKey));
                        }
                     }
                  }

                  if (isLoggingDebug) {
                     Logger.debug("initializing the handler");
                  }

                  // now initialize the servlet
                  if ((handler != null) && handler.init(path, kvpApp)) {
                     if (isLoggingDebug) {
                        Logger.debug("initialization succeeded");
							}
                     
                     // register it
                     if (!addPathHandler(path, handler)) {
                        Logger.error("unable to register handler for path " +
                                      path);
                        
                        if (m_requireAllHandlersForStartup) {
                           return false;
                        }
                     }
                  } else {
                     Logger.error("unable to initialize handler for path " +
                                   path);
                     if (m_requireAllHandlersForStartup) {
                        return false;
                     }
                  }
               }
            } else {
               if (!moduleSection.isEmpty()) {
                  Logger.error("no configuration for handler " +
                                moduleSection);
               } else {
                  Logger.error("no configuration for handler " +
                                path);
               }

               if (m_requireAllHandlersForStartup) {
                  return false;
               }
            }
         }

         // do we have any handlers?
         if (!m_allowBuiltInHandlers && m_mapPathHandlers.isEmpty()) {
            Logger.critical("no handlers registered");
            return false;
         }
      }
   } catch (Exception e) {
      Logger.critical("exception initializing server: " + e.getMessage());
      return false;
   } catch (Throwable t) {
      Logger.critical("unknown exception initializing server");
      return false;
   }

      if (!m_isUsingKernelEventServer) {
         try {
            if (isLoggingDebug) {
               Logger.debug("creating server socket on port=" + port);
            }
      
            m_serverSocket = new ServerSocket(port);
         } catch (Throwable t) {
            String msg = "unable to open server socket port '" +
                    port + "'";
            Logger.critical(msg);
            return false;
         }
      }

      String concurrencyModel = "";

      if (m_isThreaded) {
         boolean isUsingLibDispatch = false;
         m_threadingFactory = new DefaultThreadingFactory();
         ThreadingFactory.setThreadingFactory(m_threadingFactory);
         m_threadPool =
            m_threadingFactory.createThreadPoolDispatcher(m_threadPoolSize,
                                                           "thread_pool");
         
         m_threadPool.start();

         concurrencyModel = "multithreaded - ";
         concurrencyModel += m_threading;
      
         if (!isUsingLibDispatch) {
            concurrencyModel += "[";
            concurrencyModel += m_threadPoolSize;
            concurrencyModel += " threads]";
         }
      } else {
         concurrencyModel = "serial";
         m_threadPoolSize = 1;   // not a pool, we have 1 processing thread
      }

      m_concurrencyModel = concurrencyModel;

      String portAsString = "" + port;

      String startupMsg = SERVER_NAME;
      startupMsg += " ";
      startupMsg += SERVER_VERSION;
      startupMsg += " listening on port ";
      startupMsg += portAsString;
      startupMsg += " (request concurrency: ";
      startupMsg += concurrencyModel;
      startupMsg += ")";
      startupMsg += " (sockets: ";
      startupMsg += m_sockets;
      startupMsg += ")";

      System.out.println(startupMsg);

      m_isFullyInitialized = true;
   
      return true;
   }
   
   /**
    * Adds built-in handlers
    * @return boolean indicating whether the built-in handlers were successfully added
    */
   protected boolean addBuiltInHandlers() {
      return addPathHandler("/Echo", new EchoHandler()) &&
          addPathHandler("/GMTDateTime", new GMTDateTimeHandler()) &&
          addPathHandler("/ServerDateTime", new ServerDateTimeHandler()) &&
          addPathHandler("/ServerObjectsDebugging", new ServerObjectsDebugging()) &&
          addPathHandler("/ServerStats", new ServerStatsHandler()) &&
          addPathHandler("/ServerStatus", new ServerStatusHandler());
   }
   
}
