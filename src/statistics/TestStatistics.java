package statistics;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testet die Klasse {@code Statistics} mit der Zahlenfolge 0 - 9,
 * Die Ergebnisse werden mit der erwarteten Werten verglichen.
 */
public class TestStatistics {
    private static final double EPS = 1e-10;
    private Statistics stat;
    private File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = new File("test.txt");
        PrintStream f = new PrintStream(new FileOutputStream(testFile));
        for (int i = 0; i < 10; i++)
            f.printf(Locale.US, "%6.2f\n", (double) i);
        f.close();
        stat = new Statistics("test.txt");
    }
    
    @Test
    public void testSize() {
        assertEquals(10, stat.size());
    }
    
    @Test
    public void testSum() {
        assertEquals(45.0, stat.sum(), EPS);
    }
    
    @Test
    public void testQsum() {
        assertEquals(285.0, stat.qsum(), EPS);
    }
    
    @Test
    public void testMean() {
        assertEquals(4.5, stat.mean(), EPS);
    }
    
    @Test
    public void testVariance() {
        assertEquals(55.0/6.0, stat.variance(), EPS);
    }
    
    @Test
    public void testStd() {
        assertEquals(sqrt(55.0/6.0), stat.std(), EPS);
    }
    
    @Test
    public void testMse() {
        assertEquals(sqrt(11.0/12.0), stat.mse(), EPS);
    }

    @After
    public void tearDown() {
        testFile.delete();
    }
}
