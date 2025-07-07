package io.github.guerillaqc

import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.utils.Enums
import io.github.guerillaqc.fnr.utils.FnrUtils

fun main() {

    print("Tilfeldig fødselsnummer: ${FnrGenerator.tilfeldigFodselsnummer()}\n" +
          "Tilfeldig fødselsdato: ${FnrGenerator.tilfeldigFodselsdato()}\n" +
          "Tilfeldig fødselsnummer mann: ${FnrGenerator.tilfeldigFodselsnummerMann()} \n" +
          "Tilfeldig fødselsdato for mann: ${FnrGenerator.tilfeldigFodselsnummerMann()}\n" +
          "Tilfeldig fødselsnummer kvinne: ${FnrGenerator.tilfeldigFodselsnummerKvinne()}\n" +
          "Tilfeldig fødselsdato for kvinne: ${FnrGenerator.tilfeldigFodselsnummerKvinne()}\n" +
          "Tilfeldig fødselsnummer med kjønn mann: ${FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)}\n" +
          "Tilfeldig fødselsnummer med kjønn kvinne: ${FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)}\n" +
          "Tilfeldig fødselsnummer forelder: ${FnrGenerator.tilfeldigFodselsnummerForelder("01060599633")} " +
            "(alder avkom: ${FnrUtils.alderFraFodselsnummer("01060599633")})\n" +
          "Tilfeldig fødselsdato forelder: ${FnrGenerator.tilfeldigFodselsnummerForelder("01060599633")}\n" +
          "Tilfeldig fødselsnummer millenial: ${FnrGenerator.tilfeldigFodselsnummerMillenial()}\n" +
          "Tilfeldig fødselsndato millenial: ${FnrGenerator.tilfeldigFodselsdatoMillenial()}\n" +
          "Tilfeldig fødselsnummer voksen: ${FnrGenerator.tilfeldigFodselsnummerVoksen()}\n" +
          "Tilfeldig fødselsdato voksen: ${FnrGenerator.tilfeldigFodselsdatoVoksen()}\n" +
          "Tilfeldig fødselsnummer barn: ${FnrGenerator.tilfeldigFodselsnummerBarn()}\n" +
          "Tilfeldig fødselsnummer jente: ${FnrGenerator.tilfeldigFodselsnummerJente()}\n" +
          "Tilfeldig fødselsnummer gutt: ${FnrGenerator.tilfeldigFodselsnummerGutt()}\n" +
          "Tilfeldig fødselsdato barn: ${FnrGenerator.tilfeldigFodselsdatoBarn()}\n" +
          "Tilfeldig fødselsnummer barnehage: ${FnrGenerator.tilfeldigFodselsnummerBarnehage()}\n" +
          "Tilfeldig fødselsdato barnehage: ${FnrGenerator.tilfeldigFodselsdatoBarnehage()}\n" +
          "Tilfeldig fødselsnummer SFO: ${FnrGenerator.tilfeldigFodselsnummerSFO()}\n" +
          "Tilfeldig fødselsdato SFO: ${FnrGenerator.tilfeldigFodselsdatoSFO()}\n" +
          "Tilfeldig fødselsnummer for alder (20): ${FnrGenerator.tilfeldigFodselsnummerAlder(20)}\n" +
          "Tilfeldig fødselsnummer for alder (30): ${FnrGenerator.tilfeldigFodselsnummerAlder(30)}\n" +
          "Tilfeldig fødselsdato for alder (20): ${FnrGenerator.tilfeldigFodselsdatoAlder(20)}\n" +
          "Tilfeldig fødselsdato for alder (30): ${FnrGenerator.tilfeldigFodselsdatoAlder(30)}\n" +
          "Tilfeldig fødselsdato for alder mellom 20 og 40: ${FnrGenerator.tilfeldigFodselsnummerAlderMellom(20, 40)}\n" +
          "Tilfeldig syntetisk fødselsnummer: ${FnrGenerator.tilfeldigSyntetiskFodselsnummer()}\n" +
          "Tilfeldig syntetisk fødselsdato: ${FnrGenerator.tilfeldigSyntetiskFodselsdato()}\n" +
          "Tilfeldig : ${FnrGenerator.tilfeldigSyntetiskFodselsnummer()}\n")


    /* Generer tilfeldig fødselsnummer og fødselsdato: */
    val tileldigfFodselsnummenr         = FnrGenerator.tilfeldigFodselsnummer()
    val tilfeldigFdato                  = FnrGenerator.tilfeldigFodselsdato()

    /* Generer føselsnummer med spesifikt kjønn, og finn kjønn ut fra fødselsnummer: */
    val tilfeldigFodselsnummerKvinne    =  FnrGenerator.tilfeldigFodselsnummerKvinne()
    val kjonnFodselsnummerKvinne        =  FnrUtils.kjonnFraFodselsnummer(tilfeldigFodselsnummerKvinne)
    val tilfeldigFodselsnummerMann      =  FnrGenerator.tilfeldigFodselsnummerMann()
    val kjonnFodselsnummerMann          =  FnrUtils.kjonnFraFodselsnummer(tilfeldigFodselsnummerMann)
    val tilfeldigFodselsnummerJente     = FnrGenerator.tilfeldigFodselsnummerJente()
    val kjonnFodselsnummerJente         = FnrUtils.kjonnFraFodselsnummer(tilfeldigFodselsnummerJente)
    val tilfeldigFodselsnummerGutt      = FnrGenerator.tilfeldigFodselsnummerGutt()
    val kjonnFodselsnummerGutt          = FnrUtils.kjonnFraFodselsnummer(tilfeldigFodselsnummerMann)

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som kan være forelder til angitt fødselsnummer: */
    val tilfeldigFnrAvkom               = "01060599633"
    val alderAvkom                      = FnrUtils.alderFraFodselsnummer(tilfeldigFnrAvkom)
    //TODO: Her ble det på ett tidspunkt generert følgende nr.: 20017657209 - denne personen er oppgitt som født i 1876!
    val tilfeldigFodselsnummerForelder  = FnrGenerator.tilfeldigFodselsnummerForelder(tilfeldigFnrAvkom)
    val alderForelder                   = FnrUtils.alderFraFodselsnummer(tilfeldigFodselsnummerForelder)
    val tilfeldigFodselsdatoForelder    = FnrGenerator.tilfeldigFodselsdatoForelder("01060599633")
    //print("")

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som er født etter år 2000: */
    val tilfeldigFodselsnummerMillenial = FnrGenerator.tilfeldigFodselsnummerMillenial()
    val tilfeldigFodselsdatoMillenial   = FnrGenerator.tilfeldigFodselsdatoMillenial()

    /* Generer tilfeldig fødselsnummer og fødselsdato for voksen person: */
    val tilfeldigFodselsnummerVoksen    = FnrGenerator.tilfeldigFodselsnummerVoksen()
    val tilfeldigFodselsdatoVoksen      = FnrGenerator.tilfeldigFodselsdatoVoksen()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn: */
    val tilfeldigFodselsnummerBarn      = FnrGenerator.tilfeldigFodselsnummerBarn()
    val tilfeldigFodselsdatoBarn        = FnrGenerator.tilfeldigFodselsdatoBarn()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går i barnehage (riktig alder): */
    val tilfeldigFodselsnummerBarnehage = FnrGenerator.tilfeldigFodselsnummerBarnehage()
    val tilfeldigFodselsdatoBarnehage   = FnrGenerator.tilfeldigFodselsdatoBarnehage()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går på SFO (riktig alder): */
    val tilfeldigFodselsnummerBarnSFO   = FnrGenerator.tilfeldigFodselsnummerSFO()
    val tilfFodselsdatoBarnSFO          = FnrGenerator.tilfeldigFodselsdatoSFO()

    /* Generer tilfeldig fødselsnummer og fødselsdato for person med spesifikk alder: */
    val tilfeldigFodselsnummerAlder1        = FnrGenerator.tilfeldigFodselsnummerAlder(20)
    val tilfeldigFodselsnummerAlder2        = FnrGenerator.tilfeldigFodselsnummerAlder(30)
    val tilfFodselsdatolder1                = FnrGenerator.tilfeldigFodselsdatoAlder(20)
    val tilfFodselsdatolder2                = FnrGenerator.tilfeldigFodselsdatoAlder(30)
    val tilfeldigFodselsnummerAlderMellom   = FnrGenerator.tilfeldigFodselsnummerAlderMellom(20, 40)

    /* Finn fødselsdato ut fra fødselsnummer: */
    val fodselsdatoFraFodselsnummer     = FnrUtils.fodselsdatoFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummer())

    /* Finn alder ut fra fødselsdato: */
    val fdato                   = FnrGenerator.tilfeldigFodselsdato()
    val alderFraFdato           = FnrUtils.alderFraFodselsdato(fdato)

    /* Finn alder ut fra fødselsnummer: */
    val alderFraFnr             = FnrUtils.alderFraFodselsnummer("10057434940")

    /* Finn alder ut fra fødselsdato - dag, mnd og år: */
    val alderFraFdatoDagMndAr   = FnrUtils.alderFraFodselsdato(10, 5, 1974)

    /* Formatter fødselsnummer (skille mellom datodel og fnrdel): */
    val fnrFormattertDefault   = FnrUtils.formaterFodselsnummer(FnrGenerator.tilfeldigFodselsnummer())
    val fnrFormattertAnnet     = FnrUtils.formaterFodselsnummer(FnrGenerator.tilfeldigFodselsnummer(), "-")

    /* Formatter fødselsdato: */
    val fdatoTilFormattering    = FnrGenerator.tilfeldigFodselsdato()
    val formattertFodselsdatoD  = FnrUtils.formaterFodselsdato(fdatoTilFormattering)
    val formattertFodselsdato   = FnrUtils.formaterFodselsdato(fdatoTilFormattering, "-")

    print("")
}