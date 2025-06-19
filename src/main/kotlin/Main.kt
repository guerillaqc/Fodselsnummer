package org.guerillaqc


import org.guerillaqc.fnr.FnrGenerator
import org.guerillaqc.fnr.utils.Enums
import org.guerillaqc.fnr.utils.FnrUtils

fun main() {
    var fnr                 = FnrGenerator.getRandomPersonFnr()
    var fdato               = FnrGenerator.getRandomPersonFdato()

    var fnrKjonnM           = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.MANN)
    var fnrKjonnK           = FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)

    var fnrForelderTil      = FnrGenerator.getRandomFnrForelderTilFnr("10057434940")
    var fdatoForelderTil    = FnrGenerator.getRandomFdatoForelderTilFnr("10057434940")

    var fnrMillenial        = FnrGenerator.getRandomFnrMillenial()
    var fdatoMillenial      = FnrGenerator.getRandomFdatoMillenial()

    var fnrBarn             = FnrGenerator.getRandomFnrBarn()
    var fdatoBarn           = FnrGenerator.getRandomFdatoBarn()

    var fnrBarnehage        = FnrGenerator.getRandomFnrBarnehage()
    var fdatoBarnehage      = FnrGenerator.getRandomFdatoBarnehage()

    var fnrBarnSFO          = FnrGenerator.getRandomFnrSFO()
    var fdatoBarnSFO        = FnrGenerator.getRandomFdatoSFO()

    var fnrForAlder1        = FnrGenerator.getRandomFnrAlder(20)
    var fnrForAlder2        = FnrGenerator.getRandomFnrAlder(30)

    var fdatoForAlder1      = FnrGenerator.getRandomFdatoAlder(20)
    var fdatoForAlder2      = FnrGenerator.getRandomFdatoAlder(30)

    var kjonnFraFnr                 = FnrUtils.getKjonnFraFnr(FnrGenerator.getRandomPersonFnr())
    var fdatoFraFnr                 = FnrUtils.getFodselsdatoFraFnr(FnrGenerator.getRandomPersonFnr())
    var fnrFormattert               = FnrUtils.getFnrFormatert(FnrGenerator.getRandomPersonFnr())
    var alderFraFdatoDatostreng     = FnrUtils.getAlderFraFdato(FnrGenerator.getRandomPersonFdato())
    var akderFraFdatoDato           = FnrUtils.getAlderFraFdato(10, 5, 1974)
    var alderFraFnr                 = FnrUtils.getAlderFraFnr(FnrGenerator.getRandomPersonFnr())

    print("")
}