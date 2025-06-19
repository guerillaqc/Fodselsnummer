package org.guerillaqc.fnr.utils

import java.util.Random

fun String.requireValidFnr(): String = apply {
    require(length == 11 && all { it.isDigit() }) {
        "Fnr må være eksakt 11 siffer, men var '$this' ($length tegn)"
    }
}

fun String.requireValidFdatoString(): String = apply {
    require(length == 8 && all { it.isDigit() }) {
        "Fdato må være eksakt 8 siffer, men var '$this' ($length tegn)"
    }
}

fun String.requireValidDag(): String = apply {
    require(length == 2 && all { it.isDigit() }) {
        "Dag må være eksakt 2 siffer, men var '$this' ($length tegn)"
    }
    val dayValue = toInt()
    require(dayValue in 1..31) {
        "Dag må være mellom 01 og 31, men var '$this' ($dayValue)"
    }
}

fun String.requireValidMnd(): String = apply {
    require(length == 2 && all { it.isDigit() }) {
        "Mnd må være eksakt 2 siffer, men var '$this' ($length tegn)"
    }
    val dayValue = toInt()
    require(dayValue in 1..12) {
        "Mnd må være mellom 01 og 12, men var '$this' ($dayValue)"
    }
}

//TODO: Se på om det er formålsmessig å begrense år til realistiske årstall også. Må tilpasses genererte fnr/dfato.
fun String.requireValidAr(): String = apply {
    require(length == 4 && all { it.isDigit() }) {
        "År må være eksakt 4 siffer, men var '$this' ($length tegn)"
    }
}

class HelperUtils {
    companion object {

        private var randomizer = Random()

        fun <T : Enum<*>> getRandomEnum(clazz: Class<T>): T {
            val x = randomizer.nextInt(clazz.enumConstants.size)
            return clazz.enumConstants[x]
        }

    }
}
