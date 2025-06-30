import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.FnrValidator.erGyldig
import io.github.guerillaqc.fnr.utils.Enums
import io.github.guerillaqc.fnr.utils.FnrUtils

/**
 * Det er litt problematisk å bruke egen validator til å teste genererte fnr, men siden det ikke finnes noe pålitelig
 * åpent bibliotek som gjør dette så er det den eneste muligheten vi har pr. nå. I tillegg har vi allerede validatoren
 * i prosjektet.
 * */

class GenererTilfeldigFnr : StringSpec({

    "Generert tilfeldig fnr er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummer()) shouldBe true
    }

    "Generert tilfeldig fnr med spesifikt kjønn er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMann()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKvinne()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)) shouldBe true
    }

    "Generert tilfeldig fnr med spesifikt kjønn for barn er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerJente()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerGutt()) shouldBe true

        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerJente()) shouldBeLessThan 18
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerGutt()) shouldBeLessThan 18
    }

    "Generert tilfeldig fnr med spesifikt kjønn for barn i barnehage er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerBarnehage()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerBarnehage()) shouldBeLessThan 7
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoBarnehage()) shouldBeLessThan 7
    }

    "Generert tilfeldig fnr med spesifikt kjønn for barn i SFO er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerSFO()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerSFO()) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoSFO()) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoSFO()) shouldBeLessThanOrEqual 9
    }

    "Generert tilfeldig fnr for voksen person er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerVoksen()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerVoksen()) shouldBeGreaterThan 17
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoVoksen()) shouldBeGreaterThan 17
    }

    "Generert tilfelding fnr for person som kan være forelder er gyldig." {
        val fnrAvkom        = "10057434940"
        val alderAvkom      = FnrUtils.alderFraFodselsnummer(fnrAvkom)
        val fnrForelder     = FnrGenerator.tilfeldigFodselsnummerForelder(fnrAvkom)
        val alderForelder   = FnrUtils.alderFraFodselsnummer(fnrForelder)

        alderForelder shouldBeGreaterThan (alderAvkom + 16)
    }

    "Generert tilfeldig fødselsnummer og fødselsdato for person som er født etter år 2000 er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMillenial()) shouldBe true
        FnrGenerator.tilfeldigFodselsdatoMillenial() shouldBeGreaterThan "01012000"
    }

    "Generert tilfeldig fødselsnummer og fødselsdato for barn er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMillenial()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerBarn()) shouldBeLessThan  18
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoBarn()) shouldBeLessThan  18
    }

    "Generert tilfeldig fødselsnummer (MANGLER FORELØPIG: og fødselsdato) for person med spesifikk alder er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(5)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(5)) shouldBe 5
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(20)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(20)) shouldBe 20
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(55)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(55)) shouldBe 55
    }

    "Generert tilfeldig fødselsnummer (MANGLER FORELØPIG: og fødselsdato) for person i spesifikt aldersintervall er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBeLessThanOrEqual 15
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBeLessThanOrEqual 55
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBeLessThanOrEqual 75

        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(5, 15)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(5, 15)) shouldBeLessThanOrEqual 15
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(15, 55)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(15, 55)) shouldBeLessThanOrEqual 55
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(55, 75)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(55, 75)) shouldBeLessThanOrEqual 75
    }

})
