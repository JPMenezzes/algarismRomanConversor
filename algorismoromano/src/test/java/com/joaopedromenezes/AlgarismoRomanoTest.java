package com.joaopedromenezes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AlgarismoRomanoTest {
    /*
     * Inteiro para Romano
     */
    AlgarismoRomano algarismoRomano;

    @BeforeEach
    void setUp() {
        algarismoRomano = new AlgarismoRomano();
    }

    @Test
    @DisplayName("Inteiro igual ou menor que zero")
    public void testDecimalParaRomanoComZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            algarismoRomano.inteiroParaRomano(0);
        });
    }

    @Test
    @DisplayName("Inteiro maior que 3999")
    public void testDecimalParaRomanoCom3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            algarismoRomano.inteiroParaRomano(4000);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, I",
            "4, IV",
            "9, IX",
            "49, XLIX",
            "3999, MMMCMXCIX",
            "100, C",
            "500, D",
            "1000, M",
            "1500, MD",
            "1984, MCMLXXXIV"
    })
    @DisplayName("Conversão de número decimal para algarismos romanos")
    public void testDecimalParaRomano(int decimal, String romano) {
        String resultado = algarismoRomano.inteiroParaRomano(decimal);
        assertEquals(romano, resultado);
    }

    /*
     * Romano para Inteiro
     */

    @Test
    @DisplayName("Conversão de algarismo romano para números inteiros")
    public void testRomanoParaInteiro() {
        assertEquals(3, algarismoRomano.romanoParaInteiro("III"));
        assertEquals(30, algarismoRomano.romanoParaInteiro("XXX"));
        assertEquals(2000, algarismoRomano.romanoParaInteiro("MM"));
        assertEquals(4, algarismoRomano.romanoParaInteiro("IV"));
        assertEquals(3, algarismoRomano.romanoParaInteiro("III"));
        assertEquals(9, algarismoRomano.romanoParaInteiro("IX"));
        assertEquals(40, algarismoRomano.romanoParaInteiro("XL"));
        assertEquals(90, algarismoRomano.romanoParaInteiro("XC"));
        assertEquals(400, algarismoRomano.romanoParaInteiro("CD"));
        assertEquals(900, algarismoRomano.romanoParaInteiro("CM"));
        assertEquals(1984, algarismoRomano.romanoParaInteiro("MCMLXXXIV"));
    }

    @Test
    @DisplayName("Conversões Incorretas")
    public void testRomanoParaInteiroFailed() {
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("IIII")); // Repetição maior que 3
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("XXXX")); // Repetição maior que 3
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("CCCC")); // Repetição maior que 3
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("MMMM")); // Repetição maior que 3
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("IIV")); // Subtração inválida
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("LC")); // L = 50 antes de quantidade maior (C = 100)
        assertThrows(IllegalArgumentException.class, () -> algarismoRomano.romanoParaInteiro("DM")); // D(500) antes de M(1000)
    }
}
