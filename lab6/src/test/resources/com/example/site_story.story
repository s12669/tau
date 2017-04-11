Scenario:  Page refuses to log the user in when entering incorrect password

Given user is on logging page
When user enters incorrect password
Then the user should not log in


Scenario:  Page behaves as expected when trying to log in with correct password

Given user is on logging page
When user enters correct password
Then the user should log in

Scenario:  User enters the page again after logging in

Given user is logged in
When user enters the logging page
Then the user should already be logged in



