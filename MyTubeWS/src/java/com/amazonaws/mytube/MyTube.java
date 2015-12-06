/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amazonaws.mytube;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Filipe
 */
@WebService(serviceName = "MyTube")
@Stateless()
public class MyTube {

	/**
	 * This is a sample web service operation
	 */
	@WebMethod(operationName = "hello")
	public String hello(@WebParam(name = "name") String txt) {
		return "Hello " + txt + " !";
	}

	/**
	 * Operação de Web service
	 */
	@WebMethod(operationName = "upload")
	public String upload(@WebParam(name = "name") String name, @WebParam(name = "file") String file) throws IOException {
		//TODO write your implementation code here:
		return null;
	}

	/**
	 * Operação de Web service
	 */
	@WebMethod(operationName = "Download")
	public String Download(@WebParam(name = "key") String key) throws IOException {
		//TODO write your implementation code here:
		return null;
	}
}
