package pl.edu.pjwstk;

import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VectorTests {

    private VectorImpl vector = new VectorImpl();

    @Test
    public void testEmpty() {
        assertTrue(vector.getVector().isEmpty());
    }

    @Test
    public void testVectors() {
        Vector<Integer> vector1 = new Vector<>(2);
        vector1.add(1);
        vector1.add(5);
        vector.setVector(vector1);
       assertFalse(vector.getVector().isEmpty());
    }

    @Test
    public void adding(){
        Vector<Integer> vector1 = new Vector<>(2);
        vector1.add(1);
        Vector<Integer> vector2 = new Vector<>(2);
        vector2.add(5);
        assertTrue(vector1.size()==vector2.size());
    }
}
