Feature: Slet aktivitet
Description: Bruger sletter en aktivitet fra listen
 
Actor: Projektleder
Scenario: Slet aktivitet 
And brugeren "NIE" er projektleder
And Projektet "Projekt" 1461 eksisterer
And aktiviteten "Planlægning" horer under projektet 1461
When brugeren sletter aktiviteten "Planlægning" fra projektet "Projekt" 1461
Then aktiviteten "Planlægning" fjernes fra projektet "Projekt" 1461


Scenario: Forsog pa at slette aktivitet fra tomt projekt
Given brugeren "NIE" er projektleder
And Projektet "Projekt" 1461 eksisterer
And Projektet "Projekt" 1461 er tomt
When brugeren vælger projektet "Projekt" 1461
Then fås fejlmeddelsen "Projektet er tomt"
