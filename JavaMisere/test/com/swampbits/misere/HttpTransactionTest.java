/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.Socket;
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
   
   public HttpTransactionTest() {
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
    * Test of streamFromSocket method, of class HttpTransaction.
    */
   @Test
   public void testStreamFromSocket() throws Exception {
      System.out.println("streamFromSocket");
      Socket socket = null;
      HttpTransaction instance = new HttpTransaction();
      boolean expResult = false;
      boolean result = instance.streamFromSocket(socket);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRawHeader method, of class HttpTransaction.
    */
   @Test
   public void testGetRawHeader() {
      System.out.println("getRawHeader");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getRawHeader();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getBody method, of class HttpTransaction.
    */
   @Test
   public void testGetBody() {
      System.out.println("getBody");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getBody();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setBody method, of class HttpTransaction.
    */
   @Test
   public void testSetBody() {
      System.out.println("setBody");
      String body = "";
      HttpTransaction instance = new HttpTransaction();
      instance.setBody(body);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testHasHeaderValue() {
      System.out.println("hasHeaderValue");
      String headerKey = "";
      HttpTransaction instance = new HttpTransaction();
      boolean expResult = false;
      boolean result = instance.hasHeaderValue(headerKey);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testGetHeaderValue() {
      System.out.println("getHeaderValue");
      String headerKey = "";
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getHeaderValue(headerKey);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getHeaderKeys method, of class HttpTransaction.
    */
   @Test
   public void testGetHeaderKeys() {
      System.out.println("getHeaderKeys");
      List<String> headerKeys = null;
      HttpTransaction instance = new HttpTransaction();
      instance.getHeaderKeys(headerKeys);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setHeaderValue method, of class HttpTransaction.
    */
   @Test
   public void testSetHeaderValue() {
      System.out.println("setHeaderValue");
      String key = "";
      String value = "";
      HttpTransaction instance = new HttpTransaction();
      instance.setHeaderValue(key, value);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getProtocol method, of class HttpTransaction.
    */
   @Test
   public void testGetProtocol() {
      System.out.println("getProtocol");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getProtocol();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRequestMethod method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestMethod() {
      System.out.println("getRequestMethod");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getRequestMethod();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRequestPath method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestPath() {
      System.out.println("getRequestPath");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getRequestPath();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRequestLine method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestLine() {
      System.out.println("getRequestLine");
      HttpTransaction instance = new HttpTransaction();
      String expResult = "";
      String result = instance.getRequestLine();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of populateWithHeaders method, of class HttpTransaction.
    */
   @Test
   public void testPopulateWithHeaders() {
      System.out.println("populateWithHeaders");
      Map<String, String> hashTable = null;
      HttpTransaction instance = new HttpTransaction();
      instance.populateWithHeaders(hashTable);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getRequestLineValues method, of class HttpTransaction.
    */
   @Test
   public void testGetRequestLineValues() {
      System.out.println("getRequestLineValues");
      HttpTransaction instance = new HttpTransaction();
      List<String> expResult = null;
      List<String> result = instance.getRequestLineValues();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setProtocol method, of class HttpTransaction.
    */
   @Test
   public void testSetProtocol() {
      System.out.println("setProtocol");
      String protocol = "";
      HttpTransaction instance = new HttpTransaction();
      instance.setProtocol(protocol);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of parseHeaders method, of class HttpTransaction.
    */
   @Test
   public void testParseHeaders() {
      System.out.println("parseHeaders");
      HttpTransaction instance = new HttpTransaction();
      boolean expResult = false;
      boolean result = instance.parseHeaders();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
