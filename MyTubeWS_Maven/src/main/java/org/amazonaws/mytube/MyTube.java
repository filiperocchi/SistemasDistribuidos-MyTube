/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amazonaws.mytube;

import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.ListObjectsRequest;
//import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.servlet.http.Part;

/**
 *
 * @author Filipe
 */
@WebService(serviceName = "MyTube")
@Stateless()
public class MyTube {

	AmazonS3 s3 = new AmazonS3Client();

	String bucketName = "darkspock";

	/**
	 * This is a sample web service operation
	 */
	@WebMethod(operationName = "hello")
	public String hello(@WebParam(name = "name") String txt) {
		return "Hello " + txt + " !";
	}

	/**
	 * Operação de Web service
	 *
	 * @param name
	 * @param file
	 * @param description
	 * @return
	 * @throws java.io.IOException
	 */
	@WebMethod(operationName = "Upload")
	public String Upload(@WebParam(name = "name") String name, @WebParam(name = "file") Object file, @WebParam(name = "description") String description) throws IOException {
		try{
			System.out.println("Uploading a new object to S3 from a file\n");
			s3.putObject(new PutObjectRequest(bucketName, name, (File) file));
		
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
				  + "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
				  + "a serious internal problem while trying to communicate with S3, "
				  + "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "File uploaded sucessfully.";
		
	}
	
	private static File createFile(String fileString) throws IOException {
		
		File file = File.createTempFile("aws-java-sdk-", ".txt");
		FileOutputStream out = new FileOutputStream(file);
		
		out.write(fileString.getBytes(), 0, fileString.length());
		
//		out.write('#');
//		out.write('#');
//		out.write('\n');
//		out.write(description.getBytes(), 0, description.length());
		
		return file;
	}

	/**
	 * Operação de Web service
	 *
	 * @param key
	 * @throws java.io.IOException
	 * @return
	 */
	@WebMethod(operationName = "Download")
	public String Download(@WebParam(name = "key") String key) throws IOException {
		try{
			
			System.out.println("Downloading an object");
			S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
			System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
			String response = displayTextInputStream(object.getObjectContent());
			return response;
		
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
				  + "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
				  + "a serious internal problem while trying to communicate with S3, "
				  + "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return null;
	}
	
	private static String displayTextInputStream(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String response="";
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			response = response+"    " + line+"\n";
		}
		return response;
	}

	
	
	@WebMethod(operationName = "uploadOnlyFile")
	public String uploadOnlyFile(@WebParam(name = "file") Object file) {
		try{
			System.out.println("Uploading a new object to S3 from a file\n");
			s3.putObject(new PutObjectRequest(bucketName, "TESTE111", (File) file));
		
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
				  + "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
				  + "a serious internal problem while trying to communicate with S3, "
				  + "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "File uploaded sucessfully.";
	}

	/**
	 * Operação de Web service
	 */
	@WebMethod(operationName = "uploadFileAsString")
	public String uploadFileAsString(@WebParam(name = "fileString") String fileString) {
		try{
			System.out.println("Uploading a new object to S3 from a file\n");
			
			File file;
			
			try{
				s3.putObject(new PutObjectRequest(bucketName, "", createFile(fileString)));
			}
			catch(IOException e) {
				System.out.println("IOEXCEPTION ON LINE 191");
			}
		
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
				  + "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
				  + "a serious internal problem while trying to communicate with S3, "
				  + "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		
		return "File uploaded sucessfully.";
	}
}
