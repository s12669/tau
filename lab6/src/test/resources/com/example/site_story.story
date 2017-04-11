Scenario:  Page behaves as expected when trying to log in with correct password

Given user is on logging page
When user enters correct password
Then the user should log in

Scenario:  Page refuses to log the user in when entering incorrect password

Given user is on logging page
When user enters incorrect password
Then the user should not log in

