package statistics;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Random;

/**
 * Diese Klasse schreibt in die Datei {@code input.txt} eine Reihe von
 * Zufallszahlen.
 */
public class Generator {
    private static final int ANZAHL_ZAHLEN = 1000;
    private static final double LIMIT = 100.0;
    
    public static void main(String[] args) throws Exception {
        PrintStream f = new PrintStream(new FileOutputStream("input.txt"));
        Random r = new Random();
        for (int i = 0; i < ANZAHL_ZAHLEN; i++)
            f.printf(Locale.US, "%6.2f\n", LIMIT * r.nextDouble());
        f.close();
    }
}
