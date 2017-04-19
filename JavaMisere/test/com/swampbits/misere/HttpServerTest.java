/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.KeyValuePairs;
import com.swampbits.chaudiere.SectionedConfigDataSource;
import com.swampbits.chaudiere.SocketRequest;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paul
 */
public class HttpServerTest {
   
   public HttpServerTest() {
   }
   
   @BeforeClass
   public static void setUpClass() {
   }
   
   @AfterClass
   public static void tearDownClass() {
   }
   
   @Before
   public void setUp() {
   }
   
   @After
   public void tearDown() {
   }

   /**
    * Test of getSystemDateGMT method, of class HttpServer.
    */
   @Test
   public void testGetSystemDateGMT() {
      System.out.println("getSystemDateGMT");
      HttpServer instance = null;
      String expResult = "";
      String result = instance.getSystemDateGMT();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getLocalDateTime method, of class HttpServer.
    */
   @Test
   public void testGetLocalDateTime() {
      System.out.println("getLocalDateTime");
      HttpServer instance = null;
      String expResult = "";
      String result = instance.getLocalDateTime();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of buildHeader method, of class HttpServer.
    */
   @Test
   public void testBuildHeader() {
      System.out.println("buildHeader");
      String responseCode = "";
      Map<String, String> mapHeaders = null;
      HttpServer instance = null;
      String expResult = "";
      String result = instance.buildHeader(responseCode, mapHeaders);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of addPathHandler method, of class HttpServer.
    */
   @Test
   public void testAddPathHandler() {
      System.out.println("addPathHandler");
      String path = "";
      HttpHandler handler = null;
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.addPathHandler(path, handler);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of removePathHandler method, of class HttpServer.
    */
   @Test
   public void testRemovePathHandler() {
      System.out.println("removePathHandler");
      String path = "";
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.removePathHandler(path);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getPathHandler method, of class HttpServer.
    */
   @Test
   public void testGetPathHandler() {
      System.out.println("getPathHandler");
      String path = "";
      HttpServer instance = null;
      HttpHandler expResult = null;
      HttpHandler result = instance.getPathHandler(path);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of runSocketServer method, of class HttpServer.
    */
   @Test
   public void testRunSocketServer() {
      System.out.println("runSocketServer");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.runSocketServer();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of runKernelEventServer method, of class HttpServer.
    */
   @Test
   public void testRunKernelEventServer() {
      System.out.println("runKernelEventServer");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.runKernelEventServer();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of run method, of class HttpServer.
    */
   @Test
   public void testRun() {
      System.out.println("run");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.run();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of logRequest method, of class HttpServer.
    */
   @Test
   public void testLogRequest_3args() {
      System.out.println("logRequest");
      String clientIPAddress = "";
      String requestLine = "";
      String responseCode = "";
      HttpServer instance = null;
      instance.logRequest(clientIPAddress, requestLine, responseCode);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of logRequest method, of class HttpServer.
    */
   @Test
   public void testLogRequest_4args() {
      System.out.println("logRequest");
      String clientIPAddress = "";
      String requestLine = "";
      String responseCode = "";
      String threadWorkerId = "";
      HttpServer instance = null;
      instance.logRequest(clientIPAddress, requestLine, responseCode, threadWorkerId);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getConfigDataSource method, of class HttpServer.
    */
   @Test
   public void testGetConfigDataSource() throws Exception {
      System.out.println("getConfigDataSource");
      HttpServer instance = null;
      SectionedConfigDataSource expResult = null;
      SectionedConfigDataSource result = instance.getConfigDataSource();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getSocketSendBufferSize method, of class HttpServer.
    */
   @Test
   public void testGetSocketSendBufferSize() {
      System.out.println("getSocketSendBufferSize");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.getSocketSendBufferSize();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getSocketReceiveBufferSize method, of class HttpServer.
    */
   @Test
   public void testGetSocketReceiveBufferSize() {
      System.out.println("getSocketReceiveBufferSize");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.getSocketReceiveBufferSize();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getServerId method, of class HttpServer.
    */
   @Test
   public void testGetServerId() {
      System.out.println("getServerId");
      HttpServer instance = null;
      String expResult = "";
      String result = instance.getServerId();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of serviceSocket method, of class HttpServer.
    */
   @Test
   public void testServiceSocket() {
      System.out.println("serviceSocket");
      SocketRequest socketRequest = null;
      HttpServer instance = null;
      instance.serviceSocket(socketRequest);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasTrueValue method, of class HttpServer.
    */
   @Test
   public void testHasTrueValue() {
      System.out.println("hasTrueValue");
      KeyValuePairs kvp = null;
      String setting = "";
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.hasTrueValue(kvp, setting);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getIntValue method, of class HttpServer.
    */
   @Test
   public void testGetIntValue() {
      System.out.println("getIntValue");
      KeyValuePairs kvp = null;
      String setting = "";
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.getIntValue(kvp, setting);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of replaceVariables method, of class HttpServer.
    */
   @Test
   public void testReplaceVariables() {
      System.out.println("replaceVariables");
      KeyValuePairs kvp = null;
      String s = "";
      HttpServer instance = null;
      instance.replaceVariables(kvp, s);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of compressResponse method, of class HttpServer.
    */
   @Test
   public void testCompressResponse() {
      System.out.println("compressResponse");
      String mimeType = "";
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.compressResponse(mimeType);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of compressionEnabled method, of class HttpServer.
    */
   @Test
   public void testCompressionEnabled() {
      System.out.println("compressionEnabled");
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.compressionEnabled();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of minimumCompressionSize method, of class HttpServer.
    */
   @Test
   public void testMinimumCompressionSize() {
      System.out.println("minimumCompressionSize");
      HttpServer instance = null;
      int expResult = 0;
      int result = instance.minimumCompressionSize();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of init method, of class HttpServer.
    */
   @Test
   public void testInit() {
      System.out.println("init");
      int port = 0;
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.init(port);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of addBuiltInHandlers method, of class HttpServer.
    */
   @Test
   public void testAddBuiltInHandlers() {
      System.out.println("addBuiltInHandlers");
      HttpServer instance = null;
      boolean expResult = false;
      boolean result = instance.addBuiltInHandlers();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
