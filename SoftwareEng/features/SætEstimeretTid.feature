Feature: Sæt estimeret tid
Description: Sæt det estimerede start og sluttidspunkt for projektet
Actor: Bruger


Scenario: Main scenario
Given brugeren "FGBN" er projektleder
And aktivitet "opbevaring" eksisterer under projektet "projekt"
When brugeren "FGBN" sætter start til 24112019 og slut til 14012020 for aktiviteten "opbevaring" under projektet "projekt"
Then start er 24 11 2019 og slut 14 01 2020 for aktiviteten "opbevaring" under projektet "projekt"


Scenario: Secondary scenario
Given brugeren "INJK" er projektleder
And aktivitet "opbevaring" eksisterer under projektet "projekt"
When brugeren "FGBN" sætter start til 14012020 og slut til 24112019 for aktiviteten "opbevaring" under projektet "projekt"
Then fås fejlmeddelsen "Ugyldige datoer"