import java.util.Vector;

public class VectorImpl implements IVector {

    private Vector<Integer> vector = new Vector<>(2);

    Vector<Integer> getVector() {
        return vector;
    }

    void setVector(Vector<Integer> vector) {
        this.vector = vector;
    }

    @Override
    public Vector<Integer> add(Vector<Integer> v) {
        Vector<Integer> result = new Vector<>();
        result.addAll(v);
        result.addAll(v);
        return result;
    }

    public static Vector<Integer> addVectors(Vector<Integer> v, Vector<Integer> vec) {
        {
            Vector<Integer> result = new Vector<>();
            result.addAll(v);
            result.addAll(vec);
            return result;
        }
    }
}