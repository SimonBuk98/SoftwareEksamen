Feature: Tilføj en medarbejder til projekt
Description: Bruger tilføjer medarbejder til projekt
 
Actors: Projektleder, Bruger

Scenario: Main scenario
Given at brugeren "ABC” "projektleder
And projektet "pojekt" eksisterer
And medarbejderen "HEJ" eksisterer
And medarbejderen ikke er en del af projektet
When brugeren tilføjer medarbejderen til projektet
Then brugeren er tilføjet til projektet
 

Scenario: Secondary scenario
Given at brugeren "ABC" er eksisterer
And projektet "Test" ikke eksisterer
And medarbejderen "HEJ" eksisterer
When brugeren tilføjer medarbejderen til projektet
Then fås fejlmeddelelsen "projekt eksisterer ikke"
