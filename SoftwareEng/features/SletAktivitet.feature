#Feature: Slet aktivitet
#Description: Bruger sletter en aktivitet fra listen
# 
#Actor: Projektleder
#Scenario: Slet aktivitet
#Given brugeren "NIE" er projektleder
#And aktiviteten "Planlægning" og projektet "Projekt" eksisterer
#And aktiviteten "Planlægning" hører under projektet "Projekt"
#When brugeren sletter aktiviteten
#Then aktiviteten fjernes fra projektet
# 
#
#Scenario: Forsøg på at slette aktivitet fra forkert projekt
#Given brugeren "NIE" er projektleder
#And aktiviteten "Aktivitet" og projektet "Projekt2" eksisterer
#And aktiviteten "Aktivitet" ikke hører under projektet "Projekt2"
#When brugeren sletter aktiviteten
#Then fås fejlmeddelelsen "aktivitet hører ikke under projekt"
