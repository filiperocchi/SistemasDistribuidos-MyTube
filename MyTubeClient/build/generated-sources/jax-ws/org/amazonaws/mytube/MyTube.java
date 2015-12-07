
package org.amazonaws.mytube;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyTube", targetNamespace = "http://mytube.amazonaws.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyTube {


    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.HelloResponse")
    @Action(input = "http://mytube.amazonaws.org/MyTube/helloRequest", output = "http://mytube.amazonaws.org/MyTube/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

    /**
     * 
     * @param key
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "Download")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Download", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.Download")
    @ResponseWrapper(localName = "DownloadResponse", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.DownloadResponse")
    @Action(input = "http://mytube.amazonaws.org/MyTube/DownloadRequest", output = "http://mytube.amazonaws.org/MyTube/DownloadResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://mytube.amazonaws.org/MyTube/Download/Fault/IOException")
    })
    public String download(
        @WebParam(name = "key", targetNamespace = "")
        String key)
        throws IOException_Exception
    ;

    /**
     * 
     * @param file
     * @param name
     * @param description
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "Upload")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Upload", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.Upload")
    @ResponseWrapper(localName = "UploadResponse", targetNamespace = "http://mytube.amazonaws.org/", className = "org.amazonaws.mytube.UploadResponse")
    @Action(input = "http://mytube.amazonaws.org/MyTube/UploadRequest", output = "http://mytube.amazonaws.org/MyTube/UploadResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://mytube.amazonaws.org/MyTube/Upload/Fault/IOException")
    })
    public String upload(
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "file", targetNamespace = "")
        String file,
        @WebParam(name = "description", targetNamespace = "")
        String description)
        throws IOException_Exception
    ;

}
