package com.driss.pokemon.util.extensions

import org.junit.Assert.*
import org.junit.Test

class IntExtensionKtTest{

    @Test
    fun `test format value contained between 0 and 100 expect success`(){
        val entry1 = 15
        val expected1 = 0.15f
        assertTrue(entry1.formatPercentage() == expected1)
        val entry2 = 0
        val expected2 = 0f
        assertTrue(entry2.formatPercentage() == expected2)
        val entry3 = 100
        val expected3 = 1f
        assertTrue(entry3.formatPercentage() == expected3)
    }

    @Test
    fun `test format negative values expect 0f as a result`(){
        val entry1 = -15
        val expected1 = 0f
        assertTrue(entry1.formatPercentage() == expected1)
        val entry2 = -210
        val expected2 = 0f
        assertTrue(entry2.formatPercentage() == expected2)
    }

    @Test
    fun `test over 100 values expect 1f as a result`(){
        val entry1 = 101
        val expected1 = 1f
        assertTrue(entry1.formatPercentage() == expected1)
        val entry2 = 3999
        val expected2 = 1f
        assertTrue(entry2.formatPercentage() == expected2)
    }

}