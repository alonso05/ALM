package qc.rest.examples;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import qc.rest.examples.infrastructure.Constants;
import qc.rest.examples.infrastructure.RestConnector;
import qc.rest.examples.xml.ReadXMLFileDOM;

public class TestInstances {

    public static void main(String[] args) throws Exception {

        RestConnector con =
                RestConnector.getInstance().init(
                        new HashMap<String, String>(),
                        Constants.HOST,
                        Constants.PORT,
                        Constants.DOMAIN,
                        Constants.PROJECT);


        AuthenticateLoginLogoutExample login = new AuthenticateLoginLogoutExample();
        login.login(Constants.USERNAME, Constants.PASSWORD);
        String testInstanceUrl = con.buildEntityCollectionUrl("test-instance");
        System.out.println(testInstanceUrl);
        
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Accept", "application/xml");
        String cycle = "query={assign-rcyc[1491]}";
        String responseStr = con.httpGet(testInstanceUrl, cycle, requestHeaders).toString();
        
        ReadXMLFileDOM readerXML = new ReadXMLFileDOM();
        readerXML.getInstance(responseStr);

       System.out.println("end");
	}

}
