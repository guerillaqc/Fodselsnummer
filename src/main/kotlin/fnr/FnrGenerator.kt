package org.guerillaqc.fnr

import org.guerillaqc.fnr.utils.Enums
import org.guerillaqc.fnr.utils.FnrUtils
import org.guerillaqc.fnr.utils.HelperUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class FnrGenerator {
    companion object {
        private val iar: String = DateTimeFormatter.ofPattern("yy").format(LocalDateTime.now())

        fun tilfeldigFodselsnummer(): String = getRandomFnr()

        fun tilfeldigFodselsdato(): String = getRandomFdato()

        fun tilfeldigFodselsnummerKvinne(): String = tilfeldigFodselsnummerKjonn(Enums.Kjonn.KVINNE)

        fun tilfeldigFodselsnummerMann(): String = tilfeldigFodselsnummerKjonn(Enums.Kjonn.MANN)

        fun tilfeldigFodselsnummerKjonn(kjonn: Enums.Kjonn): String = getRandomFnr(kjonn = kjonn)

        fun tilfeldigFodselsnummerBarn(): String = getRandomFnr(barn = true)

        fun tilfeldigFodselsdatoBarn(): String = getRandomFdato(barn = true)

        fun tilfeldigFodselsnummerJente(): String = getRandomFnr(barn = true, kjonn = Enums.Kjonn.KVINNE)

        fun tilfeldigFodselsnummerGutt(): String = getRandomFnr(barn = true, kjonn = Enums.Kjonn.MANN)

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

        fun tilfeldigFodselsnummerAlderMellom(alderFra: Int, alderTil: Int): String {
            return Random.nextInt(alderFra, alderTil + 1)
                .let { alder -> getRandomFnr(alder = alder, millenial = alder <= iar.toInt()) }
        }

        fun tilfeldigFodselsdatoAlder(alder: Int): String = getRandomFdato(alder = alder, millenial = alder <= iar.toInt())

        private fun getRandomFnr(alder: Int? = null,
                                 kjonn: Enums.Kjonn = HelperUtils.getRandomEnum(Enums.Kjonn::class.java),
                                 forelderTil: String? = null,
                                 millenial: Boolean = false,
                                 barn: Boolean = false,
                                 voksen: Boolean = false,
                                 barnehage: Boolean = false,
                                 sfo: Boolean = false): String =
            listOf(
                forelderTil != null && !millenial && !barn && !barnehage && !sfo,
                forelderTil == null && millenial && !barn && !barnehage && !sfo,
                forelderTil == null && !millenial && !barn && !barnehage && !sfo,
                forelderTil == null && !millenial && barn && !barnehage && !sfo,
                forelderTil == null && !millenial && barn && barnehage && !sfo,
                forelderTil == null && !millenial && barn && !barnehage && sfo
            ).any { it }
                .let { isValid ->
                    if (isValid) {
                        createRandomFnr(alder = alder, forelderTil = forelderTil, kjonn = kjonn, millenial = millenial,
                            barn = barn, voksen = voksen, barnehage = barnehage, sfo = sfo)
                    } else {
                        throw Exception("Ugyldig kombinasjon av millenial, barn, barnehage og SFO!")
                    }
                }

        private fun getRandomFdato(alder: Int? = null,
                                   kjonn: Enums.Kjonn = Enums.Kjonn.entries.random(),
                                   forelderTil: String? = null,
                                   millenial: Boolean = false,
                                   barn: Boolean = false,
                                   voksen: Boolean = false,
                                   barnehage: Boolean = false,
                                   sfo: Boolean = false): String =
            getRandomFnr(alder = alder, kjonn = kjonn, forelderTil = forelderTil,
                millenial = millenial, barn = barn, voksen = voksen, barnehage = barnehage, sfo = sfo)
                .let { FnrUtils.finnFodselsdatoFraFodselsnummer(it) }

        private fun isLeapYear(year: Int): Boolean =
            year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

        private fun getValidDayForMonth(month: Int, year: Int): Int =
            when (month) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> if (isLeapYear(year)) 29 else 28
                else -> 28
            }.let { daysInMonth -> (1..daysInMonth).random() }

        private fun createRandomFnr(
            alder: Int? = null,
            forelderTil: String?,
            kjonn: Enums.Kjonn,
            millenial: Boolean,
            barn: Boolean,
            voksen: Boolean,
            barnehage: Boolean,
            sfo: Boolean
        ): String =
            generateSequence {
                val fmaned = (1..12).random()
                val far = getAr(
                    alder = alder,
                    forelderTil = forelderTil,
                    millenial = millenial,
                    barn = barn,
                    voksen = voksen,
                    barnehage = barnehage,
                    sfo = sfo
                )
                val fullYear = if (far <= iar.toInt()) 2000 + far else 1900 + far
                val fdag = getValidDayForMonth(fmaned, fullYear)

                val farFormatted = far.toString().padStart(2, '0')
                val fdagFormatted = fdag.toString().padStart(2, '0')
                val fmanedFormatted = fmaned.toString().padStart(2, '0')

                var individnummer: Int
                do {
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

                    individnummer = "$milleniumtall$tilfeldigTall$kjonntall".toInt()
                } while (individnummer in 500..749) // Forkast ugyldige individnummer

                "$fdagFormatted$fmanedFormatted$farFormatted${individnummer.toString().padStart(3, '0')}"
            }
                .mapNotNull { baseNumber ->
                    val tallrekkeT = listOf(3, 7, 6, 1, 8, 9, 4, 5, 2)
                    val tallrekkeF = listOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2)

                    getKontrollsiffer(baseNumber, tallrekkeT)?.let { T ->
                        getKontrollsiffer("$baseNumber$T", tallrekkeF)?.let { F ->
                            "$baseNumber$T$F"
                        }
                    }
                }
                .first()

        private fun getAr(alder: Int? = null,
                          forelderTil: String?,
                          millenial: Boolean,
                          barn: Boolean,
                          voksen: Boolean,
                          barnehage: Boolean,
                          sfo: Boolean): Int = when {
            alder != null -> LocalDateTime.now().year.let { currentYear ->
                (currentYear - alder) % 100
            }
            forelderTil != null -> getFodselsarForelder(forelderTil)
            millenial -> (0..iar.toInt()).random()
            barn -> when {
                barnehage -> iar.toInt() - (1..5).random()
                sfo -> (iar.toInt() - 9..iar.toInt() - 6).random()
                else -> iar.toInt() - (1..17).random()
            }
            voksen -> LocalDateTime.now().year.let { currentYear ->
                val adultAge = (18..80).random()
                (currentYear - adultAge) % 100
            }
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

        private fun getKontrollsiffer(fnr: String, tallrekke: List<Int>): Int? =
            fnr.mapIndexed { i, char -> char.digitToInt() * tallrekke[i] }
                .sum()
                .let { sum ->
                    when (val rest = sum % 11) {
                        0 -> 0
                        1 -> null
                        else -> 11 - rest
                    }
                }
    }
}
