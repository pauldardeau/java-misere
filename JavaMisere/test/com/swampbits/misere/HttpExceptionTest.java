/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

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
public class HttpExceptionTest {
   
   public HttpExceptionTest() {
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
    * Test of getStatusCode method, of class HttpException.
    */
   @Test
   public void testGetStatusCode() {
      System.out.println("getStatusCode");
      HttpException e = new HttpException(403, "forbidden");
      assertEquals(403, e.getStatusCode());
   }
   
   @Test
   public void testGetReasonPhrase() {
      System.out.println("getReasonPhrase");
      String reason = "humpty dumpty had a great fall";
      HttpException e = new HttpException(500, reason);
      assertEquals(reason, e.getMessage());
   }
   
}
