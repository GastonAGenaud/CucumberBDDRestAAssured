Feature: Access to Petstore orders

  Scenario: Returns pet inventories by status
    Given Get Call to "url"
    Then I send a GET request to "/store/inventory"

  Scenario: Place an order for a pet
    Given Get Call to "url"
    Then I send a POST request to "/store/order" and request json:
            """
            {
                "id": 10,
                "petId": 198772,
                "quantity": 7,
                "shipDate": "2022-08-02T19:38:29.170Z",
                "status": "approved",
                "complete": true
            }
            """

  Scenario: Find purchase order by ID
    Given Get Call to "url"
    Then I send a GET request to "/store/order/10" with query parameter "10"

  Scenario: Delete purchase order by ID
    Given Get Call to "url"
    Then I send a DELETE request to "/store/order/1001"