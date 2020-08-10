$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/Features/customer.feature");
formatter.feature({
  "name": "Testing customer REST API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Get customer details by id  fails when wrong query is provided",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I am user and entering customer_query",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "wrong query provided",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Call get api with givens details",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Return client error as wrong query in payload get request",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Get customer Success",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Enter customer_query in payload",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Get customer customer_query is valid",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Get the customer with given details",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("src/test/resources/Features/customerAddress.feature");
formatter.feature({
  "name": "Testing customerAddress REST API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Get customer Address details by id  fails when wrong query is provided",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I am user and entering customer_address_query",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "wrong customer_address_query provided",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Call get api with givens customer_address_query details",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Return client error as wrong customer_address_query in payload get request",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Get customer Success",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Enter customer_address_query in payload",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Get customer customer_address_query is valid",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Get the customer address with given customer_address_query details",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});