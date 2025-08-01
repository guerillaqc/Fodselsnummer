import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.FnrValidator.erGyldig
import io.github.guerillaqc.fnr.FnrValidator.erGyldigSyntetisk
import io.github.guerillaqc.fnr.utils.FnrUtils

/**
 * Det er litt problematisk å bruke egen validator til å teste genererte fnr, men siden det ikke finnes noe pålitelig
 * åpent bibliotek som gjør dette så er det den eneste muligheten vi har pr. nå. I tillegg har vi allerede validatoren
 * i prosjektet.
 * */

class KonverterMellomReelleOgSyntetiskeFnrTester : StringSpec({

    "Konverter reelt fnr til syntetisk fnr." {
        erGyldigSyntetisk(FnrUtils.konverterTilSyntetiskFnr(FnrGenerator.tilfeldigFodselsnummer()).toString()) shouldBe true
    }

    "Konverter syntetisk fnr til reellt fnr." {
        erGyldig(FnrUtils.konverterFraSyntetiskFnr(FnrGenerator.tilfeldigSyntetiskFodselsnummer()).toString()) shouldBe true
    }

})