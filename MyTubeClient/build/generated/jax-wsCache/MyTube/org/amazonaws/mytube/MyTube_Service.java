
package org.amazonaws.mytube;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyTube", targetNamespace = "http://mytube.amazonaws.org/", wsdlLocation = "http://localhost:8080/MyTube/MyTube?wsdl")
public class MyTube_Service
    extends Service
{

    private final static URL MYTUBE_WSDL_LOCATION;
    private final static WebServiceException MYTUBE_EXCEPTION;
    private final static QName MYTUBE_QNAME = new QName("http://mytube.amazonaws.org/", "MyTube");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/MyTube/MyTube?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYTUBE_WSDL_LOCATION = url;
        MYTUBE_EXCEPTION = e;
    }

    public MyTube_Service() {
        super(__getWsdlLocation(), MYTUBE_QNAME);
    }

    public MyTube_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYTUBE_QNAME, features);
    }

    public MyTube_Service(URL wsdlLocation) {
        super(wsdlLocation, MYTUBE_QNAME);
    }

    public MyTube_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYTUBE_QNAME, features);
    }

    public MyTube_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyTube_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MyTube
     */
    @WebEndpoint(name = "MyTubePort")
    public MyTube getMyTubePort() {
        return super.getPort(new QName("http://mytube.amazonaws.org/", "MyTubePort"), MyTube.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MyTube
     */
    @WebEndpoint(name = "MyTubePort")
    public MyTube getMyTubePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://mytube.amazonaws.org/", "MyTubePort"), MyTube.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYTUBE_EXCEPTION!= null) {
            throw MYTUBE_EXCEPTION;
        }
        return MYTUBE_WSDL_LOCATION;
    }

}
