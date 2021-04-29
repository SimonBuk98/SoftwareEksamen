#Feature: Tilføj en medarbejder til projekt
#Description: Bruger tilføjer medarbejder til projekt
# 
#Actors: Projektleder, Bruger
#
#Scenario: Main scenario
#Given at brugeren "CD” "projektleder
#And projektet "Pojekt" eksisterer
#And medarbejderen "" eksisterer
#And medarbejderen ikke er en del af projektet
#When brugeren tilføjer medarbejderen til projektet
#Then brugeren er tilføjet til projektet
# 
#
#Scenario: Secondary scenario
#Given at brugeren “ABCD” er projektleder
#And projektet “Projekt” ikke eksisterer
#And medarbejderen “ABCD” eksisterer
#When brugeren tilføjer medarbejderen til projektet
#Then fås fejlmeddelelsen “projekt eksisterer ikke”
# 