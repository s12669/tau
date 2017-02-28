import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTests {

    VectorImpl vector = new VectorImpl();

    @Test
    public void testVector(){
        assertEquals(4,vector.add(2));
    }
    public static void testVectors(){
        assertEquals(5,vector.add(3,2));
    }
}
