package org.guerillaqc

import org.guerillaqc.fnr.FnrGenerator
import org.guerillaqc.fnr.utils.Enums
import org.guerillaqc.fnr.utils.FnrUtils

fun main() {

    print("Tilfeldig fødselsnummer: ${FnrGenerator.tilfeldigFodselsnummer()}\n" +
          "Tilfeldig fødselsdato: ${FnrGenerator.tilfeldigFodselsdato()}\n" +
          "Tilfeldig fødselsnummer mann: ${FnrGenerator.tilfeldigFodselsnummerMann()} \n" +
          "Tilfeldig fødselsnummer kvinne: ${FnrGenerator.tilfeldigFodselsnummerKvinne()}\n" +
          "Tilfeldig fødselsnummer med kjønn mann: ${FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)}\n" +
          "Tilfeldig fødselsnummer med kjønn kvinne: ${FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)}\n" +
          "Tilfeldig fødselsnummer forelder: ${FnrGenerator.tilfeldigFodselsnummerForelder("01060599633")} " +
            "(alder avkom: ${FnrUtils.alderFraFodselsnummer("01060599633")})\n" +
          "Tilfeldig fødselsdato forelder:  + ${FnrGenerator.tilfeldigFodselsnummerForelder("01060599633")}\n" +
          "Tilfeldig fødselsnummer millenial: ${FnrGenerator.tilfeldigFodselsnummerMillenial()}\n" +
          "Tilfeldig fødselsndato millenial: ${FnrGenerator.tilfeldigFodselsdatoMillenial()}\n" +
          "Tilfeldig fødselsnummer voksen: ${FnrGenerator.tilfeldigFodselsnummerVoksen()}\n" +
          "Tilfeldig fødselsdato voksen: ${FnrGenerator.tilfeldigFodselsdatoVoksen()}" +
          "Tilfeldig fødselsnummer barn: ${FnrGenerator.tilfeldigFodselsnummerBarn()}\n" +
          "Tilfeldig fødselsdato barn: ${FnrGenerator.tilfeldigFodselsdatoBarn()}\n" +
          "Tilfeldig fødselsnummer barnehage: ${FnrGenerator.tilfeldigFodselsnummerBarnehage()}\n" +
          "Tilfeldig fødselsdato barnehage: ${FnrGenerator.tilfeldigFodselsdatoBarnehage()}\n" +
          "Tilfeldig fødselsnummer SFO: ${FnrGenerator.tilfeldigFodselsnummerSFO()}\n" +
          "Tilfeldig fødselsdato SFO: ${FnrGenerator.tilfeldigFodselsdatoSFO()}\n" +
          "Tilfeldig fødselsnummer for alder (20): ${FnrGenerator.tilfeldigFodselsnummerAlder(20)}\n" +
          "Tilfeldig fødselsnummer for alder (30): ${FnrGenerator.tilfeldigFodselsnummerAlder(30)}\n" +
          "Tilfeldig fødselsdato for alder (20): ${FnrGenerator.tilfeldigFodselsdatoAlder(20)}\n" +
          "Tilfeldig fødselsdato for alder (30): ${FnrGenerator.tilfeldigFodselsdatoAlder(30)}\n")

    /* Generer tilfeldig fødselsnummer og fødselsdato: */
    val tilfFnr                 = FnrGenerator.tilfeldigFodselsnummer()
    val tilfFdato               = FnrGenerator.tilfeldigFodselsdato()

    /* Generer tilfeldig fødselsnummer med spesifikt kjønn: */
    val tilfeldigFnrMann        = FnrGenerator.tilfeldigFodselsnummerMann()
    val tilfeldigFnrKvinne      = FnrGenerator.tilfeldigFodselsnummerKvinne()
    val tilfeldigFnrKjonnMann   = FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)
    val tilfeldigFnrKjonnKvinne = FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som kan være forelder til angitt fødselsnummer: */
    val tilfeldigFnrAvkom               = "01060599633"
    val alderAvkom                      = FnrUtils.alderFraFodselsnummer(tilfeldigFnrAvkom)
    val tilfeldigFodselsnummerForelder  = FnrGenerator.tilfeldigFodselsnummerForelder(tilfeldigFnrAvkom)
    val alderForelder                   = FnrUtils.alderFraFodselsnummer(tilfeldigFodselsnummerForelder)
    var tilfeldigFodselsdatoForelder    = FnrGenerator.tilfeldigFodselsdatoForelder("01060599633")
    //print("")

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som er født etter år 2000: */
    var tilfeldigFodselsnummerMillenial = FnrGenerator.tilfeldigFodselsnummerMillenial()
    var tilfeldigFodselsdatoMillenial   = FnrGenerator.tilfeldigFodselsdatoMillenial()

    /* Generer tilfeldig fødselsnummer og fødselsdato for voksen person: */
    var tilfeldigFodselsnummerVoksen    = FnrGenerator.tilfeldigFodselsnummerVoksen()
    var tilfeldigFodselsatoVoksen       = FnrGenerator.tilfeldigFodselsdatoVoksen()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn: */
    var tilfeldigFodselsnummerBarn      = FnrGenerator.tilfeldigFodselsnummerBarn()
    var tilfeldigFodselsdatoBarn        = FnrGenerator.tilfeldigFodselsdatoBarn()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går i barnehage (riktig alder): */
    var tilfFnrBarnehage                = FnrGenerator.tilfeldigFodselsnummerBarnehage()
    var tilfFdatoBarnehage              = FnrGenerator.tilfeldigFodselsdatoBarnehage()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går på SFO (riktig alder): */
    var tilfFnrBarnSFO                  = FnrGenerator.tilfeldigFodselsnummerSFO()
    var tilfFdatoBarnSFO                = FnrGenerator.tilfeldigFodselsdatoSFO()

    /* Generer tilfeldig fødselsnummer og fødselsdato for person med spesifikk alder: */
    var tilfeldigFodselsnummerAlder1    = FnrGenerator.tilfeldigFodselsnummerAlder(20)
    var tilfeldigFodselsnummerAlder2    = FnrGenerator.tilfeldigFodselsnummerAlder(30)
    var tilfFodselsdatolder1            = FnrGenerator.tilfeldigFodselsdatoAlder(20)
    var tilfFodselsdatolder2            = FnrGenerator.tilfeldigFodselsdatoAlder(30)

    /* Finn kjønn ut fra fødselsnummer: */
    var fodselsnummerKvinne             =  FnrGenerator.tilfeldigFodselsnummerKvinne()
    var kjonnFodselsnummerKvinne        =  FnrUtils.getKjonnFraFnr(fodselsnummerKvinne)
    var fodselsnummerMann               =  FnrGenerator.tilfeldigFodselsnummerMann()
    var kjonnFodselsnummerMann          =  FnrUtils.getKjonnFraFnr(fodselsnummerMann)

    /* Finn fødselsdato ut fra fødselsnummer: */
    var fdatoFraFnr             = FnrUtils.getFodselsdatoFraFnr(FnrGenerator.tilfeldigFodselsnummer())

    /* Finn alder ut fra fødselsdato: */
    val fdato                   = FnrGenerator.tilfeldigFodselsdato()
    var alderFraFdato           = FnrUtils.getAlderFraFdato(fdato)

    /* Finn alder ut fra fødselsnummer: */
    var alderFraFnr             = FnrUtils.alderFraFodselsnummer("10057434940")

    /* Finn alder ut fra fødselsdato - dag, mnd og år: */
    var alderFraFdatoDagMndAr   = FnrUtils.getAlderFraFdato(10, 5, 1974)

    /* Formatter fødselsnummer (skille mellom datodel og fnrdel): */
    var fnrFormattertDefault   = FnrUtils.getFnrFormatert(FnrGenerator.tilfeldigFodselsnummer())
    var fnrFormattertAnnet     = FnrUtils.getFnrFormatert(FnrGenerator.tilfeldigFodselsnummer(), "-")

    /* Formatter fødselsdato: */
    val fdatoTilFormattering    = FnrGenerator.tilfeldigFodselsdato()
    val formattertFodselsdatoD  = FnrUtils.getFdatoFormatert(fdatoTilFormattering)
    val formattertFodselsdato   = FnrUtils.getFdatoFormatert(fdatoTilFormattering, "-")

    print("")
}