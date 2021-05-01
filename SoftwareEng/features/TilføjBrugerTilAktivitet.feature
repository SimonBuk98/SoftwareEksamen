Feature: Tilføj edarbejder til aktivitet
Description: Bruger tilføjer medarbejder til aktivitet
 
Actor: Projektleder
Scenario: Main scenario
Given brugeren "ABC" projektleder
And projektet "Pacman" eksisterer
And aktiviteten "Ghost" eksisterer
And medarbejderen "HEJ" eksisterer
And medarbejderen er del af projektet
When brugeren tilføjer medarbejderen til aktiviteten
Then brugeren er en del af aktiviteten


Scenario: Secondary scenario
Given brugeren "ABC" er projektleder
And projektet "Pacman" eksisterer
And aktiviteten "Ghost" eksisterer
And brugeren "AUD" eksisterer
And brugeren er ikke del af projektet
When den aktive bruger tilføjer den anden bruger til aktiviteten
Then fås fejlmeddelelsen "Bruger er ikke tildelt projekt"
