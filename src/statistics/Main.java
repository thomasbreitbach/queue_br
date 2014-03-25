package statistics;

import java.io.FileNotFoundException;

/**
 * Diese Klasse liest die Zufallszahlen aus der Datei {@code input.txt} und
 * berechnet einige statistische Kennzahlen.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Statistics st = new Statistics("input.txt");
            print("Anzahl:     ", st.size());
            print("Mittelwert: ", st.mean());
            print("Fehler:     ", st.mse());
            print("Breite:     ", st.std());
        }
        catch (FileNotFoundException e) {
            System.err.println("Die Datei input.txt wurde nicht gefunden.");
            System.err.println("Zuerst die Klasse Generator ausfuehren!");
        }
    }

    private static void print(String text, double value) {
        System.out.printf("%s%8.1f%n", text, value);
    }

    private static void print(String text, int value) {
        System.out.printf("%s%8d%n", text, value);
    }
}
