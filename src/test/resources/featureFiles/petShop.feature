Feature: Operations about Pets

  Scenario: Add a new pet to the store
    Given Get Call to "url"
    Then I send a POST request to "/pet" and request json:
            """
            {
                "id": 10,
                "name": "doggie",
                "category": {
                    "id": 1,
                    "name": "Dogs"
                },
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "string"
                    }
                ],
                "status": "available"
            }
            """

  Scenario: Finds Pets by status
    Given Get Call to "url"
    Then I send a GET request to "/pet/findByStatus" with query parameter "status=pending"

  Scenario: Finds Pets by tags
    Given Get Call to "url"
    Then I send a GET request to "/pet/findByTags" with query parameter "tags=string"

      #10 is the {petId}
  Scenario: Finds Pets by ID
    Given Get Call to "url"
    Then I send a GET request to "/pet/10" with query parameter "10"

  Scenario: Updates a pet in the store with form data
    Given Get Call to "url"
    Then I send a POST request to "/pet/10" with query parameters "doggie", "available"

  Scenario: Deletes a pet
    Given Get Call to "url"
    Then I send a DELETE request to "/pet/100" with query parameters "api_key", "100"

  Scenario: Upload an image
    Given Get Call to "url"
    Then I send a POST request to "/pet/10/uploadImage"