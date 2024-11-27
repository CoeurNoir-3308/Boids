package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import com.boids.Vector3D;

public class Vector3DTest {

    @Test
    public void testCreator() {
        Vector3D v1 = new Vector3D(0, 0, 0);
        assertEquals(0, v1.getX(), 0.0001);
        assertEquals(0, v1.getY(), 0.0001);
        assertEquals(0, v1.getZ(), 0.0001);

        Vector3D v2 = new Vector3D(1, 2, 3);
        assertEquals(1, v2.getX(), 0.0001);
        assertEquals(2, v2.getY(), 0.0001);
        assertEquals(3, v2.getZ(), 0.0001);
    }

    @Test
    public void testAddition() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(4, 5, 6);
        Vector3D result = v1.add(v2);

        assertEquals(5, result.getX(), 0.0001);
        assertEquals(7, result.getY(), 0.0001);
        assertEquals(9, result.getZ(), 0.0001);
    }

    @Test
    public void testSubtraction() {
        Vector3D v1 = new Vector3D(4, 5, 6);
        Vector3D v2 = new Vector3D(1, 2, 3);
        Vector3D result = v1.subtract(v2);

        assertEquals(3, result.getX(), 0.0001);
        assertEquals(3, result.getY(), 0.0001);
        assertEquals(3, result.getZ(), 0.0001);
    }

    @Test
    public void testMultiplication() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        double scalar = 2;
        Vector3D result = v1.mult(scalar);

        assertEquals(2, result.getX(), 0.0001);
        assertEquals(4, result.getY(), 0.0001);
        assertEquals(6, result.getZ(), 0.0001);
    }

    @Test
    public void testDivision() {
        Vector3D v1 = new Vector3D(2, 4, 6);
        double scalar = 2;
        Vector3D result = v1.div(scalar);

        assertEquals(1, result.getX(), 0.0001);
        assertEquals(2, result.getY(), 0.0001);
        assertEquals(3, result.getZ(), 0.0001);
    }

    @Test
    public void testDotProduct() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(4, 5, 6);
        double result = v1.dotProduct(v2);

        assertEquals(32, result, 0.0001);
    }

    @Test
    public void testMagnitude() {
        Vector3D v1 = new Vector3D(3, 4, 0);
        assertEquals(5, v1.magnitude(), 0.0001);
        
        Vector3D v2 = new Vector3D(1, 2, 2);
        assertEquals(3, v2.magnitude(), 0.0001);
    }

    @Test
    public void testNormalisation() {
        Vector3D v1 = new Vector3D(3, 4, 0);
        Vector3D result = v1.normaliser();

        assertEquals(0.6, result.getX(), 0.0001);
        assertEquals(0.8, result.getY(), 0.0001);
        assertEquals(0.0, result.getZ(), 0.0001);
    }

    @Test
    public void testGetAngles() {
        Vector3D v1 = new Vector3D(1, 1, 0);
        double[] angles = v1.getAngles();

        assertEquals(Math.PI / 4, angles[0], 0.0001);
        assertEquals(Math.PI / 2, angles[1], 0.0001);

        Vector3D v2 = new Vector3D(0, 0, 1);
        angles = v2.getAngles();

        assertEquals(0, angles[0], 0.0001);
        assertEquals(0, angles[1], 0.0001);
    }

    @Test
    public void testToString() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        assertEquals("Vector3D(1.0, 2.0, 3.0)", v1.toString());
    }
}
