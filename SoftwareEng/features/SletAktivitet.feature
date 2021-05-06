Feature: Slet aktivitet
Description: Bruger sletter en aktivitet fra listen
 
Actor: Projektleder
Scenario: Slet aktivitet 
Given brugeren "NIE" er projektleder
And Projektet "Projekt" eksisterer
And aktiviteten "Planlægning" horer under projektet "Projekt"
When brugeren sletter aktiviteten "Planlægning" fra projektet "Projekt"
Then aktiviteten "Planlægning" fjernes fra projektet "Projekt"


Scenario: Forsog pa at slette aktivitet fra tomt projekt
Given brugeren "NIE" er projektleder
And Projektet "Projekt" eksisterer
And Projektet "Projekt" er tomt
When brugeren vælger projektet "Projekt"
Then fås fejlmeddelsen "Projektet er tomt"
