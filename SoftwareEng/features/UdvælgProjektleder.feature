Feature: Udvælg projektleder
Description: Bruger udvælger en projektleder
Actor: Bruger


Scenario: Main scenario
Given en bruger med initialerne “ABC” findes
When admin tilføjer brugeren “ABC” som projektleder
Then en projektleder er oprettet
Then en projektleder med initialerne “ABC” findes


#Scenario: tilføj en projektleder til systemet, men brugeren findes ikke
#When admin tilføjer brugeren “ABC” som projektleder
#Then en projektleder er oprettet
#Then en fejlbesked “Brugeren skal være oprettet i systemet” er givet