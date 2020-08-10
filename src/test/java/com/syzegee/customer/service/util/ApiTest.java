package com.syzegee.customer.service.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApiTest {
    public static void main(String[] args) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response;
        String query = "{\n" +
                "customer(id:\"1\"){\n" +
                "customerId \n" +
                "userId\n" +
                "customerCode\n" +
                "tierCode\n" +
                "statusCode\n" +
                "organizationId{\n" +
                "organizationId\n" +
                "organizationName\n" +
                "organizationDesc\n" +
                "}\n" +
                "customerAddresses{\n" +
                "addressLine1 \n" +
                "addressLine2 \n" +
                "city\n" +
                "  }\n" +
                "  customerBenefits{\n" +
                "  benefitCode\n" +
                "  }\n" +
                "  customerUsers{\n" +
                "  customerContactInfo\n" +
                "  email\n" +
                "  firstName\n" +
                "  lastName\n" +
                "  }\n" +
                "  domainDetails{\n" +
                "  siteCode\n" +
                "  templateCode\n" +
                "  siteName\n" +
                "  domainName\n" +
                "  }\n" +
                "  customerRoles{\n" +
                "  customerRoleType\n" +
                "  }\n" +
                "  }\n" +
                "}";

        HttpPost request = new HttpPost(
                "http://localhost:8088/api/v1/customers");
        StringEntity body = new StringEntity(query);
        request.setEntity(body);
        request.addHeader("Content-Type", "text/plain");
        response = httpClient.execute(request);
        System.out.println("response ..."+response.getStatusLine().getStatusCode());
        ResponseHandler<String> handler = new BasicResponseHandler();
        System.out.println(new JSONObject(handler.handleResponse(response)));
    }

}
