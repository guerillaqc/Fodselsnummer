package io.github.guerillaqc.fnr.utils

import io.github.guerillaqc.fnr.FnrGenerator
import io.github.guerillaqc.fnr.FnrGenerator.Companion.getKontrollsiffer
import io.github.guerillaqc.fnr.FnrValidator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import kotlin.text.substring
import kotlin.text.toInt

class FnrUtils {
    companion object {
        private val iar: String = DateTimeFormatter.ofPattern("yy").format(LocalDateTime.now())

        fun formaterFodselsnummer(fnr: String, skilletegn: String = " "): String =
            fnr.requireValidFnr()
                .let { it.substring(0, 6) + skilletegn + it.substring(6) }

        fun formaterFodselsdato(fdato: String, skilletegn: String = " "): String =
            fdato.requireValidFdatoString()
                .let { it.substring(0, 2) + skilletegn + it.substring(2, 4) + skilletegn + it.substring(4, 8) }

        fun alderFraFodselsnummer(fnr: String): Int =
            alderFraFodselsdato(fodselsdatoFraFodselsnummer(fnr.requireValidFnr()))

        fun alderFraFodselsdato(fdatoEllerDag: Any, mnd: Int? = null, ar: Int? = null): Int = when {
            fdatoEllerDag is String && mnd == null && ar == null ->
                fdatoEllerDag.requireValidFdatoString()
                    .let { fdato ->
                        alderFraFodselsdato(
                            fdato.substring(0, 2).toInt(),
                            fdato.substring(2, 4).toInt(),
                            fdato.substring(4, 8).toInt()
                        )
                    }

            fdatoEllerDag is Int && mnd != null && ar != null ->
                Triple(fdatoEllerDag, mnd, ar)
                    .let { (dag, mnd, ar) ->
                        LocalDate.of(
                            ar.toString().requireValidAr().toInt(),
                            mnd.toString().padStart(2, '0').requireValidMnd().toInt(),
                            dag.toString().padStart(2, '0').requireValidDag().toInt()
                        )
                    }
                    .let { Period.between(it, LocalDate.now()).years }

            else -> throw IllegalArgumentException("Ugyldig(e) argument(er).")
        }

        fun fodselsdatoFraFodselsnummer(fnr: String): String =
            fnr.requireValidFnr()
                .let {
                    val yearPart = it.substring(4, 6).toInt()
                    val currentYear = iar.toInt()
                    val century = if (yearPart <= currentYear) "20" else "19"
                    "${it.substring(0, 4)}$century${it.substring(4, 6)}"
                }

        fun kjonnFraFodselsnummer(fnr: String): Enums.Kjonn =
            fnr.requireValidFnr()
                .let { if (it.substring(8, 9).toInt() % 2 == 0) Enums.Kjonn.KVINNE else Enums.Kjonn.MANN }

        fun konverterTilSyntetiskFnr(fnr: String): String? =
            fnr.takeIf { it.length == 11 }
                ?.let { validFnr ->
                    validFnr.substring(2, 4).toIntOrNull()
                        ?.takeIf { it in 1..12 }
                        ?.let { opprinneligManed ->
                            val syntetiskManed = (79 + opprinneligManed).toString().padStart(2, '0')
                            "${validFnr.take(2)}$syntetiskManed${validFnr.drop(4)}"
                        }
                }

        /*fun konverterFraSyntetisk(fnr: String): String? =
            fnr.takeIf { it.length == 11 }
                ?.let { validFnr ->
                    validFnr.substring(2, 4).toIntOrNull()
                        ?.takeIf { it in 80..91 }
                        ?.let { syntetiskManed ->
                            val vanligManed = (syntetiskManed - 79).toString().padStart(2, '0')
                            "${validFnr.take(2)}$vanligManed${validFnr.drop(4)}"
                        }
                }*/
        fun konverterFraSyntetiskFnr(fnr: String): String? =
            fnr.takeIf { it.length == 11 }
                ?.let { validFnr ->
                    validFnr.substring(2, 4).toIntOrNull()
                        ?.takeIf { it in 80..91 }
                        ?.let { syntetiskManed ->
                            val vanligManed = (syntetiskManed - 79).toString().padStart(2, '0')
                            val baseNumber = "${validFnr.take(2)}$vanligManed${validFnr.substring(4, 9)}"

                            val tallrekkeT = listOf(3, 7, 6, 1, 8, 9, 4, 5, 2)
                            val tallrekkeF = listOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2)

                            val T = getKontrollsiffer(baseNumber, tallrekkeT) ?: return null
                            val F = getKontrollsiffer("$baseNumber$T", tallrekkeF) ?: return null

                            "$baseNumber$T$F"
                        }
                }

    }

}
