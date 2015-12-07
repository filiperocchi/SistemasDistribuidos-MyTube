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

	<form name="Upload" action="index.jsp">
	    <h3>Upload</h3>
	    Name: <input type="text" name="uploadName">
	    <br>
	    <br>
	    File: <input type="text" name="videoFile">
	    <br>
	    <br>
	    Description: <input type="text" name="descriptionVideo">
	    <br>
	    <br>
	    <input type="submit" value="Upload">
	</form>
	
	<%
	try {
	    String inputName = request.getParameter("uploadName");
	    String inputVideoFile = request.getParameter("videoFile");
	    String inputDescVideo = request.getParameter("descriptionVideo");
	    if(inputName == null || inputVideoFile == null || inputDescVideo == null){
		
	    }
	    else if(inputName.length() == 0 ||
		    inputVideoFile.length() == 0){
		
	    }
	    else{
		org.amazonaws.mytube.MyTube_Service service = new org.amazonaws.mytube.MyTube_Service();
		org.amazonaws.mytube.MyTube port = service.getMyTubePort();
		// TODO initialize WS operation arguments here
		java.lang.String name = inputName;
		java.lang.String file = inputVideoFile;
		java.lang.String description = inputDescVideo;
		// TODO process result here
		java.lang.String result = port.upload(name, file, description);
		out.println("Result = " + result);
	    }
	} catch (Exception ex) {
	    // TODO handle custom exceptions here
	    out.println("Deu Ruim aqui tbm.");
	} 
	%>
		
	<br>
	<br>
	<br>
	<br>
	<h3>Download</h3>
	<form name="Download" action="index.jsp">
	    Name: <input type="text" name="downloadName">
	    <input type="submit" value="Download">
	</form>

	<% try {
	    String inputKey = request.getParameter("downloadName");
	    if(inputKey == null){
		
	    }
	    else if(inputKey.length() == 0){
		
	    }
	    else{
		org.amazonaws.mytube.MyTube_Service service = new org.amazonaws.mytube.MyTube_Service();
		org.amazonaws.mytube.MyTube port = service.getMyTubePort();
		// TODO initialize WS operation arguments here
		//java.lang.String key = request.getParameter("downloadName");
		java.lang.String key = inputKey;
		// TODO process result here
		java.lang.String result = port.download(key);
		out.println("Result = " + result);
	    }
	} catch (Exception ex) {
	    // TODO handle custom exceptions here
	    out.println("Deu Ruim.");
	} %>
		
    </body>
</html>
