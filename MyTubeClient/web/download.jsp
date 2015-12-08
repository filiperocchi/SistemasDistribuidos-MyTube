<%-- 
    Document   : download
    Created on : 08/12/2015, 10:59:30
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTube - Download</title>
	
	<link rel="shortcut icon" href="http://i.imgur.com/kSaAQGi.png"/>
    </head>
    <body>
        <h1>Downloaded File:</h1>
	<br>
	
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
	<br>
	<br>
	<button onclick="location.href='index.jsp'">Home</button>
    </body>
</html>
