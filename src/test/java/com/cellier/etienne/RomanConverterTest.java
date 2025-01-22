package com.cellier.etienne;

import com.cellier.etienne.services.RomanConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RomanConverterTest {

    @ParameterizedTest
    @CsvSource({
            "I, 1",
            "V, 5",
            "X, 10",
            "L, 50",
            "C, 100",
            "D, 500",
            "M, 1000",
            "G, 5000",
            "H, 10000",
            "J, 50000",
            "K, 100000",
            "F, 500000",
            "P, 1000000"
    })
    void convertRomanToArabicNumbers_SingleCharacter_ReturnsCorrectValue(String roman, int expected) {
        int result = RomanConverter.convertRomanToArabicNumbers(roman);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "IV, 4",      // 5 - 1
            "IX, 9",      // 10 - 1
            "XL, 40",     // 50 - 10
            "XC, 90",     // 100 - 10
            "CD, 400",    // 500 - 100
            "CM, 900",    // 1000 - 100
            "IM, 999",    // 1000 - 1
            "XM, 990",    // 1000 - 10
            "IH, 9999",   // 10000 - 10
            "XP, 999990"  // 1000000 - 10
    })
    void convertRomanToArabicNumbers_SubtractionCases_ReturnsCorrectValue(String roman, int expected) {
        int result = RomanConverter.convertRomanToArabicNumbers(roman);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "MMXXIV, 2024",
            "MCMLXXXIX, 1989",
            "MMMDCCCLXXXVIII, 3888",
            "HG, 15000",           // 5000 + 10000
            "HGDCCLXXVII, 15777",  // (5000 + 10000) + 500 + 200 + 70 + 7
            "KKK, 300000"          // 100000 + 100000 + 100000
    })
    void convertRomanToArabicNumbers_ComplexNumbers_ReturnsCorrectValue(String roman, int expected) {
        int result = RomanConverter.convertRomanToArabicNumbers(roman);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void convertRomanToArabicNumbers_EmptyOrWhitespace_ThrowsArgumentException(String roman) {
        assertThrows(IllegalArgumentException.class, () ->
                RomanConverter.convertRomanToArabicNumbers(roman));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC", "123", "XVI2"})
    void convertRomanToArabicNumbers_InvalidCharacters_ThrowsArgumentException(String roman) {
        assertThrows(IllegalArgumentException.class, () ->
                RomanConverter.convertRomanToArabicNumbers(roman));
    }

    @Test
    void getValueDescription_ValidSymbol_ReturnsCorrectDescription() {
        String description = RomanConverter.getValueDescription('M');
        assertEquals("1 000", description);
    }

    @Test
    void getValueDescription_InvalidSymbol_ReturnsUnknownMessage() {
        String description = RomanConverter.getValueDescription('Z');
        assertEquals("Symbole inconnu", description);
    }

    @Test
    void getAllSymbols_ReturnsCorrectNumberOfSymbols() {
        var symbols = RomanConverter.getAllSymbols();
        assertEquals(13, symbols.size());
    }
}
