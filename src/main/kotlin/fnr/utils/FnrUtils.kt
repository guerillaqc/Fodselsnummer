package org.guerillaqc.fnr.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import kotlin.text.substring
import kotlin.text.toInt

class FnrUtils {
    companion object {
        private val iar: String = Integer.parseInt(DateTimeFormatter.ofPattern("yy")
            .format(LocalDateTime.now())).toString()

        fun getFnrFormatert(fnr: String): String {
            return fnr.substring(0, 6) + " " + fnr.substring(6)
        }

        fun getAlderFraFnr(fnr: String): Int =
            getAlderFraFdato(getFodselsdatoFraFnr(fnr)) +
                    if (fnr.substring(3, 6).toInt() > 24 && fnr.substring(6, 9).toInt() > 500) 100 else 0

        /*fun getAlderFraFdato(fdato: String): Int = getAlderFraFdato(fdato.substring(0,2).toInt(),
            fdato.substring(2,4).toInt(), fdato.substring(4,8).toInt())

        fun getAlderFraFdato(dayOfMonth: Int, month: Int, year: Int): Int = Period.between(
            LocalDate.of(year, month, dayOfMonth), LocalDate.now()).years*/
        //Fjern utkommentert over når 100% sikker på versjonen under...
        fun getAlderFraFdato(fdatoOrDay: Any, month: Int? = null, year: Int? = null): Int {
            return when {
                fdatoOrDay is String && month == null && year == null -> {
                    getAlderFraFdato(
                        fdatoOrDay.substring(0, 2).toInt(),
                        fdatoOrDay.substring(2, 4).toInt(),
                        fdatoOrDay.substring(4, 8).toInt()
                    )
                }
                fdatoOrDay is Int && month != null && year != null -> {
                    Period.between(LocalDate.of(year, month, fdatoOrDay), LocalDate.now()).years
                }
                else -> throw IllegalArgumentException("Ugyldig(e) argument(er).")
            }
        }

        fun getFodselsdatoFraFnr(fnr: String): String =
            "${fnr.substring(0, 4)}${if (fnr.substring(4, 6) >= iar) "19" else "20"}${fnr.substring(4, 6)}"

        fun getKjonnFraFnr(fnr: String): Enums.Kjonn =
            if (fnr.substring(8, 9).toInt() % 2 == 0) Enums.Kjonn.KVINNE else Enums.Kjonn.MANN

        /*private fun endreArFormat(ar: String): String = when (ar.length) {
            4 -> ar.removeRange(0, 1)
            2 -> if (ar.toInt() < 50) { "20$ar" } else { "19$ar" }
            else -> "Feil format. Lovlig årstallformat er YY eller YYYY"
        }*/

    }
}
