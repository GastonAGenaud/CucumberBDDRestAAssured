Feature: Operatios about user

  Scenario: Create user
    Given Get Call to "url"
    Then I send a POST request to "/user" and request json:
            """
            {
                "id": 10,
                "username": "theUser",
                "firstName": "John",
                "lastName": "James",
                "email": "john@email.com",
                "password": "12345",
                "phone": "12345",
                "userStatus": 1
            }
            """

  Scenario: Creates list of users with given input array
    Given Get Call to "url"
    Then I send a POST request to "/user/createWithList" and request json:
            """
            [
                {
                    "id": 10,
                    "username": "theUser",
                    "firstName": "John",
                    "lastName": "James",
                    "email": "john@email.com",
                    "password": "12345",
                    "phone": "12345",
                    "userStatus": 1
                }
            ]
            """

  Scenario: Logs user into the system
    Given Get Call to "url"
    Then I send a GET request to "user/login"


  Scenario: Logs out current logged in user session
    Given Get Call to "url"
    When I send a GET request to "/user/logout"

  Scenario: Get user by user name
    Given Get Call to "url"
    When I send a GET request to "/user/theUser" with query parameter "username"

  Scenario: update user
    Given Get Call to "url"
    When I send a PUT request to "/user/theUser" and request json:
            """
            {
                "id": 10,
                "username": "theUser",
                "firstName": "John",
                "lastName": "James",
                "email": "john@email.com",
                "password": "12345",
                "phone": "12345",
                "userStatus": 1
            }
            """

  Scenario: Delete user
    Given Get Call to "url"
    When I send a PUT request to "/user/user1" with query parameter "username"