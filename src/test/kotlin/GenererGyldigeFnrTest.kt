import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import org.guerillaqc.fnr.FnrGenerator
import no.bekk.bekkopen.person.FodselsnummerValidator.isValid
import org.guerillaqc.fnr.utils.FnrUtils

/* TODO:
    no.bekk.bekkopen.person er ikke til å stole på. Gyldige numre blir flagget som ugyldige. Det er dermed umulig å
    teste dette troverdig.

    Eksempler:
    17105150644
    15024850525
    06114459648
    25064354836
    28064557175
 */

class GenererTilfeldigFnr : StringSpec({

    /*"Generert tilfeldig fnr er gyldig." {
        val randomfnr = FnrGenerator.getRandomPersonFnr()
        println(randomfnr)

        isValid(randomfnr) shouldBe true
    }*/

    /*"Generert tilfeldig fnr med spesifikt kjønn MANN er gyldig." {
        val randomfnrMann = FnrGenerator.getRandomPersonFnr()
        println(randomfnrMann)

        isValid(randomfnrMann) shouldBe true
    }*/

    //isValid(FnrGenerator.getRandomPersonFnrKjonn(Enums.Kjonn.KVINNE)) shouldBe true

    /*"Generert tilfelding fnr for person som kan være forelder er gyldig." {
        val fnrAvkom        = "10057434940"
        val alderAvkom      = FnrUtils.getAlderFraFnr(fnrAvkom)
        val fnrForelder     = FnrGenerator.getRandomFnrForelderTilFnr(fnrAvkom)
        val alderForelder   = FnrUtils.getAlderFraFnr(fnrForelder)

        //println("Alder på forelder: $alderForelder\nAlder avkom: $alderAvkom\nDifferanse: ($alderForelder-$alderAvkom)")
        //isValid(fnrForelder) shouldBe true

        println("alderForelder type: ${alderForelder::class}")
        println("alderForelder value: '$alderForelder'")
        println("alderAvkom type: ${alderAvkom::class}")
        println("alderAvkom value: '$alderAvkom'")
        println("alderAvkom + 16 = ${alderAvkom + 16}")
        println("Direct comparison: ${183 > 67}")
        println("Variable comparison: ${alderForelder > (alderAvkom + 16)}")
        println("Are they the same type? ${alderForelder::class == (alderAvkom + 16)::class}")

        //val a = 183
        //val b = 67
        //println("Hardcoded test: ${a > b}")
        //assertTrue(a > b)

        alderForelder shouldBeGreaterThan (alderAvkom + 16)
        //assertTrue(alderForelder > (alderAvkom + 16))
    }*/

    /*"Generert tilfeldig fødselsnummer og fødselsdato for person som er født etter år 2000 er gyldig."{
        isValid(FnrGenerator.getRandomFnrMillenial()) shouldBe true
    }*/

})
