<%-- 
    Document   : upload
    Created on : 08/12/2015, 10:56:31
    Author     : Filipe
--%>

<%@page import="java.nio.charset.Charset"%>
<%@page import="org.apache.commons.io.IOUtils"%>
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
	    String inputDescVideo = request.getParameter("descriptionVideo");
out.println("1");
	    if(inputName == null || inputDescVideo == null){
		if(inputName == null)
		    out.println("2.1");
		if(inputDescVideo == null)
		    out.println("2.2");
	    }
	    //else 
	    //if(inputName.length() == 0){
		//System.out.println("3");
	    //}
	    //else
	    {
out.println("antesdo4");
		org.amazonaws.mytube.MyTube_Service service = new org.amazonaws.mytube.MyTube_Service();
out.println("antesdo4.2");
		org.amazonaws.mytube.MyTube port = service.getMyTubePort();
		// TODO initialize WS operation arguments here
out.println("4");
		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory(); // sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(1024*1024*3); // 3mb
		factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // sets temporary location to store files
out.println("5");
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024*1024*40); // sets maximum size of upload file
		upload.setSizeMax(1024*1024*50); // sets maximum size of request (include file + form data)
out.println("6");
		try
		{
		    List<FileItem> formItems = upload.parseRequest(request); // parses the request's content to extract file data
out.println("7");
		    if (formItems != null && formItems.size() > 0) // iterates over form's fields
		    {out.println("8");
			for (FileItem item : formItems) // processes only fields that are not form fields
			{out.println("9");
			    if (!item.isFormField())
			    {out.println("10");
				String fileName = item.getName();
				File file = File.createTempFile("aws-java-sdk-", ".txt");
out.println("11");
				// saves the file on disk
				item.write(file);
				
				byte[] byteFile = IOUtils.toByteArray(new FileInputStream(file));
				String stringFile = new String(byteFile, Charset.defaultCharset());
out.println("12");				
				// TODO process result here
				java.lang.String result = port.uploadFileAsString(stringFile);
out.println("13");				
				out.println("Result = " + result);
out.println("14");
			    }
			}
		    }
		} catch (Exception ex) {
		    out.println("There was an error: " + ex.getMessage());
		    ex.printStackTrace();
		}
		
	    }
	} catch (Exception ex) {
	    // TODO handle custom exceptions here
	    out.println("Deu Ruim.");
	    ex.printStackTrace();
	}
	%>
	<br>
	<br>
	<button onclick="location.href='index.jsp'">Home</button>
    </body>
</html>
