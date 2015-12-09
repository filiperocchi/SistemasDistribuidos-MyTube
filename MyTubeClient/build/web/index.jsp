<%-- 
    Document   : index
    Created on : 07/12/2015, 00:47:24
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTube</title>

	<link rel="shortcut icon" href="http://i.imgur.com/kSaAQGi.png"/>
	
    </head>
    <body>
	<h1>My Tube - Video Storage</h1>

	<form method="POST" name="Upload" action="upload.jsp" enctype="multipart/form-data" >
	    <h3>Upload</h3>
	    Name: <input type="text" name="uploadName">
	    <br>
	    <br>
	    File: <input type="file" name="videoFile" id="videoFile">
	    <br>
	    <br>
	    Description: <input type="text" name="descriptionVideo">
	    <br>
	    <br>
	    <input type="submit" value="Upload">
	</form>
		
	<br>
	<br>
	<br>
	<br>
	<h3>Download</h3>
	<form method="POST" name="Download" action="download.jsp" enctype="multipart/form-data">
	    Name: <input type="text" name="downloadName">
	    <input type="submit" value="Download">
	</form>
		
    </body>
</html>
