import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import org.guerillaqc.fnr.FnrGenerator
import org.guerillaqc.fnr.utils.Enums
import no.bekk.bekkopen.person.FodselsnummerValidator.isValid
import org.guerillaqc.fnr.utils.FnrUtils

class GenererTilfeldigFnr : StringSpec({

    "Generert tilfeldig fnr er gyldig." {
        isValid(FnrGenerator.getRandomPersonFnr()) shouldBe true
    }

    "Generert tilfeldig fnr med spesifikt kjønn MANN er gyldig." {
        isValid(FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)) shouldBe true
        isValid(FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.MANN)) shouldBe true
    }

    "Generert tilfelding fnr for person som kan være forelder er gyldig." {
        val fnrAvkom        = "10057434940"
        val alderAvkom      = FnrUtils.getAlderFraFnr(fnrAvkom)
        val fnrForelder     = FnrGenerator.getRandomFnrForelderTilFnr(fnrAvkom)
        val alderForelder   = FnrUtils.getAlderFraFnr(fnrForelder)

        isValid(fnrForelder) shouldBe true
        alderForelder shouldBeGreaterThan (alderAvkom + 17)
    }



})
