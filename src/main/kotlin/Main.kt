package org.guerillaqc


import org.guerillaqc.fnr.FnrGenerator
import org.guerillaqc.fnr.utils.Enums
import org.guerillaqc.fnr.utils.FnrUtils

fun main() {
    /* Generer tilfeldig fødselsnummer og fødselsdato: */
    //var tilfFnr                 = FnrGenerator.getRandomPersonFnr()
    //var tilfFdato               = FnrGenerator.getRandomPersonFdato()

    /* Generer tilfeldig fødselsnummer med spesifikt kjønn: */
    //var tilfFnrKjonnM           = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.MANN)
    //var tilfFnrKjonnK           = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som kan være forelder til angitt fødselsnummer: */
    //val tilfeldigFnrAvkom       = "10057434940"
    //val tilfeldigAlderAvkom     = FnrUtils.getAlderFraFnr(tilfeldigFnrAvkom)
    //val tilfeldigFnrForelderTil = FnrGenerator.getRandomFnrForelderTilFnr(tilfeldigFnrAvkom)
    //val tilfeldigAlderForelder = FnrUtils.getAlderFraFnr(tilfeldigFnrForelderTil)
    //print("")

    //var tilfFdatoForelderTil    = FnrGenerator.getRandomFdatoForelderTilFnr("10057434940")

    /* Generer tilfeldig fødselsnummer og fødselsdato for person som er født etter år 2000: */
    //var tilfFnrMillenial        = FnrGenerator.getRandomFnrMillenial()
    //var tilfFdatoMillenial      = FnrGenerator.getRandomFdatoMillenial()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn: */
    //var tilfFnrBarn             = FnrGenerator.getRandomFnrBarn()
    //var tilfFdatoBarn           = FnrGenerator.getRandomFdatoBarn()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går i barnehage (riktig alder): */
    //var tilfFnrBarnehage        = FnrGenerator.getRandomFnrBarnehage()
    //var tilfFdatoBarnehage      = FnrGenerator.getRandomFdatoBarnehage()

    /* Generer tilfeldig fødselsnummer og fødselsdato for barn som går på SFO (riktig alder): */
    //var tilfFnrBarnSFO          = FnrGenerator.getRandomFnrSFO()
    //var tilfFdatoBarnSFO        = FnrGenerator.getRandomFdatoSFO()

    /* Generer tilfeldig fødselsnummer og fødselsdato for person med spesifikk alder: */
    //var tilfFnrForAlder1        = FnrGenerator.getRandomFnrAlder(20)
    //var tilfFnrForAlder2        = FnrGenerator.getRandomFnrAlder(30)
    //var tilfFdatoForAlder1      = FnrGenerator.getRandomFdatoAlder(20)
    //var tilfFdatoForAlder2      = FnrGenerator.getRandomFdatoAlder(30)

    /* Finn kjønn ut fra fødselsnummer: */
    //var kjonnFnrK               =  FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)
    //var kjonnK                  =  FnrUtils.getKjonnFraFnr(kjonnFnrK)
    //var kjonnFnrM               =  FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.MANN)
    //var kjonnM                  =  FnrUtils.getKjonnFraFnr(kjonnFnrM)

    /* Finn fødselsdato ut fra fødselsnummer: */
    //var fdatoFraFnr             = FnrUtils.getFodselsdatoFraFnr(FnrGenerator.getRandomPersonFnr())

    /* Finn alder ut fra fødselsdato: */
    //val fdato                   = FnrGenerator.getRandomPersonFdato()
    //var alderFraFdato           = FnrUtils.getAlderFraFdato(fdato)

    /* Finn alder ut fra fødselsnummer: */
    //var alderFraFnr             = FnrUtils.getAlderFraFnr("10057434940")

    /* Finn alder ut fra fødselsdato - dag, mnd og år: */
    //var alderFraFdatoDagMndAr   = FnrUtils.getAlderFraFdato(10, 5, 1974)

    /* Formatter fødselsnummer (skille mellom datodel og fnrdel): */
    //var fnrFormattertDefault   = FnrUtils.getFnrFormatert(FnrGenerator.getRandomPersonFnr())
    //var fnrFormattertAnnet     = FnrUtils.getFnrFormatert(FnrGenerator.getRandomPersonFnr(), "-")

    /* Formatter fødselsdato: */
    //val fdatoTilFormattering    = FnrGenerator.getRandomPersonFdato()
    //val formattertFodselsdatoD  = FnrUtils.getFdatoFormatert(fdatoTilFormattering)
    //val formattertFodselsdato   = FnrUtils.getFdatoFormatert(fdatoTilFormattering, "-")

    print("")
}