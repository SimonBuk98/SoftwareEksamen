#s204497
Feature: Tilføj en medarbejder til projekt
Description: Bruger tilføjer medarbejder til projekt

Actors: Projektleder, Bruger

Scenario: Main scenario
Given at brugeren "hej" er projektleder
And projektet "projekt" eksisterer
And medarbejderen "hej" eksisterer
And medarbejderen "hej" ikke er en del af projektet "projekt"
When brugeren tilføjer medarbejderen "hej" til projektet "projekt"
Then brugeren "hej" er tilføjet til projektet "projekt"
 

Scenario: Secondary scenario
Given at brugeren "ABC" er projektleder
And projektet "projekt" eksisterer
And medarbejderen "HEJ" eksisterer
And medarbejderen "hej" er en del af projektet "projekt"
When brugeren tilføjer medarbejderen "hej" til projektet "projekt"
Then fås fejlmeddelelsen "Brugeren er allerede en del af projektet projekt"
