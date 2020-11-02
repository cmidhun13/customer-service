package com.syzegee.customer.service.bdd.stepdefinition;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;

import com.syzegee.customer.service.controller.CustomerController;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import  cucumber.api.java.en.*;

public class CustomerControllerAcceptanceTest    {
    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String payload;
    private String validQuery;
    private String corelationId;
    private HttpResponse response;

    @Given("^I am user and entering customer_query$")
    public void i_am_user_and_entering_wrong_customer_query()  {
        payload = "{"+
                "customer(){" +
                "customerId " +
                "userId " +
                "customerCode " +
                "tierCode " +
                "statusCode " +
                "" +
                "}" +
                "" +
                "}";

    }

    @When("^wrong query provided$")
    public void wrong_query_provided()  {
        validQuery = payload;
        corelationId="da20c6cc-508e-4ec7-a912-d14930502d64";
    }



    @Then("^Call get api with givens details$")
    public void call_get_api_with_givens_details() throws Throwable {
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(post("/api/v1/customers")
                .withRequestBody(containing(validQuery))
                .withHeader("correlationId", equalTo(corelationId))
                .willReturn(aResponse().withStatus(HttpStatus.BAD_REQUEST.value()).withStatusMessage(
                        "Invalid Syntax")
                ));
        HttpPost request = new HttpPost(
                "http://localhost:" + wireMockServer.port() +"/api/v1/customers");
        StringEntity body = new StringEntity(payload);
        request.addHeader("correlationId",corelationId);
        request.setEntity(body);
        response = httpClient.execute(request);
    }

    @Then("^Return client error as wrong query in payload get request$")
    public void return_client_error_Qurey_validation_error_in_get_request() {
        assertEquals("Invalid Syntax",
                response.getStatusLine().getReasonPhrase());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusLine().getStatusCode());
        wireMockServer.stop();
    }

    @Given("^Enter customer_query in payload$")
    public void i_am_user_and_entering_customer_query() {
        payload ="{" +
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
    }
    @When("^Get customer customer_query is valid$")
    public void get_customer_customer_query_is_valid() {
        validQuery =payload;
        corelationId="da20c6cc-508e-4ec7-a912-d14930502d64";
    }
    @Then("^Get the customer with given details$")
    public void get_the_Customer_with_given_details() throws Throwable {
        // JSONArray memberJsonArray = new JSONArray();
        // memberJsonArray.put(CommonUtilTest.getMemeberJson());
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(post("/api/v1/customers")
                .withRequestBody(containing(validQuery))
                .withHeader("correlationId", equalTo(corelationId))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value())));
        HttpPost request = new HttpPost(
                "http://localhost:" + wireMockServer.port() +"/api/v1/customers");
        StringEntity body = new StringEntity(payload);
        request.setEntity(body);
        request.addHeader("correlationId",corelationId);
        response = httpClient.execute(request);
        System.out.println("response>>>   "+ response.getStatusLine().getStatusCode());
        assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
        wireMockServer.stop();
    }
    public static ResponseDefinitionBuilder aResponse() {
        return new ResponseDefinitionBuilder();
    }
}