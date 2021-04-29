#Feature: Tidsregistrering
#Description: Bruger registrerer tid
#
#Actor: Bruger
#Scenario: Bruger registrerer tid på tildelt aktivitet
#Given bruger er logget ind
#And bruger er tildelt aktivitet "Aktivitet"
#When bruger registrerer tid arbejdet på aktiviteten "Aktivitet"
#Then brugerens totale tidsforbrug er opdateret
#
#
#Scenario: Bruger registrerer tid på ikke-tildelt aktivitet
#Given bruger er logget ind
#And bruger er ikke tildelt aktivitet "Aktivitet"
#When bruger registrerer tid arbejdet på aktiviteten "Aktivitet"
#Then fås fejlmeddelsen "Ulovlig tidsregistrering"