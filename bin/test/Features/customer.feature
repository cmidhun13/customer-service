Feature: Testing customer REST API

  #GetcustomerByIdWithWrongQuery
  Scenario: Get customer details by id  fails when wrong query is provided
    Given I am user and entering customer_query
    When wrong query provided
    Then Call get api with givens details
    And Return client error as wrong query in payload get request

 #GetcustomerById
  Scenario: Get customer Success
    Given Enter customer_query in payload
    When Get customer customer_query is valid
    Then Get the customer with given details

