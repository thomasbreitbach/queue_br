package statistics;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

import util.Queue;

/**
 * Liest Zahlen aus einer Datei und stellt statische Information zur
 * Verfuegung.
 */
public final class Statistics {
    /**
     * Die eingelesenen Zahlen.
     */
    private final Object[] data;

    /**
     * Erzeugt ein neues Statistik Objekt.
     * Die Daten werden aus einer Datei gelesen.
     * 
     * @param fileName
     *            Eingabedatei
     * @throws FileNotFoundException wenn die Eingabedatei nicht geoeffnet
     * werden kann
     */
    public Statistics(String fileName) throws FileNotFoundException {
        final Queue queue = new Queue();
        Scanner r = null;
        try {
            r = new Scanner(new FileReader(fileName));
            r.useLocale(Locale.US);
            while (r.hasNextDouble())
                queue.put(r.nextDouble());
            data = queue.toArray();
        }
        finally {
            if (r != null) r.close();
        }
    }

    /**
     * Berechnet die Summe aller Zahlen.
     * 
     * @return Summe der Zahlen.
     */
    public double sum() {
        /* TODO: Summe berechnen und richtig zurueckgeben.
         * Hinweis: mit (Double) data[i] erhaelt man eine double-Zahl
         * (Cast von Object -> Double und Autoboxing nach double)
         */
        return 0.0;
    }

    /**
     * Berechnet die Summe der Quadrate der Zahlen.
     * 
     * @return Quadratsumme
     */
    public double qsum() {
        /* TODO: Summe der Quadrate berechnen und richtig zurueckgeben.
         * Hinweis: mit (Double) data[i] erhaelt man eine double-Zahl
         * (Cast von Object -> Double und Autoboxing nach double)
         */
        return 0.0;
    }

    /**
     * Gibt die Anzahl aller Zahlen zurueck.
     * 
     * @return Anzahl der Zahlen
     */
    public int size() {
        return data.length;
    }

    /**
     * Berechnet den Mittelwert aller Zahlen.
     * 
     * @return Mittelwert
     */
    public double mean() {
        return sum() / size();
    }

    /**
     * Berechnet die Varianz aller Zahlen unter der Annahme, dass es sich um
     * eine Stichprobe handelt.
     * 
     * @return Varianz der Stichprobe
     */
    public double variance() {
        final double m = mean();
        final double n = size();
        return (qsum() - m * m * n) / (n - 1);
    }

    /**
     * Berechnet die Standardabweichung einer Stichprobe.
     * 
     * @return Standardabweichung
     */
    public double std() {
        return Math.sqrt(variance());
    }

    /**
     * Berechnet den mittleren quadratischen Fehler
     * des Mittelwerts.
     * 
     * @return Fehler des Mittelwerts
     */
    public double mse() {
        return Math.sqrt(variance() / size());
    }
}
