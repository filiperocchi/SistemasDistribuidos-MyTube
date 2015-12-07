<%-- 
    Document   : index
    Created on : 06/12/2015, 23:21:53
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MyTube</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="http://i.imgur.com/kSaAQGi.png"/>
	<script language="javascript">
	    var debug = true;
	    
	    function initialize(){
		service.useService("http://localhost:8080/MyTube/MyTube?WSDL", "Mytube");
	    }
	    
	    function upload(){
		var uploadFile, file, description;
		uploadFile = document.getElementById("uploadName").value;
		file = document.getElementById("videoFile").value;
		description = document.getElementById("descriptionVideo").value;
		service.Mytube.callService("Upload", uploadFile, file, description);
	    }
	    
	    function download(){
		if(debug) alert("download");
		var key;
		if(debug) alert("getting downloadName");
		key = document.getElementById("downloadName").value;
		if(debug) alert("calling service");
		service.Mytube.callService("MyTube/Download", key);
		if(debug) alert("alert");
	    }
	    
	    function showResult(){
		if(debug) alert("showResult");
		alert(event.result.value);
	    }
	</script>
	
    </head>
    
    <body id="service" onload="initialize()" onresult="showResult()">
        <h1>My Tube - Video Storage</h1>
	   
	<form name="Upload">
	    <h3>Upload</h3>
	    Name: <input type="text" id="uploadName">
	    <br>
	    <br>
	    File: <input type="file" id="videoFile">
	    <br>
	    <br>
	    Description: <input type="text" id="descriptionVideo">
	    <br>
	    <br>
	    <button onclick="upload()">Upload</button>
	</form>
	
	<br>
	<br>
	<br>
	<br>
	<h3>Download</h3>
	<form name="Download">
	    Name: <input type="text" id="downloadName">
	    <button onclick="download()">Download</button>
	</form>
	
    </body>
</html>
