/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.mock.MockSocket;
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
   private String method;
   private String url;
   private String protocol;
   private String host;
   private String userAgent;
   private String acceptMimeTypes;
   private String acceptLanguage;
   private String acceptEncoding;
   private String request;
   private MockSocket socket;
   private HttpRequest instance;
   private String requestLine;
   
   public HttpRequestTest() {
      method = "GET";
      url = "/docs/index.html";
      protocol = "HTTP/1.1";
      requestLine = method + " " + url + " " + protocol;
      host = "www.nowhere123.com";
      acceptMimeTypes = "image/gif, image/jpeg, */*";
      acceptLanguage = "en-us";
      acceptEncoding = "gzip, deflate";
      userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1";
   }
   
   @BeforeClass
   public static void setUpClass() {
   }
   
   @AfterClass
   public static void tearDownClass() {
   }
   
   @Before
   public void setUp() throws Exception {
      request = construct(method, null);
      socket = new MockSocket("127.0.0.1", 123);
      socket.setDataToRead(request);
      socket.open();
      instance = new HttpRequest(socket);
   }
   
   @After
   public void tearDown() {
      request = null;
      socket = null;
      instance = null;
   }
   
   public String construct(String verb, String body) {
      StringBuilder req = new StringBuilder();
      // GET /docs/index.html HTTP/1.1
      req.append(verb);
      req.append(" ");
      req.append(url);
      req.append(" ");
      req.append(protocol);
      req.append("\n");
      
      // Host: www.nowhere123.com
      req.append("Host: ");
      req.append(host);
      req.append("\n");
      
      // Accept: image/gif, image/jpeg, */*
      req.append("Accept: ");
      req.append(acceptMimeTypes);
      req.append("\n");
      
      // Accept-Language: en-us
      req.append("Accept-Language: ");
      req.append(acceptLanguage);
      req.append("\n");
      
      // Accept-Encoding: gzip, deflate
      req.append("Accept-Encoding: ");
      req.append(acceptEncoding);
      req.append("\n");

      // User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1
      req.append("User-Agent: ");
      req.append(userAgent);
      req.append("\n");
      
      req.append("Content-Length: ");
      if ((body != null) && !body.isEmpty()) {
         req.append(body.length());
      } else {
         req.append(0);
      }
      req.append("\n");
      
      req.append("\n");
      
      if ((body != null) && !body.isEmpty()) {
         req.append(body);
      }
      
      return req.toString();
   }

   /**
    * Test of streamFromSocket method, of class HttpRequest.
    */
   @Test
   public void testStreamFromSocket() {
      System.out.println("streamFromSocket");
      assertEquals(method, instance.getMethod());
   }

   /**
    * Test of isInitialized method, of class HttpRequest.
    */
   @Test
   public void testIsInitialized() {
      System.out.println("isInitialized");
      assertTrue(instance.isInitialized());
   }

   /**
    * Test of getRequest method, of class HttpRequest.
    */
   @Test
   public void testGetRequest() {
      System.out.println("getRequest");
      assertEquals(requestLine, instance.getRequest());
   }

   /**
    * Test of getMethod method, of class HttpRequest.
    */
   @Test
   public void testGetMethod() {
      System.out.println("getMethod");
      assertEquals("GET", instance.getMethod());
   }

   /**
    * Test of getPath method, of class HttpRequest.
    */
   @Test
   public void testGetPath() {
      System.out.println("getPath");
      assertEquals(url, instance.getPath());
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
      assertTrue(instance.hasAccept());
   }

   /**
    * Test of hasAcceptEncoding method, of class HttpRequest.
    */
   @Test
   public void testHasAcceptEncoding() {
      System.out.println("hasAcceptEncoding");
      assertTrue(instance.hasAcceptEncoding());
   }

   /**
    * Test of hasAcceptLanguage method, of class HttpRequest.
    */
   @Test
   public void testHasAcceptLanguage() {
      System.out.println("hasAcceptLanguage");
      assertTrue(instance.hasAcceptLanguage());
   }

   /**
    * Test of hasConnection method, of class HttpRequest.
    */
   @Test
   public void testHasConnection() {
      System.out.println("hasConnection");
      assertFalse(instance.hasConnection());
   }

   /**
    * Test of hasDNT method, of class HttpRequest.
    */
   @Test
   public void testHasDNT() {
      System.out.println("hasDNT");
      assertFalse(instance.hasDNT());
   }

   /**
    * Test of hasHost method, of class HttpRequest.
    */
   @Test
   public void testHasHost() {
      System.out.println("hasHost");
      assertTrue(instance.hasHost());
   }

   /**
    * Test of hasUserAgent method, of class HttpRequest.
    */
   @Test
   public void testHasUserAgent() {
      System.out.println("hasUserAgent");
      assertTrue(instance.hasUserAgent());
   }

   /**
    * Test of getAccept method, of class HttpRequest.
    */
   @Test
   public void testGetAccept() {
      System.out.println("getAccept");
      assertEquals(this.acceptMimeTypes, instance.getAccept());
   }

   /**
    * Test of getAcceptEncoding method, of class HttpRequest.
    */
   @Test
   public void testGetAcceptEncoding() {
      System.out.println("getAcceptEncoding");
      assertEquals(this.acceptEncoding, instance.getAcceptEncoding());
   }

   /**
    * Test of getAcceptLanguage method, of class HttpRequest.
    */
   @Test
   public void testGetAcceptLanguage() {
      System.out.println("getAcceptLanguage");
      assertEquals(this.acceptLanguage, instance.getAcceptLanguage());
   }

   /**
    * Test of getConnection method, of class HttpRequest.
    */
   @Test
   public void testGetConnection() {
      System.out.println("getConnection");
      assertNull(instance.getConnection());
   }

   /**
    * Test of getDNT method, of class HttpRequest.
    */
   @Test
   public void testGetDNT() {
      System.out.println("getDNT");
      assertNull(instance.getDNT());
   }

   /**
    * Test of getHost method, of class HttpRequest.
    */
   @Test
   public void testGetHost() {
      System.out.println("getHost");
      assertEquals(host, instance.getHost());
   }

   /**
    * Test of getUserAgent method, of class HttpRequest.
    */
   @Test
   public void testGetUserAgent() {
      System.out.println("getUserAgent");
      assertEquals(userAgent, instance.getUserAgent());
   }

   /**
    * Test of parseBody method, of class HttpRequest.
    */
   @Test
   public void testParseBody() {
      System.out.println("parseBody");
      instance.parseBody();
   }
   
}
