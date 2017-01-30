/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

import com.swampbits.chaudiere.Socket;
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
public class HttpResponseTest {
   
   public HttpResponseTest() {
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
    * Test of streamFromSocket method, of class HttpResponse.
    */
   @Test
   public void testStreamFromSocket() throws Exception {
      System.out.println("streamFromSocket");
      Socket socket = null;
      HttpResponse instance = new HttpResponse();
      boolean expResult = false;
      boolean result = instance.streamFromSocket(socket);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getStatusCode method, of class HttpResponse.
    */
   @Test
   public void testGetStatusCode() {
      System.out.println("getStatusCode");
      HttpResponse instance = new HttpResponse();
      int expResult = 0;
      int result = instance.getStatusCode();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setStatusCode method, of class HttpResponse.
    */
   @Test
   public void testSetStatusCode() {
      System.out.println("setStatusCode");
      int statusCode = 0;
      HttpResponse instance = new HttpResponse();
      instance.setStatusCode(statusCode);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getReasonPhrase method, of class HttpResponse.
    */
   @Test
   public void testGetReasonPhrase() {
      System.out.println("getReasonPhrase");
      HttpResponse instance = new HttpResponse();
      String expResult = "";
      String result = instance.getReasonPhrase();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasContentEncoding method, of class HttpResponse.
    */
   @Test
   public void testHasContentEncoding() {
      System.out.println("hasContentEncoding");
      HttpResponse instance = new HttpResponse();
      boolean expResult = false;
      boolean result = instance.hasContentEncoding();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of hasContentType method, of class HttpResponse.
    */
   @Test
   public void testHasContentType() {
      System.out.println("hasContentType");
      HttpResponse instance = new HttpResponse();
      boolean expResult = false;
      boolean result = instance.hasContentType();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getContentEncoding method, of class HttpResponse.
    */
   @Test
   public void testGetContentEncoding() {
      System.out.println("getContentEncoding");
      HttpResponse instance = new HttpResponse();
      String expResult = "";
      String result = instance.getContentEncoding();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getContentType method, of class HttpResponse.
    */
   @Test
   public void testGetContentType() {
      System.out.println("getContentType");
      HttpResponse instance = new HttpResponse();
      String expResult = "";
      String result = instance.getContentType();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setContentEncoding method, of class HttpResponse.
    */
   @Test
   public void testSetContentEncoding() {
      System.out.println("setContentEncoding");
      String contentEncoding = "";
      HttpResponse instance = new HttpResponse();
      instance.setContentEncoding(contentEncoding);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setContentType method, of class HttpResponse.
    */
   @Test
   public void testSetContentType() {
      System.out.println("setContentType");
      String contentType = "";
      HttpResponse instance = new HttpResponse();
      instance.setContentType(contentType);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}
