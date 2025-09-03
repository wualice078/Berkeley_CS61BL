import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;
public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    // FIXME: Add additional tests for Triangle.java here that pass on a
    //  correct Triangle implementation and fail on buggy Triangle implementations.

    @Test
    public void sidesFormTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("sides 1,1,1 is a triangle").that(t.sidesFormTriangle(1,1,1)).isTrue();
        assertWithMessage("sides 2,1,1 is not a triangle").that(t.sidesFormTriangle(2,1,1)).isFalse();
        assertWithMessage("sides 3,1,1 is not a triangle").that(t.sidesFormTriangle(3,1,1)).isFalse();
        assertWithMessage("sides 2,2,1 is a triangle").that(t.sidesFormTriangle(2,2,1)).isTrue();
        // remember that you'll have to call on Triangle methods like
        // t.functionName(arguments), where t is a Triangle object
    }
    @Test
    public void pointsFormTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("points (0,0), (1,1), (1,0) is a triangle").that(t.pointsFormTriangle(0,0,1,1,1,0)).isTrue();
        assertWithMessage("points (-6,-5), (-3,4), (1,-4) is a triangle").that(t.pointsFormTriangle(-6,-5,-3,4,1,-4)).isTrue();
        assertWithMessage("points (0,0), (0,0), (0,0) is not a triangle").that(t.pointsFormTriangle(0,0,0,0,0,0)).isFalse();
        assertWithMessage("points (0,0), (0,1), (0,0) is not a triangle").that(t.pointsFormTriangle(0,0,0,1,0,0)).isFalse();
        assertWithMessage("points (-1,0), (1,100), (3,0) is a triangle").that(t.pointsFormTriangle(-1,0,1,100,3,0)).isTrue();
        assertWithMessage("points (-10,10), (-1,1), (101,-101) is not a triangle").that(t.pointsFormTriangle(-10,10,1,-1,101,-101)).isFalse();
    }
    @Test
    public void triangleType() {
        Triangle t = getNewTriangle();
        assertWithMessage("sides 1,1,1 is an equilateral triangle").that(t.triangleType(1,1,1)).isEqualTo("Equilateral");
        assertWithMessage("sides 201,201,201 is an equilateral triangle").that(t.triangleType(201,201,201)).isEqualTo("Equilateral");
        assertWithMessage("sides 3,3,2 is an isosceles triangle").that(t.triangleType(3,3,2)).isEqualTo("Isosceles");
        assertWithMessage("sides 3,4,5 is a scalene triangle").that(t.triangleType(3,4,5)).isEqualTo("Scalene");
        assertWithMessage("sides 10,13,17 is a scalene triangle").that(t.triangleType(10,13,17)).isEqualTo("Scalene");
    }
    @Test
    public void squaredHypotenuse() {
        Triangle t = getNewTriangle();
        assertWithMessage("sides 1,1 makes squared hypotenuse 2").that(t.squaredHypotenuse(1,1)).isEqualTo(2);
        assertWithMessage("sides 2,2 makes squared hypotenuse 8").that(t.squaredHypotenuse(2,2)).isEqualTo(8);
        assertWithMessage("sides 3,4 makes squared hypotenuse 25").that(t.squaredHypotenuse(3,4)).isEqualTo(25);
        assertWithMessage("sides 100,1 makes squared hypotenuse 10001").that(t.squaredHypotenuse(100,1)).isEqualTo(10001);
    }
}
