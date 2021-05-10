#s191252
Feature: Tilføj edarbejder til aktivitet
Description: Bruger tilføjer medarbejder til aktivitet
 
Actor: Projektleder
Scenario: Main scenario
Given brugeren "ABC" er projektleder
And projektet "Pacman" eksisterer
And aktiviteten "opbevaring" eksisterer i projektet "Pacman" 
And medarbejderen "HEJ" eksisterer
And medarbejderen "HEJ" er del af projektet "Pacman" 
When brugeren tilføjer medarbejderen "HEJ" til aktiviteten "opbevaring" under projektet "Pacman"
Then brugeren "HEJ" er en del af aktiviteten "opbevaring"

Scenario: Secondary scenario
Given brugeren "ABC" er projektleder
And projektet "Pacman" eksisterer
And aktiviteten "Ghost" eksisterer i projektet "Pacman" 
And medarbejderen "AUD" eksisterer
And brugeren "AUD" er ikke del af projektet "Pacman"
When projektlederen tilføjer "AUD" til aktiviteten "Ghost" under projektet "Pacman"
Then fås fejlmeddelelsen "Bruger er ikke tildelt projekt"
    