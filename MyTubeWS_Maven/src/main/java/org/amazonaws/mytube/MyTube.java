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
import javax.servlet.http.Part;

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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.logging.Level;

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
	public String Upload(@WebParam(name = "name") String name, @WebParam(name = "file") Part file, @WebParam(name = "description") String description) throws IOException {
		
		try{
			System.out.println("Uploading a new object to S3 from a file\n");
			s3.putObject(new PutObjectRequest(bucketName, name, createFile(file, description)));
		
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
	
	private File createFile(Part video, String description) throws IOException {
		
		File file = File.createTempFile("aws-java-sdk-", ".txt");
		file.deleteOnExit();
		
		String fileName = getFileName(video);
		
		OutputStream out = null;
		InputStream filecontent = null;
		try{
			filecontent = video.getInputStream();
			out = new FileOutputStream(file);
			
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.write('#');
			out.write('#');
			out.write('\n');
			
			out.write(description.getBytes(), 0, description.length());
			
		}
		catch (IOException IOe) {
			System.out.println("Exception caught in createFile(): "+IOe.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
		return file;
	}
	
	private String getFileName(Part part) {
		
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
    return null;
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
}
