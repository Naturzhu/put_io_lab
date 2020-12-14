package put.io.testing.audiobooks;
import org.junit.jupiter.api.*;
import put.io.testing.junit.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    private Customer customer;
    private Audiobook audiobook;
    private AudiobookPriceCalculator calc;
    @BeforeEach
    public void setUp() {
        audiobook = new Audiobook("Hobbit", 40);
        calc = new AudiobookPriceCalculator();
    }
    @Test
    public void test1() {
        customer = new Customer("Artur", Customer.LoyaltyLevel.STANDARD, false);
        Assertions.assertEquals(40, calc.calculate(customer, audiobook));
    }
    @Test
    public void test2() {
        customer = new Customer("Aleksandra", Customer.LoyaltyLevel.SILVER, false);
        Assertions.assertEquals(36, calc.calculate(customer, audiobook));
    }
    @Test
    public void test3() {
        customer = new Customer("Ada", Customer.LoyaltyLevel.GOLD, false);
        Assertions.assertEquals(32, calc.calculate(customer, audiobook));
    }
    @Test
    public void test4() {
        customer = new Customer("Adam", Customer.LoyaltyLevel.SILVER, true);
        Assertions.assertEquals(0, calc.calculate(customer, audiobook));
    }
}