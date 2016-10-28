Feature: Digital River Contact Information

  Scenario: verify the phone number is displayed on the contact page
    Given the website "https://www.digitalriver.com" is opened
    When clicking on "Contact Us"
    Then the phone number "+1 (800) 598-7450" is visible
