/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swampbits.misere;

/**
 *
 * @author paul
 */
public class HttpException extends Exception {

   private int m_statusCode = 0;
   
   
   /**
    * Constructs and HttpException using the given status code and reason
    * @param statusCode the HTTP status code
    * @param reasonPhrase textual reason for exception
    */
   public HttpException(int statusCode, String reasonPhrase) {
      super(reasonPhrase);
      m_statusCode = statusCode;
   }
   
   /**
    * Retrieves the HTTP status code
    * @return the HTTP status code
    */
   public int getStatusCode() {
      return m_statusCode;
   }
   
}
