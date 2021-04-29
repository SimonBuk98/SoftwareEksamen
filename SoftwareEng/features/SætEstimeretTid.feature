#Feature: Sæt estimeret tid
#Description: Sæt det estimerede start og sluttidspunkt for projektet
#Actor: Bruger
#
#
#Scenario: Main scenario
#Given brugeren "FGBN" er projektleder
#When brugeren sætter start og sluttidspunkt
#Then start og sluttidspunktet er oprettet
#And den budgetterede tid er gemt
#
#
#Scenario: Secondary scenario
#Given brugeren "INJK" er ikke projektleder
#When brugeren sætter start og sluttidspunkt
#Then fås fejlmeddelsen "Brugeren skal være projektleder for at udføre denne handling"