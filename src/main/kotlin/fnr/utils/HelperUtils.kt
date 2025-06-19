package org.guerillaqc.fnr.utils

import java.util.Random

class HelperUtils {
    companion object {

        private var randomizer = Random()

        fun <T : Enum<*>> getRandomEnum(clazz: Class<T>): T {
            val x = randomizer.nextInt(clazz.enumConstants.size)
            return clazz.enumConstants[x]
        }

    }
}
