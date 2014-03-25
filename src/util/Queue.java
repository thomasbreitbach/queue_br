package util;
import java.util.NoSuchElementException;

/**
 * Diese Klasse realisiert eine unbeschraenkte Warteschlange. Die mit
 * {@code put} eingefuegten Daten werden durch {@code get} in der gleichen
 * Reihenfolge entfernt.
 * <p>
 * Die Warteschlange ist intern durch ein Array implementiert. Alle
 * Operationen sind in O(1). Dies gilt (amortisiert) auch fuer die
 * Anpassung des Speicherplatzes.
 * <p> 
 * Diese Klasse unterstuetzt nicht die Typpruefung durch den 
 * Compiler. Daher kann man hier beliebige Elemente einfuegen, muss bei
 * Bedarf aber Laufzeitpruefungen (Casts) einsetzen.
 * <p>
 * Beispiel:
 * <pre>
 *     Queue q = new Queue();
 *     q.put("the answer is");
 *     q.put(42);
 *     String qString = q.toString();
 *     // qString = "Queue(the answer is, 42)";
 *     String hello = (String) q.get();
 *     Integer fortyTwo = q.get();
 * </pre>
 * <p>
 * Bei ernsthaften Anwendungen sollte man eine Klasse wie
 * {@link java.util.ArrayDeque} benutzen.
 */
public final class Queue {
    /* TODO: Die angegebenen Fehler korrigieren.
     * 
     * Hinweis: Es ist ein weiterer Fehler versteckt.
     */
    
    /**
     * Anfangskapazitaet
     */
    public static final int INITIAL_CAPACITY = 4;
    
    /**
     * Datenspeicher.
     */
    private Object[] data = new Object[INITIAL_CAPACITY];
    
    /**
     * Anzahl der gespeicherten Elemente.
     */
    private int size = 0;
    
    /**
     * Anfang der Queue = Index fuer naechstes {@code get}.
     */
    private int head = 0;

    /**
     * Legt eine leere Warteschlange der anfaenglichen
     * Speicherkapazitaet von 4 an.
     */
    public Queue() {
    }
    
    /**
     * Stellt fest, ob die Warteschlange leer ist.
     * 
     * @return {@code true} genau dann, wenn die Warteschlange leer ist
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Bestimmt die Anzahl der Elemente der Warteschlange
     * 
     * @return Anzahl der Elemente
     */
    public int size() {
        return size;
    }

    /**
     * Informiert ueber die Groesse des verwendeten Arrays.
     * 
     * @return aktuelle Arraygroesse
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Fuegt ein Element in die Warteschlange ein.
     * 
     * @param x
     *            einzufuegendes Element
     */
    public void put(Object x) {
        /*
         * TODO: ist hier ein Fehler?
         */
        if (size == capacity())
            setCapacityTo(2 * size);
        int putIdx = (head + size) % capacity();
        data[putIdx] = x;
        size += 1;
    }

    /**
     * Entfernt das am laengsten in der Warteschlange enthaltene Element und
     * gibt es zurueck. Die Warteschlange darf dabei nicht leer sein!
     * <p>
     * Die Implementierung behaelt keine Referenz zu entfernten Objekten
     * zurueck.
     * 
     * @return entferntes Element
     * @throws NoSuchElementException
     *             wenn die Warteschlange leer ist.
     */
    public Object get() {
        if (this.isEmpty())
            throw new NoSuchElementException("Queue is empty");
        Object result = data[head];
        data[head] = null;
        /* TODO: head richtig erhoehen.
         * Hinweis: Es ist noch etwas zu beachten!
         */
        head = (head + 1) % capacity();
        size -= 1;
        return result;
    }

    /**
     * Kopiert den Inhalt der Queue in ein Array.
     * Das erzeugte Array hat genau die Groesse um alle Daten
     * aufzunehmen.
     * <p>
     * Der Inhalt der Queue wird nicht veraendert.
     * 
     * @return Queue-Elemente
     */
    public Object[] toArray() {
        return copyIntoArray(size);
    }

    private static final int MAX_TO_LIST = 10;

    /**
     * Gibt eine Stringdarstellung der Warteschlange zurueck. Die Darstellung
     * lautet {@code Queue(elem1, elem2,...)}. Es werden bis zu 10
     * Elemente aufgelistet.
     * 
     * @return Stringdarstellung der Warteschlange
     */
    @Override
    public String toString() {
        if (size == 0)
            return "Queue()";
        StringBuilder b = new StringBuilder();
        b.append("Queue(");
        int limit = Math.min(size - 1, MAX_TO_LIST - 1);
        int index = head;
        for (int i = 0; i < limit; i++) {
            b.append(data[index]).append(", ");
            index = (index + 1) % data.length;
        }
        b.append(data[index]);
        if (size > MAX_TO_LIST)
            b.append(", ...");
        b.append(")");
        return b.toString();
    }

    /**
     * Setzt den Speicherbedarf auf das noetige Minimum
     * zurueck. Dieses Minimum ist gleich dem Maximum
     * von Anzahl der gepeicherten Elemente und
     * Anfangskapazitaet.
     */
    public void reduceCapacity() {
        setCapacityTo(Math.max(size, INITIAL_CAPACITY));
    }

    /**
     * Setzt das Array data auf die angegebene Groesse und
     * uebernimmt dabei die alten Daten. {@code getIdx} und {@code putIdx}
     * werden entsprechend gesetzt.
     * <p>
     * Die Daten laufen jetzt von {@code data[0]} bis {@code data[size-1]}.
     * 
     * @param n neue Groesse des Arrays {@code data}.
     */
    private void setCapacityTo(int n) {
        data = copyIntoArray(n);
        head = 0;   
    }
    
    /**
     * Kopiert alle Datenelemente der Queue in ein neues Array der
     * Groese {@code capacity}.
     * Das neue Array ist ab Index 0 fortlaufend in der richtigen Reihenfolge
     * gefuellt.
     * 
     * @param capacity Groesse des neuen Arrays
     */
    private Object[] copyIntoArray(int capacity) {
        Object[] newArray = new Object[capacity];
        int index = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = data[index];
            index = (index + 1) % data.length;
        }
        return newArray;
    }
}
