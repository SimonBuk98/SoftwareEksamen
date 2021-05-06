Feature: Opret Projekt
Description: Admin opretter et projekt
Actor: Bruger
Scenario: Bruger opretter et projekt
Given bruger "UYH" er logget ind
When bruger opretter et projekt med navnet "Projekt"
Then et projekt "Projekt" er oprettet
Then et projekt med navnet "Projekt" og et projektnummer eksisterer


Scenario: Bruger opretter et projekt men er ikke logget ind
Given bruger er ikke logget ind
When bruger opretter et projekt med navnet "Projekt"
Then fås fejlmeddelsen "Bruger skal være logget ind"



