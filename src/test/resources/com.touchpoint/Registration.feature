# language: en
  Feature: Registration process

    @smoke
    Scenario: Registration without skipping steps
      Given LoginPage page is open
      And clicked on "startFreeTrialLink" element
      Then SignUp page is open
      When "random" value of "20" characters is entered into "firstName" field
      And "random" value of "20" characters is entered into "lastName" field
      * "random" value of "20" characters is entered into "lastName" field
      * "testEmail" value of "-" characters is entered into "email" field
      * "testPassword" value of "-" characters is entered into "password" field
      * in "country" list is selected as "Cyprus"
      * clicked on "startFreeTrialBtn" element
      Then EmailConfirmation page is open
      When confirmation code entered


