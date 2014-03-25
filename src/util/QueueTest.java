package util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Testet die Klasse @see Queue
 */
public class QueueTest {
    private Queue q;
   
    @Before
    public void setUp() throws Exception {
        q = new Queue();
    }
    
    @Test
    public void testConstructor() {
        assertTrue("neue Queue sollte leeer sein", 
                q.isEmpty());
        assertEquals("neue Queue muss Laenge 0 haben", 
                0, q.size());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void exceptionAtEmptyQueue() {
        q.get();
    }
    
    /**
     * Nach Speichern - Entnehmen muss die Reihenfolge beibehalten sein.
     */
    @Test
    public void simpleExample() {
        q.put("a");
        q.put("b");
        q.put(1);
        assertFalse("nach 3x put darf Queue nicht leer sein",
                q.isEmpty());
        assertEquals("nach 3x put muss die Laenge = 3 sein",
                3, q.size());
        assertEquals("a", q.get());
        assertEquals("b", q.get());
        assertEquals(1, q.get());
        assertTrue("3x put & 3x get = leere Queue",
                q.isEmpty());
        assertEquals("3x put & 3x get = leere Queue",
                0, q.size());
    }
    
    /**
     * Abwechselndes Speichern und Entnehmen liefert immer die gerade
     * gespeicherten Elemente und laesst eine leere Queue zurueck.
     * Der zirkulaere Lauf durch die Queue fuehrt nicht zum Vergroessern
     * der Kapazitaet der Queue.
     */
    @Test
    public void circularMoveThroughArray() {
        int initial = q.capacity();
        for (int i = 0; i < 10; i++) {
            assertEquals("anfangs leer",
                    0, q.size());
            q.put(i+1);
            assertEquals("Laenge 1 nach put",
                    1, q.size());
            int j = (Integer) q.get();
            assertEquals("get(put(x) = x",
                    i+1, j);
        }
        assertTrue("am Ende leer",
                q.isEmpty());
        assertEquals("am Ende leer",
                0, q.size());
        assertEquals("Array darf nicht vergroessert werden",
                initial, q.capacity());
    }
    
    /**
     * Man sollte eine (beliebig)
     * grosse Anzahl von Elementen speichern koennen.
     */
    @Test
    public void testAutomaticSizeIncrease() {
        for (int i = 0; i < 100; i++) {
            q.put(i+1);
        }
        assertFalse(q.isEmpty());
        assertEquals(100, q.size());
        int expected = 1;
        while (! q.isEmpty()) {
            int j = (Integer) q.get();
            assertEquals(expected, j);
            expected += 1;
        }
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }
    
    /**
     * Die Daten der Queue werden ohne Veraenderung der
     * Queue in der richtigen Reihenfolge in ein 
     * Array kopiert.
     */
    @Test
    public void testToArray() {
        for (int i = 0; i < 42; i++)
            q.put(i);
        Object[] a = q.toArray();
        assertEquals(42, a.length);
        assertEquals(42, q.size());
        for (int i = 0; i < 42; i++) { 
            assertEquals(i, a[i]);
            assertEquals(i, q.get());
        }
        assertTrue(q.isEmpty());
    }
    
    /**
     * Eine Queue mit den Elementen a,b,c ergibt den String
     * {@code "Queue(a,b,c)"}.
     * Bei mehr als 10 Elementen, steht fuer die
     * weggelassenen Elemente {@code ", ..."}.
     */
    @Test
    public void testToString() {
        assertEquals("Queue()", q.toString());
        for (int i = 0; i < 5; i++) q.put(i);
        assertEquals("Queue(0, 1, 2, 3, 4)", q.toString());
        for (int i = 5; i < 10; i++) q.put(i);
        assertEquals("Queue(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)", q.toString());
        q.put(10);
        assertEquals("Queue(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, ...)", q.toString());
        for (int i = 0; i < 9; i++)
            q.get();
        assertEquals("Queue(9, 10)", q.toString());
        q.get();
        assertEquals("Queue(10)", q.toString());
    }
    
    /**
     * Die Kapazitaet der Queue steigt erst, wenn die Anfangskapazitaet
     * ueberschritten wird. Fuer jede notwendige Steigerung steigt sie auf
     * das Doppelte.
     * <p>
     * Durch {@code reduceCapacity} wird die Kapazitaet auf die Anzahl der
     * Elemente zurueckgesetzt.
     * Der Minimalwert ist gleich der Anfangskapazitiaet.
     */
    @Test
    public void testCapacity() {
        int initial = q.capacity();
        for (int i = 0; i < initial; i++) {
            q.put('A'+i);
        }
        assertEquals("exakt die Arraygroesse",
                initial, q.capacity());
        for (int i = 0; i < initial; i++) 
            q.put('a'+i);
        assertEquals("2x Arraygroesse",
                2*initial, q.capacity());
        q.put("y");
        assertEquals("4x Arraygroesse",
                4*initial, q.capacity());
        q.get();
        q.reduceCapacity();
        assertEquals("nach reduce: size == capacity",
                q.size(), q.capacity());
        for(int i = 0; i <= initial; i++) 
            q.get();
        q.reduceCapacity();
        assertTrue(q.size() < initial);
        assertEquals("Array darf nicht kleiner als am Anfang werden",
                initial, q.capacity());
    }
}
