package com.cellier.etienne;

import com.cellier.etienne.services.RomanConverter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRENCH);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            displaySymbolGuide();

            String response;
            do {
                if (!askAndConvertRomanValue(scanner)) {
                    break;  // Sortir si une erreur de lecture survient
                }

                System.out.println("\nVoulez-vous convertir un autre nombre romain? (O/N)");
                response = readLine(scanner);
                if (response == null) break;

            } while (response.toUpperCase().equals("O"));

        } catch (Exception ex) {
            System.out.println("Une erreur inattendue s'est produite: " + ex.getMessage());
        } finally {
            System.out.println("\nProgramme terminé.");
            scanner.close();
        }
    }

    private static void displaySymbolGuide() {
        System.out.println("Guide des symboles disponibles :");
        System.out.println("--------------------------------");
        for (Map.Entry<Character, Integer> symbol : RomanConverter.getAllSymbols().entrySet()) {
            System.out.println(symbol.getKey() + " = " + RomanConverter.getValueDescription(symbol.getKey()));
        }
        System.out.println("--------------------------------\n");
    }

    private static boolean askAndConvertRomanValue(Scanner scanner) {
        System.out.println("Entrez un nombre romain:");
        String input = readLine(scanner);

        if (input == null) return false;

        if (input.trim().isEmpty()) {
            System.out.println("Vous devez entrer une valeur.");
            return true;
        }

        try {
            int resultat = RomanConverter.convertRomanToArabicNumbers(input);
            System.out.println("La valeur en chiffres arabes est: " + numberFormat.format(resultat));
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Erreur: " + ex.getMessage());
            return true;
        }
    }

    private static String readLine(Scanner scanner) {
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
