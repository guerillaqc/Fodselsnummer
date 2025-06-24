package org.guerillaqc.fnr

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle

object FnrValidator {
    fun erGyldig(fnr: String): Boolean {
        val renset = fnr.filter { it.isDigit() }
        if (renset.length != 11) return false

        val digits = renset.map { it.digitToInt() }

        if (!gyldigDato(digits)) return false
        if (!gyldigeKontrollsifre(digits)) return false

        return true
    }

    private fun gyldigDato(digits: List<Int>): Boolean {
        val day = digits[0] * 10 + digits[1]
        val month = digits[2] * 10 + digits[3]
        val year = digits[4] * 10 + digits[5]
        val individnummer = digits[6] * 100 + digits[7] * 10 + digits[8]

        val fullYear = when {
            individnummer in 0..499 -> 1900 + year
            individnummer in 500..749 && year >= 54 -> 1800 + year
            individnummer in 500..999 && year < 40 -> 2000 + year
            individnummer in 900..999 && year >= 40 -> 1900 + year
            else -> return false
        }

        val trueDay = if (day in 41..71) day - 40 else day // D-nummer
        val trueMonth = if (month in 41..52) month - 40 else month // H-nummer ikke støttet ennå

        return try {
            LocalDate.of(fullYear, trueMonth, trueDay)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun gyldigeKontrollsifre(digits: List<Int>): Boolean {
        val k1 = beregnKontrollsiffer(digits, listOf(3, 7, 6, 1, 8, 9, 4, 5, 2))
        if (k1 == 10 || digits[9] != k1) return false

        val k2 = beregnKontrollsiffer(digits, listOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2))
        return k2 != 10 && digits[10] == k2
    }

    private fun beregnKontrollsiffer(digits: List<Int>, vekter: List<Int>): Int {
        val sum = digits.zip(vekter).sumOf { it.first * it.second }
        val rest = sum % 11
        return if (rest == 0) 0 else 11 - rest
    }
}