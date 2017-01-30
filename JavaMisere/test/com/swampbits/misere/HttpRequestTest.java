/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.Socket;
import java.util.List;
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
public class HttpRequestTest {
   
   public HttpRequestTest() {
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
    * Test of streamFromSocket method, of class HttpRequest.
    */
   @Test
   public void testStreamFromSocket() throws Exception {
      System.out.println("streamFromSocket");
      Socket socket = null;
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.streamFromSocket(socket);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of isInitialized method, of class HttpRequest.
    */
   @Test
   public void testIsInitialized() {
      System.out.println("isInitialized");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.isInitialized();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRequest method, of class HttpRequest.
    */
   @Test
   public void testGetRequest() {
      System.out.println("getRequest");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getRequest();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getMethod method, of class HttpRequest.
    */
   @Test
   public void testGetMethod() {
      System.out.println("getMethod");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getMethod();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getPath method, of class HttpRequest.
    */
   @Test
   public void testGetPath() {
      System.out.println("getPath");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getPath();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasArgument method, of class HttpRequest.
    */
   @Test
   public void testHasArgument() {
      System.out.println("hasArgument");
      String key = "";
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasArgument(key);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getArgument method, of class HttpRequest.
    */
   @Test
   public void testGetArgument() {
      System.out.println("getArgument");
      String key = "";
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getArgument(key);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getArgumentKeys method, of class HttpRequest.
    */
   @Test
   public void testGetArgumentKeys() {
      System.out.println("getArgumentKeys");
      HttpRequest instance = null;
      List<String> expResult = null;
      List<String> result = instance.getArgumentKeys();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasAccept method, of class HttpRequest.
    */
   @Test
   public void testHasAccept() {
      System.out.println("hasAccept");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasAccept();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasAcceptEncoding method, of class HttpRequest.
    */
   @Test
   public void testHasAcceptEncoding() {
      System.out.println("hasAcceptEncoding");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasAcceptEncoding();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasAcceptLanguage method, of class HttpRequest.
    */
   @Test
   public void testHasAcceptLanguage() {
      System.out.println("hasAcceptLanguage");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasAcceptLanguage();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasConnection method, of class HttpRequest.
    */
   @Test
   public void testHasConnection() {
      System.out.println("hasConnection");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasConnection();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasDNT method, of class HttpRequest.
    */
   @Test
   public void testHasDNT() {
      System.out.println("hasDNT");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasDNT();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasHost method, of class HttpRequest.
    */
   @Test
   public void testHasHost() {
      System.out.println("hasHost");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasHost();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasUserAgent method, of class HttpRequest.
    */
   @Test
   public void testHasUserAgent() {
      System.out.println("hasUserAgent");
      HttpRequest instance = null;
      boolean expResult = false;
      boolean result = instance.hasUserAgent();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAccept method, of class HttpRequest.
    */
   @Test
   public void testGetAccept() {
      System.out.println("getAccept");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getAccept();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAcceptEncoding method, of class HttpRequest.
    */
   @Test
   public void testGetAcceptEncoding() {
      System.out.println("getAcceptEncoding");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getAcceptEncoding();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAcceptLanguage method, of class HttpRequest.
    */
   @Test
   public void testGetAcceptLanguage() {
      System.out.println("getAcceptLanguage");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getAcceptLanguage();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getConnection method, of class HttpRequest.
    */
   @Test
   public void testGetConnection() {
      System.out.println("getConnection");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getConnection();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getDNT method, of class HttpRequest.
    */
   @Test
   public void testGetDNT() {
      System.out.println("getDNT");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getDNT();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getHost method, of class HttpRequest.
    */
   @Test
   public void testGetHost() {
      System.out.println("getHost");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getHost();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getUserAgent method, of class HttpRequest.
    */
   @Test
   public void testGetUserAgent() {
      System.out.println("getUserAgent");
      HttpRequest instance = null;
      String expResult = "";
      String result = instance.getUserAgent();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of parseBody method, of class HttpRequest.
    */
   @Test
   public void testParseBody() {
      System.out.println("parseBody");
      HttpRequest instance = null;
      instance.parseBody();
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
