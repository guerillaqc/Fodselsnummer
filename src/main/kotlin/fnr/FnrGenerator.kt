package org.guerillaqc.fnr

import org.guerillaqc.fnr.utils.Enums
import org.guerillaqc.fnr.utils.FnrUtils
import org.guerillaqc.fnr.utils.HelperUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FnrGenerator {
    companion object {
        private val iar: String = Integer.parseInt(DateTimeFormatter.ofPattern("yy").format(LocalDateTime.now())).toString()

        fun tilfeldigFodselsnummer(): String = getRandomFnr()

        fun tilfeldigFodselsdato(): String = getRandomFdato()

        fun tilfeldigFodselsnummerKvinne() : String = tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)

        fun tilfeldigFodselsnummerMann() : String = tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)

        fun tilfeldigFodselsnummerKjonn(kjonn: Enums.Kjonn): String = getRandomFnr(kjonn = kjonn)

        fun tilfeldigFodselsnummerBarn(): String = getRandomFnr(barn = true)

        fun tilfeldigFodselsdatoBarn(): String = getRandomFdato(barn = true)

        fun tilfeldigFodselsnummerVoksen(): String = getRandomFnr(voksen = true)

        fun tilfeldigFodselsdatoVoksen(): String = getRandomFdato(voksen = true)

        fun tilfeldigFodselsnummerBarnehage(): String = getRandomFnr(barn = true, barnehage = true, sfo = false)

        fun tilfeldigFodselsdatoBarnehage(): String = getRandomFdato(barn = true, barnehage = true, sfo = false)

        fun tilfeldigFodselsnummerSFO(): String = getRandomFnr(barn = true, sfo = true)

        fun tilfeldigFodselsdatoSFO(): String = getRandomFdato(barn = true, sfo = true)

        fun tilfeldigFodselsnummerMillenial(): String = getRandomFnr(millenial = true)

        fun tilfeldigFodselsdatoMillenial(): String = getRandomFdato(millenial = true)

        fun tilfeldigFodselsnummerForelder(fnr: String): String = getRandomFnr(forelderTil = fnr)

        fun tilfeldigFodselsdatoForelder(fnr: String): String = getRandomFdato(forelderTil = fnr)

        fun tilfeldigFodselsnummerAlder(alder: Int): String = getRandomFnr(alder = alder, millenial = alder <= iar.toInt())

        fun tilfeldigFodselsdatoAlder(alder: Int): String = getRandomFdato(alder = alder, millenial = alder <= iar.toInt())

        private fun getRandomFnr(alder: Int? = null,
                                 kjonn: Enums.Kjonn = HelperUtils.getRandomEnum(Enums.Kjonn::class.java),
                                 forelderTil: String? = null,
                                 millenial: Boolean = false,
                                 barn: Boolean = false,
                                 voksen: Boolean = false,
                                 barnehage: Boolean = false,
                                 sfo: Boolean = false): String {
            /* TIP
               Gyldige kombinasjoner:
               parentOf = null, millenial = true,  barn = false, barnehage = false, sfo = false
               parentOf = null, millenial = false, barn = false, barnehage = false, sfo = false
               parentOf = null, millenial = false, barn = true,  barnehage = false, sfo = false
               parentOf = null, milennial = false, barn = true,  barnehage = true,  sfo = false
               parentOf = null, millenial = false, barn = true,  barnehage = false, sfo = true
               parentOf = """", millenial = false, barn = false, barnehage = galse, sfo = false */

            if ((forelderTil != null && !millenial && !barn && !barnehage && !sfo) ||
                (forelderTil == null && millenial && !barn && !barnehage && !sfo) ||
                (forelderTil == null && !millenial && !barn && !barnehage && !sfo) ||
                (forelderTil == null && !millenial && barn && !barnehage && !sfo) ||
                (forelderTil == null && !millenial && barn && barnehage && !sfo) ||
                (forelderTil == null && !millenial && barn && !barnehage && sfo)) {

                return createRandomFnr(alder = alder, forelderTil = forelderTil, kjonn = kjonn, millenial = millenial,
                    barn = barn, voksen = voksen, barnehage = barnehage, sfo = sfo)
            } else {
                throw Exception("Ugyldig kombinasjon av millenial, barn, barnehage og SFO!")
            }
        }

        private fun getRandomFdato(alder: Int? = null,
                                   kjonn: Enums.Kjonn = Enums.Kjonn.entries.toTypedArray().random(),
                                   forelderTil: String? = null,
                                   millenial: Boolean = false,
                                   barn: Boolean = false,
                                   voksen: Boolean = false,
                                   barnehage: Boolean = false,
                                   sfo: Boolean = false): String =
            FnrUtils.getFodselsdatoFraFnr(getRandomFnr(alder = alder, kjonn = kjonn, forelderTil = forelderTil,
                millenial = millenial, barn = barn, voksen = voksen, barnehage = barnehage, sfo = sfo))

        private fun createRandomFnr(alder: Int? = null,
                                    forelderTil: String?,
                                    kjonn: Enums.Kjonn,
                                    millenial: Boolean,
                                    barn: Boolean,
                                    voksen: Boolean,
                                    barnehage: Boolean,
                                    sfo: Boolean): String {
            while (true) {
                val fdag = (1..28).random().toString().padStart(2, '0')
                val fmaned = (1..12).random().toString().padStart(2, '0')
                val far = getAr(alder = alder, forelderTil = forelderTil, millenial = millenial, barn = barn,
                    voksen = voksen, barnehage = barnehage, sfo = sfo)
                    .toString().padStart(2, '0')

                // TODO: Mulig det må gjøres noe her ifm. voksen = true...
                val milleniumtall = when {
                    millenial -> (6..9).random()
                    barn -> (5..9).random()
                    else -> (1..5).random()
                }

                val tilfeldigTall = (0..9).random()

                val kjonntall = when (kjonn) {
                    Enums.Kjonn.MANN -> listOf(1, 3, 5, 7, 9).random()
                    else -> listOf(0, 2, 4, 6, 8).random()
                }

                val baseNumber = "$fdag$fmaned$far$milleniumtall$tilfeldigTall$kjonntall"

                val tallrekkeT = listOf(3, 7, 6, 1, 8, 9, 4, 5, 2)
                val tallrekkeF = listOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2)

                val T = getKontrollsiffer(baseNumber, tallrekkeT) ?: continue

                val F = getKontrollsiffer("$baseNumber$T", tallrekkeF) ?: continue

                return "$baseNumber$T$F"
            }
        }

        private fun getAr(alder: Int? = null,
                          forelderTil: String?,
                          millenial: Boolean,
                          barn: Boolean,
                          voksen: Boolean,
                          barnehage: Boolean,
                          sfo: Boolean): Int = when {
            alder != null -> if (millenial) {
                iar.toInt() - alder
            } else {
                100 - (alder - 25)
            }
            forelderTil != null -> getFodselsarForelder(forelderTil)
            millenial -> (0..iar.toInt()).random()
            barn -> when {
                barnehage -> iar.toInt() - (1..5).random()
                sfo -> (iar.toInt() - 9..iar.toInt() - 6).random()
                else -> iar.toInt() - (1..17).random()
            }
            voksen -> (18..100).random()
            else -> (0..99).random()
        }

        private fun getFodselsarForelder(forelderTil: String?): Int =
            forelderTil
                ?.substring(4, 6)
                ?.toIntOrNull()
                ?.let { barnetsAar ->
                    val iarInt = iar.toInt()
                    val barnetsFulleAar = if (barnetsAar <= iarInt) 2000 + barnetsAar else 1900 + barnetsAar
                    (barnetsFulleAar - 40..barnetsFulleAar - 18).random() % 100
                }
                ?: 0

        private fun getKontrollsiffer(fnr: String, tallrekke: List<Int>): Int? {
            val sum = fnr.mapIndexed { i, char ->
                char.digitToInt() * tallrekke[i]
            }.sum()

            return when (val rest = sum % 11) {
                0 -> 0
                1 -> null
                else -> 11 - rest
            }
        }

    }
}