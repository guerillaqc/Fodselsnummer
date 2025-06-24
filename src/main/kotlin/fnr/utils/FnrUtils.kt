package org.guerillaqc.fnr.utils

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
    }
}

/*class FnrUtils {
    companion object {
        private val iar: String = Integer.parseInt(DateTimeFormatter.ofPattern("yy")
            .format(LocalDateTime.now())).toString()

        fun formaterFodselsnummer(fnr: String, skilletegn: String = " "): String {
            return fnr.requireValidFnr()
                .let { it.substring(0, 6) + skilletegn + it.substring(6) }
        }

        fun formaterFodselsdato(fdato: String, skilletegn: String = " "): String {
            return fdato.requireValidFdatoString()
                .let { it.substring(0, 2) + skilletegn + it.substring(2, 4) + skilletegn + it.substring(4, 8) }
        }

        fun alderFraFodselsnummer(fnr: String): Int = finnAlderFraFodselsdato(finnFodselsdatoFraFodselsnummer(fnr.requireValidFnr()))

        fun finnAlderFraFodselsdato(fdatoEllerDag: Any, mnd: Int? = null, ar: Int? = null): Int = when {
            fdatoEllerDag is String && mnd == null && ar == null ->
                fdatoEllerDag.requireValidFdatoString()
                    .let { fdato ->
                        finnAlderFraFodselsdato(
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

        /*fun finnFodselsdatoFraFodselsnummer(fnr: String): String =
            fnr.requireValidFnr()
                .let { "${it.substring(0, 4)}${if (it.substring(4, 6) >= iar) "19" else "20"}${it.substring(4, 6)}" }*/
        fun finnFodselsdatoFraFodselsnummer(fnr: String): String =
            fnr.requireValidFnr()
                .let {
                    val yearPart = it.substring(4, 6).toInt()
                    val currentYear = iar.toInt()
                    val century = if (yearPart <= currentYear) "20" else "19"
                    "${it.substring(0, 4)}$century${it.substring(4, 6)}"
                }

        fun finnKjonnFraFodselsnummer(fnr: String): Enums.Kjonn =
            fnr.requireValidFnr()
                .let { if (it.substring(8, 9).toInt() % 2 == 0) Enums.Kjonn.KVINNE else Enums.Kjonn.MANN }

        //TODO: Se om den e har noe for seg å bruke:
        /*private fun endreArFormat(ar: String): String = when (ar.length) {
            4 -> ar.removeRange(0, 1)
            2 -> if (ar.toInt() < 50) { "20$ar" } else { "19$ar" }
            else -> "Feil format. Lovlig årstallformat er YY eller YYYY"
        }*/

    }
}*/
