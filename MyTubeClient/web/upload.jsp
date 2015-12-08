<%-- 
    Document   : upload
    Created on : 08/12/2015, 10:56:31
    Author     : Filipe
--%>

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
	<button onclick="location.href='index.jsp'">Home</button>
    </body>
</html>
