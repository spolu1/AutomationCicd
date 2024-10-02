
@tag
Feature: Purchase the order from the Ecommerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce Page
	
  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given Login  with username <name> and password <password>
    When I add product  <ProductName> to cart
    And Checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDR." message is displayed on Confirmation Page

    Examples: 
      | name                  | password    | ProductName  |
      | sridharpolu@gmail.com |    Sree@123 | ZARA COAT 3  |
      
