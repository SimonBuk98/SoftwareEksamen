Feature: Tidsregistrering
Description: Bruger registrerer tid

Actor: Bruger
Scenario: Bruger registrerer tid på tildelt aktivitet
Given bruger "ABC" er logget ind
And Projektet "projekt" eksisterer
And aktiviteten "Aktivitet" horer under projektet "projekt"
And bruger er tildelt aktivitet "Aktivitet" under projektet "projekt"
When bruger "ABC" registrerer tid arbejdet på aktiviteten "Aktivitet" under projektet "projekt"
Then brugerens totale tidsforbrug er opdateret


Scenario: Bruger registrerer tid på ikke-tildelt aktivitet
Given bruger "ASN" er logget ind
And bruger er ikke tildelt nogen aktiviteter
When bruger registrerer tid
Then fås fejlmeddelsen "Du er ikke tildelt nogen aktiviteter at registrere tid på"