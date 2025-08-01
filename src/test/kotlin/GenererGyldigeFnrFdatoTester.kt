import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.FnrValidator.erGyldig
import io.github.guerillaqc.fnr.FnrValidator.erGyldigFodselsdato
import io.github.guerillaqc.fnr.FnrValidator.erGyldigSyntetisk
import io.github.guerillaqc.fnr.utils.Enums
import io.github.guerillaqc.fnr.utils.FnrUtils
import io.kotest.matchers.ints.*
import io.kotest.matchers.ints.shouldBeGreaterThan

/**
 * Det er litt problematisk å bruke egen validator til å teste genererte fnr, men siden det ikke finnes noe pålitelig
 * åpent bibliotek som gjør dette så er det den eneste muligheten vi har pr. nå. I tillegg har vi allerede validatoren
 * i prosjektet.
 * */

class GenererTilfeldigFnr : StringSpec({

    "Generert tilfeldig fødselsnummer er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummer()) shouldBe true
    }

    "Generert tilfeldig fødselsdato er gyldig." {
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdato())
    }

    "Generert tilfeldig fødselsnummer med spesifikt kjønn er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMann()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKvinne()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)) shouldBe true
    }

    "Generert tilfeldig fødselsnummer med spesifikt kjønn for barn er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerJente()) shouldBe true
        erGyldig(FnrGenerator.tilfeldigFodselsnummerGutt()) shouldBe true

        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerJente()) shouldBeLessThan 18
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerGutt()) shouldBeLessThan 18
    }

    "Generert tilfeldig fødselsnummer for barn er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMillenial()) shouldBe true
    }

    "Generert tilfeldig fødselsdato for barn er riktig."{
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerBarn()) shouldBeLessThan  18
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoBarn()) shouldBeLessThan  18
    }

    "Generert tilfeldig fødselsnummer for barn i barnehage er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerBarnehage()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerBarnehage()) shouldBeLessThan 7
    }

    "Generert tilfeldig fødselsdato for barn i barnehage er riktig." {
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoBarnehage()) shouldBeLessThan 7
    }

    "Generert tilfeldig fødselsnummer for barn i SFO er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerSFO()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerSFO()) shouldBeGreaterThanOrEqual 5
    }

    "Generert tilfeldig fødselsdato for barn i SFO er riktig." {
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoSFO()) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoSFO()) shouldBeLessThanOrEqual 9
    }

    "Generert tilfeldig fødselsnummer for voksen person er gyldig." {
        erGyldig(FnrGenerator.tilfeldigFodselsnummerVoksen()) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerVoksen()) shouldBeGreaterThanOrEqual  18
    }

    "Generert tilfeldig fødselsdato for voksen person er gyldig og riktig." {
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdatoVoksen())
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoVoksen()) shouldBeGreaterThan 17
    }

    "Generert tilfelding fødselsnummer for person som kan være forelder er gyldig." {
        val fnrAvkom        = "10057434940"
        val alderAvkom      = FnrUtils.alderFraFodselsnummer(fnrAvkom)
        val fnrForelder     = FnrGenerator.tilfeldigFodselsnummerForelder(fnrAvkom)
        val alderForelder   = FnrUtils.alderFraFodselsnummer(fnrForelder)

        alderForelder shouldBeGreaterThan (alderAvkom + 16)
    }

    "Generert tilfelding fødselsdato for person som kan være forelder er gyldig." {
        val fnrAvkom        = "10057434940"
        val alderAvkom      = FnrUtils.alderFraFodselsnummer(fnrAvkom)
        val fdatoForelder   = FnrGenerator.tilfeldigFodselsdatoForelder(fnrAvkom)
        val alderForelder   = FnrUtils.alderFraFodselsdato(fdatoForelder)

        alderForelder shouldBeGreaterThan (alderAvkom + 16)
    }

    "Generert tilfeldig fødselsnummer for person som er født etter år 2000 er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerMillenial()) shouldBe true
    }

    "Generert tilfeldig fødselsdato for person som er født etter år 2000 er riktig."{
        FnrGenerator.tilfeldigFodselsdatoMillenial() shouldBeGreaterThan "01012000"
    }

    "Generert tilfeldig fødselsnummer for person med spesifikk alder er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(5)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(5)) shouldBe 5
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(20)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(20)) shouldBe 20
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlder(55)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlder(55)) shouldBe 55
    }

    "Generert tilfeldig fødselsdato for person med spesifikk alder er gyldig."{
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(5)) shouldBe true
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(5)) shouldBe 5
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(20)) shouldBe true
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(20)) shouldBe 20
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(55)) shouldBe true
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlder(55)) shouldBe 55
    }

    "Generert tilfeldig fødselsnummer for person i spesifikt aldersintervall er gyldig."{
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(5, 15)) shouldBeLessThanOrEqual 15
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(15, 55)) shouldBeLessThanOrEqual 55
        erGyldig(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBe true
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsnummer(FnrGenerator.tilfeldigFodselsnummerAlderMellom(55, 75)) shouldBeLessThanOrEqual 75
    }

    "Generert tilfeldig fødselsdato for person i spesifikt aldersintervall er gyldig."{
        erGyldigFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(5, 15)) shouldBe true
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(5, 15)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(5, 15)) shouldBeLessThanOrEqual 15
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(15, 55)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(15, 55)) shouldBeLessThanOrEqual 55
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(55, 75)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraFodselsdato(FnrGenerator.tilfeldigFodselsdatoAlderMellom(55, 75)) shouldBeLessThanOrEqual 75
    }

    "Generert tilfeldig syntetisk fødselsnummer er gyldig." {
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummer()) shouldBe true
    }

    "Generert tilfeldig syntetisk fødselsnummer med spesifikt kjønn er gyldig." {
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerMann()) shouldBe true
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerKvinne()) shouldBe true
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerKjonn(Enums.Kjonn.MANN)) shouldBe true
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerKjonn(Enums.Kjonn.KVINNE)) shouldBe true
    }

    "Generert tilfeldig syntetisk fødselsnummer for person med spesifikk alder er gyldig."{
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(5)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(5)) shouldBe 5
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(20)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(20)) shouldBe 20
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(55)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlder(55)) shouldBe 55
    }

    "Generert tilfeldig syntetisk fødselsnummer barn er gyldig."{
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerBarn()) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerBarn()) shouldBeLessThan 18
    }

    "Generert tilfeldig syntetisk fødselsnummer voksen er gyldig."{
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerVoksen()) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerVoksen()) shouldBeGreaterThanOrEqual 18
    }

    "Generert tilfeldig syntetisk fødselsnummer for person i spesifikt aldersintervall er gyldig."{
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(5, 15)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(5, 15)) shouldBeGreaterThanOrEqual 5
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(5, 15)) shouldBeLessThanOrEqual 15
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(15, 55)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(15, 55)) shouldBeGreaterThanOrEqual 15
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(15, 55)) shouldBeLessThanOrEqual 55
        erGyldigSyntetisk(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(55, 75)) shouldBe true
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(55, 75)) shouldBeGreaterThanOrEqual 55
        FnrUtils.alderFraSyntetiskFodselsnummer(FnrGenerator.tilfeldigSyntetiskFodselsnummerAlderMellom(55, 75)) shouldBeLessThanOrEqual 75
    }
})

