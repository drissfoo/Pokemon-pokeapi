package com.driss.pokemon.util.extensions

import org.junit.Assert.*
import org.junit.Test

class StringExtensionKtTest{

    @Test
    fun `test capitalize first is capitalizing successfully a string`(){
        val entry1 = "attack-speed"
        val expected1 = "Attack-speed"
        assertTrue(entry1.capitalizeFirst() == expected1)
        val entry2 = "-hp"
        val expected2 = "-hp"
        assertTrue(entry2.capitalizeFirst() == expected2)
    }

    @Test
    fun `test format and capitalize is changing successfully a string`(){
        val entry1 = "attack-speed"
        val expected1 = "Attack speed"
        assertTrue(entry1.formatCapitalize() == expected1)
        val entry2 = "-hp"
        val expected2 = "Hp"
        println(entry2.formatCapitalize())
        assertTrue(entry2.formatCapitalize() == expected2)
    }

}