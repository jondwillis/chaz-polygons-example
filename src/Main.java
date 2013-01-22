import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jonwillis
 * Date: 1/21/13
 * Time: 9:17 PM
 */
class Main {

    public static void main(String[] args) {

        LinkedList<Vector2> testList1 = new LinkedList<Vector2>();
        testList1.add(new Vector2(0, 0));
        testList1.add(new Vector2(3, 0));
        testList1.add(new Vector2(3, 3));
        testList1.add(new Vector2(0, 3));
        testList1.add(new Vector2(0, 0));
        List<Vector3> result = createPrism(testList1, 0, 6);
        for(Vector3 r : result) {
            System.out.println(r.toString());
        }

    }

    static List<Vector3> createPrism(List<Vector2> floorFace, double floorZ, double ceilingZ) throws IllegalArgumentException {

        // do not attempt to work on an invalid floorFace, and notify clients that this is illegal.
        if(floorFace == null || floorFace.size() == 0) {
            throw new IllegalArgumentException("Floor face cannot be empty or null.");
        }

        if(floorFace.size() < 4 && floorFace.get(0).equals(floorFace.get(floorFace.size()-1))) {
            throw new IllegalArgumentException("Floor face must be a polygon.");
        }

        // a set ensures by its definition that there can be no duplicate elements.
        List<Vector3> prismVectors = new LinkedList<Vector3>();

        for(int i = 0; i < floorFace.size(); i++) {

            int nextVectorIndex = i + 1;

            if(nextVectorIndex >= floorFace.size() - 1) {
                nextVectorIndex = 0;
            }

            Vector2 currentVector = floorFace.get(i);
            Vector2 nextVector = floorFace.get(nextVectorIndex);

            // we are blindly adding all possible vectors.
            // since the set does not allow duplicates,
            // this doesn't matter, aside from us calculating a bit more
            prismVectors.add(new Vector3(currentVector, floorZ));
            prismVectors.add(new Vector3(currentVector, ceilingZ));
            prismVectors.add(new Vector3(nextVector, ceilingZ));
            prismVectors.add(new Vector3(nextVector, floorZ));
            prismVectors.add(new Vector3(currentVector, floorZ));
        }

        return prismVectors;
    }


    static Set<Vector3> getPrismVectors(List<Vector2> floorFace, double floorZ, double ceilingZ) throws IllegalArgumentException {

        // do not attempt to work on an invalid floorFace, and notify clients that this is illegal.
        if(floorFace == null || floorFace.size() == 0) {
            throw new IllegalArgumentException("Floor face cannot be empty or null.");
        }

        if(floorFace.size() < 4 && floorFace.get(0).equals(floorFace.get(floorFace.size()-1))) {
            throw new IllegalArgumentException("Floor face must be a polygon.");
        }

        // a set ensures by its definition that there can be no duplicate elements.
        Set<Vector3> prismVectors = new LinkedHashSet<Vector3>();

        for(int i = 0; i < floorFace.size(); i++) {

            int nextVectorIndex = i + 1;

            if(nextVectorIndex >= floorFace.size() - 1) {
                nextVectorIndex = 0;
            }

            Vector2 currentVector = floorFace.get(i);
            Vector2 nextVector = floorFace.get(nextVectorIndex);

            // we are blindly adding all possible vectors.
            // since the set does not allow duplicates,
            // this doesn't matter, aside from us calculating a bit more
            prismVectors.add(new Vector3(currentVector, floorZ));
            prismVectors.add(new Vector3(currentVector, ceilingZ));
            prismVectors.add(new Vector3(nextVector, ceilingZ));
            prismVectors.add(new Vector3(nextVector, floorZ));
            prismVectors.add(new Vector3(currentVector, floorZ));
        }

        return prismVectors;
    }

    /**
     * A 3-space vector object. Treat it like a point! I learned some cool properties
     * about vectors in school but I've forgotten them all!
     */
    static class Vector3 {
        double x, y, z;

        Vector3(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Create a new vector from a 2d vector and a z value
         * @param vector2
         * @param z
         */
        Vector3(Vector2 vector2, double z) {
            this.x = vector2.x;
            this.y = vector2.y;
            this.z = z;
        }

        @Override
        public String toString() {
            return String.format("%f\t\t%f\t\t%f", x, y, z);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Vector3) {
                boolean equals =
                 ((Vector3)obj).x == this.x
                        && ((Vector3)obj).y == this.y
                        && ((Vector3)obj).z == this.z;
                return equals;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (int)Math.pow(33 * x, Math.pow(17 * y, 67 * z));
        }
    }

    /**
     * A 2-space vector object. Treat it like a point! I learned some cool properties
     * about vectors in school but I've forgotten them all!
     */
    static class Vector2 {
        double x, y;

        Vector2(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("%f, %f", x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Vector2) {
                return ((Vector2)obj).x == this.x
                        && ((Vector2)obj).y == this.y;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (int)Math.pow(33 * x, 17 * y);
        }
    }
}
