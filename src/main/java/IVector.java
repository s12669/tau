import java.util.Vector;

public interface IVector {
    Vector<Integer> add(Vector<Integer> v);

    static Vector<Integer> addVectors(Vector<Integer> v, Vector<Integer> vec) {
        Vector<Integer> result = new Vector<>();
        result.addAll(v);
        result.addAll(vec);
        return result;
    }
}