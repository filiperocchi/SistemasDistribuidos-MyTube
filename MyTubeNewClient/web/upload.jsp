<%-- 
    Document   : upload
    Created on : 08/12/2015, 10:56:31
    Author     : Filipe
--%>

<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTube - Upload</title>
	
	<link rel="shortcut icon" href="http://i.imgur.com/kSaAQGi.png"/>
    </head>
    <body>
        <h1>Result of Upload</h1>
	
	<%
	try {
	    String inputName = request.getParameter("uploadName");
	    String inputVideoFile = request.getParameter("videoFile");
	    String inputDescVideo = request.getParameter("descriptionVideo");
	    
	    if(inputName == null || inputVideoFile == null || inputDescVideo == null){
		
	    }
	    else if(inputName.length() == 0 ||
		    inputVideoFile.length()== 0){
		
	    }
	    else{
		org.amazonaws.mytube.MyTube_Service service = new org.amazonaws.mytube.MyTube_Service();
		org.amazonaws.mytube.MyTube port = service.getMyTubePort();
		// TODO initialize WS operation arguments here

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory(); // sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(1024*1024*3); // 3mb
		factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // sets temporary location to store files

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024*1024*40); // sets maximum size of upload file
		upload.setSizeMax(1024*1024*50); // sets maximum size of request (include file + form data)

		try
		{
		    List<FileItem> formItems = upload.parseRequest(request); // parses the request's content to extract file data

		    if (formItems != null && formItems.size() > 0) // iterates over form's fields
		    {
			for (FileItem item : formItems) // processes only fields that are not form fields
			{
			    if (!item.isFormField())
			    {
				String fileName = item.getName();
				File file = File.createTempFile("aws-java-sdk-", ".txt");

				// saves the file on disk
				item.write(file);
				
				// TODO process result here
				java.lang.String result = port.upload(inputName, file, inputDescVideo);
				
				out.println("Result = " + result);
			    }
			}
		    }
		} catch (Exception ex) {
		    out.println("There was an error: " + ex.getMessage());
		}
		
	    }
	} catch (Exception ex) {
	    // TODO handle custom exceptions here
	    out.println("Deu Ruim.");
	}
	%>
	<br>
	<br>
	<button onclick="location.href='index.jsp'">Home</button>
    </body>
</html>
