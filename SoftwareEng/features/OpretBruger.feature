Feature: Opret bruger
Description: Bruger opretter en bruger
Actor: Bruger


Scenario: Tilføj bruger til systemet
Given bruger "SIL" er logget ind
When bruger laver en bruger med initialerne "ABC"
Then brugeren med initialerne "ABC" findes

Scenario: Bruger opretter en bruger med allerede eksisterende initialer
Given bruger "HJD" er logget ind
And der eksisterer en bruger med intialerne "ABC"
When bruger opretter en bruger med initialerne "ABC"
Then fås fejlmeddelsen "Disse intialer er allerede taget"