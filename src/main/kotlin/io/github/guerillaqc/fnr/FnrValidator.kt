package io.github.guerillaqc.fnr

import java.time.LocalDate

object FnrValidator {

    fun erGyldig(fnr: String): Boolean {
        val renset = fnr.filter { it.isDigit() }
        if (renset.length != 11) return false

        val numre = renset.map { it.digitToInt() }

        if (!gyldigDato(numre)) return false
        if (!gyldigeKontrollsifre(numre)) return false

        return true
    }

    fun erGyldigSyntetisk(fnr: String): Boolean {
        if (fnr.length != 11) return false
        val maned = fnr.substring(2, 4).toIntOrNull() ?: return false
        return maned in 80..99
    }

    fun erGyldigFodselsdato(fdato: String): Boolean {
        if (fdato.length != 8) return false

        val dag = fdato.substring(0, 2).toIntOrNull() ?: return false
        val maned = fdato.substring(2, 4).toIntOrNull() ?: return false
        val ar = fdato.substring(4, 8).toIntOrNull() ?: return false

        if (maned !in 1..12) return false

        return when (maned) {
            1, 3, 5, 7, 8, 10, 12 -> dag in 1..31
            4, 6, 9, 11 -> dag in 1..30
            2 -> {
                val erSkuddar = (ar % 4 == 0 && ar % 100 != 0) || (ar % 400 == 0)
                if (erSkuddar) dag in 1..29 else dag in 1..28
            }
            else -> false
        }
    }

    private fun gyldigDato(numre: List<Int>): Boolean {
        val dag = numre[0] * 10 + numre[1]
        val mnd = numre[2] * 10 + numre[3]
        val ar = numre[4] * 10 + numre[5]
        val individnummer = numre[6] * 100 + numre[7] * 10 + numre[8]

        val fulltAr = when {
            individnummer in 0..499 -> 1900 + ar
            individnummer in 500..749 && ar >= 54 -> 1800 + ar
            individnummer in 500..999 && ar < 40 -> 2000 + ar
            individnummer in 900..999 && ar >= 40 -> 1900 + ar
            else -> return false
        }

        val fagktiskDag = if (dag in 41..71) dag - 40 else dag
        val faktiskMnd = if (mnd in 41..52) mnd - 40 else mnd

        return try {
            LocalDate.of(fulltAr, faktiskMnd, fagktiskDag)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun gyldigeKontrollsifre(numre: List<Int>): Boolean {
        val k1 = beregnKontrollsiffer(numre, listOf(3, 7, 6, 1, 8, 9, 4, 5, 2))
        if (k1 == 10 || numre[9] != k1) return false

        val k2 = beregnKontrollsiffer(numre, listOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2))
        return k2 != 10 && numre[10] == k2
    }

    private fun beregnKontrollsiffer(numre: List<Int>, vekter: List<Int>): Int {
        val sum = numre.zip(vekter).sumOf { it.first * it.second }
        val rest = sum % 11
        return if (rest == 0) 0 else 11 - rest
    }
}