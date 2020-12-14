package put.io.testing.junit;
import org.junit.jupiter.api.*;

class CalculatorTest {
    private Calculator calc;
    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }
    @Test
    public void testADD() {
        Assertions.assertEquals(9, calc.add(4,5));
        Assertions.assertEquals(13, calc.add(6,7));
    }
    @Test
    public void testMULTIPLY() {
        Assertions.assertEquals(5, calc.multiply(1,5));
        Assertions.assertEquals(56, calc.multiply(7,8));
    }
    @Test
    public void testADDPOSNUM(){
        Assertions.assertThrows(IllegalArgumentException.class,() ->{calc.addPositiveNumbers(-13,13);});
    }
}