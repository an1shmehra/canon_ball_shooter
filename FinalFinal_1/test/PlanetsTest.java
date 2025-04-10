import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ngmen
 */
public class PlanetsTest {

    public PlanetsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findTimeToTopOfParabola method, of class Planets.
     */
    @Test
    public void testFindTimeToTopOfParabola() {
        System.out.println("findTimeToTopOfParabola");
        double angle = Math.PI * 0.25;
        double expResult = Math.pow(2, 0.5) * 0.1;
        Planets.gravity = 10;
        Planets.initialSpeed = 1;
        Planets.speed = 1;
        double result = Planets.findTimeToTopOfParabola(angle);
        assertEquals(expResult, result, 0.000000001);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findHeighOfParabola method, of class Planets.
     */
    @Test
    public void testFindHeighOfParabola() {
        System.out.println("findHeighOfParabola");
        double angle = Math.PI * 0.5;
        double expResult = 0.01;
        Planets.gravity = 10;
        Planets.initialSpeed = 1;
        Planets.speed = 1;
        double result = Planets.findHeighOfParabola(angle);
        assertEquals(expResult, result, 0.000000001);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findRange method, of class Planets.
     */
    @Test
    public void testFindRange() {
        System.out.println("findRange");
        double angle = Math.PI *1.25;
        Planets.gravity = 10;
        Planets.initialSpeed = 1;
        Planets.speed = 1;
        double expResult = 0.02;
        double result = Planets.findRange(angle);
        assertEquals(expResult, result, 0.000000001);
        // TODO review the generated test code and remove the default call to fail.
    }

}
