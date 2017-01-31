/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.Socket;
import com.swampbits.chaudiere.mock.MockSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class HttpTransactionTest {
   
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
   private HttpTransaction instance;
   private String requestLine;

   public HttpTransactionTest() {
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
      instance = new HttpTransaction();
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
    * Test of streamFromSocket method, of class HttpTransaction.
    */
   @Test
   public void testStreamFromSocket() throws Exception {
      System.out.println("streamFromSocket");
      assertTrue(instance.streamFromSocket(socket));
   }

   /**
    * Test of getRawHeader method, of class HttpTransaction.
    */
   @Test
   public void testGetRawHeader() throws Exception {
      System.out.println("getRawHeader");
      instance.streamFromSocket(socket);
      String rawHeader = instance.getRawHeader();
      assertNotNull(rawHeader);
      assertFalse(rawHeader.isEmpty());
      assertTrue(rawHeader.contains(userAgent));
   }

   /**
    * Test of getBody method, of class HttpTransaction.
    */
   @Test
   public void testGetBody() throws Exception {
      System.out.println("getBody");
      instance.streamFromSocket(socket);
      assertNull(instance.getBody());
   }

   /**
    * Test of setBody method, of class HttpTransaction.
    */
   @Test
   public void testSetBody() {
      System.out.println("setBody");
      String body = "Hello world";
      instance.setBody(body);
      assertEquals(body, instance.getBody());
   }

   /**
    * Test of hasHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testHasHeaderValue() throws Exception {
      System.out.println("hasHeaderValue");
      instance.streamFromSocket(socket);
      assertTrue(instance.hasHeaderValue(HTTP.HTTP_ACCEPT_LANGUAGE));
      assertFalse(instance.hasHeaderValue("sdfkj"));
   }

   /**
    * Test of getHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testGetHeaderValue() throws Exception {
      System.out.println("getHeaderValue");
      instance.streamFromSocket(socket);
      assertEquals(acceptLanguage, instance.getHeaderValue(HTTP.HTTP_ACCEPT_LANGUAGE));
      assertNull(instance.getHeaderValue("sdfkj"));
   }

   /**
    * Test of getHeaderKeys method, of class HttpTransaction.
    */
   @Test
   public void testGetHeaderKeys() throws Exception {
      System.out.println("getHeaderKeys");
      instance.streamFromSocket(socket);
      List<String> headerKeys = new ArrayList<>();
      instance.getHeaderKeys(headerKeys);
      assertFalse(headerKeys.isEmpty());
      assertTrue(headerKeys.contains(HTTP.HTTP_ACCEPT_LANGUAGE.toLowerCase()));
      assertTrue(headerKeys.contains(HTTP.HTTP_USER_AGENT.toLowerCase()));
   }

   /**
    * Test of setHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testSetHeaderValue() {
      System.out.println("setHeaderValue");
      String myUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2)";
      instance.setHeaderValue(HTTP.HTTP_USER_AGENT, myUserAgent);
      assertTrue(instance.hasHeaderValue(HTTP.HTTP_USER_AGENT));
      assertEquals(myUserAgent, instance.getHeaderValue(HTTP.HTTP_USER_AGENT));
   }

   /**
    * Test of getProtocol method, of class HttpTransaction.
    */
   @Test
   public void testGetProtocol() throws Exception {
      System.out.println("getProtocol");
      instance.streamFromSocket(socket);
      assertEquals(protocol, instance.getProtocol());
   }

   /**
    * Test of getRequestMethod method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestMethod() throws Exception {
      System.out.println("getRequestMethod");
      instance.streamFromSocket(socket);
      assertEquals(method, instance.getRequestMethod());
   }

   /**
    * Test of getRequestPath method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestPath() throws Exception {
      System.out.println("getRequestPath");
      instance.streamFromSocket(socket);
      assertEquals(url, instance.getRequestPath());
   }

   /**
    * Test of getRequestLine method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestLine() throws Exception {
      System.out.println("getRequestLine");
      instance.streamFromSocket(socket);
      assertEquals(requestLine, instance.getRequestLine());
   }

   /**
    * Test of populateWithHeaders method, of class HttpTransaction.
    */
   @Test
   public void testPopulateWithHeaders() throws Exception {
      System.out.println("populateWithHeaders");
      HashMap<String, String> hashHeaders = new HashMap<>();
      instance.streamFromSocket(socket);
      instance.populateWithHeaders(hashHeaders);
      assertFalse(hashHeaders.isEmpty());
      String lowerUserAgent = HTTP.HTTP_USER_AGENT.toLowerCase();
      assertTrue(hashHeaders.containsKey(lowerUserAgent));
      assertEquals(userAgent, hashHeaders.get(lowerUserAgent));
   }

   /**
    * Test of getRequestLineValues method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestLineValues() throws Exception {
      System.out.println("getRequestLineValues");
      instance.streamFromSocket(socket);
      List<String> reqLineValues = instance.getRequestLineValues();
      assertNotNull(reqLineValues);
      assertEquals(3, reqLineValues.size());
      assertEquals(method, reqLineValues.get(0));
      assertEquals(url, reqLineValues.get(1));
      assertEquals(protocol, reqLineValues.get(2));
   }

   /**
    * Test of setProtocol method, of class HttpTransaction.
    */
   @Test
   public void testSetProtocol() {
      System.out.println("setProtocol");
      protocol = HTTP.HTTP_PROTOCOL1_0;
      instance.setProtocol(protocol);
      assertNotNull(instance.getProtocol());
      assertEquals(protocol, instance.getProtocol());
      protocol = HTTP.HTTP_PROTOCOL1_1;
      instance.setProtocol(protocol);
      assertNotNull(instance.getProtocol());
      assertEquals(protocol, instance.getProtocol());
   }

   /**
    * Test of parseHeaders method, of class HttpTransaction.
    */
   @Test
   public void testParseHeaders() throws Exception {
      System.out.println("parseHeaders");
      instance.streamFromSocket(socket);
   }
   
}
