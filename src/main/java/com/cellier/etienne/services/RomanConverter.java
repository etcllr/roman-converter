package com.cellier.etienne.services;

import java.util.HashMap;
import java.util.Map;

public class RomanConverter {
    private static final Map<Character, Integer> romanValues = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
        put('G', 5000);
        put('H', 10000);
        put('J', 50000);
        put('K', 100000);
        put('F', 500000);
        put('P', 1000000);
    }};

    private static final Map<Character, String> symbolDescriptions = new HashMap<>() {{
        put('I', "1");
        put('V', "5");
        put('X', "10");
        put('L', "50");
        put('C', "100");
        put('D', "500");
        put('M', "1 000");
        put('G', "5 000");
        put('H', "10 000");
        put('J', "50 000");
        put('K', "100 000");
        put('F', "500 000");
        put('P', "1 000 000");
    }};

    public static Map<Character, Integer> getAllSymbols() {
        return new HashMap<>(romanValues);
    }

    public static String getValueDescription(char symbol) {
        return symbolDescriptions.getOrDefault(Character.toUpperCase(symbol), "Symbole inconnu");
    }

    public static int convertRomanToArabicNumbers(String romanValue) {
        if (romanValue == null || romanValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nombre ne peut pas être vide");
        }

        String upperRomanValue = romanValue.toUpperCase();
        int result = 0;

        for (int i = 0; i < upperRomanValue.length(); i++) {
            Character currentChar = upperRomanValue.charAt(i);
            if (!romanValues.containsKey(currentChar)) {
                throw new IllegalArgumentException("Caractère invalide trouvé: " + currentChar);
            }

            int currentValue = romanValues.get(currentChar);

            if (i < upperRomanValue.length() - 1) {
                Character nextChar = upperRomanValue.charAt(i + 1);
                if (!romanValues.containsKey(nextChar)) {
                    throw new IllegalArgumentException("Caractère invalide trouvé: " + nextChar);
                }

                int nextValue = romanValues.get(nextChar);

                if (currentValue < nextValue) {
                    result += (nextValue - currentValue);
                    i++;
                    continue;
                }
            }

            result += currentValue;
        }

        if (result > 1000000) {
            throw new IllegalArgumentException("La valeur dépasse le maximum autorisé (1 million)");
        }

        return result;
    }
}
