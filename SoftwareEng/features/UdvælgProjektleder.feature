#s204501
Feature: Udvælg projektleder
Description: Bruger udvælger en projektleder
Actor: Bruger


Scenario: Main scenario
Given bruger "USE" er logget ind
And en bruger med initialerne "HJE" findes
When brugeren tilføjer brugeren "HJE" som projektleder
Then en projektleder med initialerne "HJE" findes


Scenario: tilføj en projektleder til systemet, men brugeren findes ikke
When brugeren tilføjer brugeren "Hej" som projektleder
Then en projektleder er oprettet
Then en fejlbesked "Brugeren skal være oprettet i systemet" er givet