package Task18_TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorTest {
    //declare calculator
    private Calculator calculator;

    @BeforeClass
    public void createInstance() {
        calculator = new Calculator();
        System.out.println("BeforeClass: Class instance created");
    }

    @AfterClass
    public void resetToNull() {
        calculator = null;
        System.out.println("AfterClass: Calculator = null set");
    }

    //create new metods:testAddition, testSubtraction, testMultiplication, testDivision
    @Test(priority = 1, description = "testing the addition operation")
    public void testAddition() {
        double result = calculator.add(-90, 90);
        System.out.println("testAddition: (-90+90) = " + result);
    }

    @Test(priority = 2, description = "testing the subtraction operation")
    public void testSubtraction() {
        double result = calculator.subtract(-190, 290);
        System.out.println("testSubtraction: (-190-290) = " + result);
    }

    @Test(priority = 3, description = "testing the multiplication operation")
    public void testMultiplication() {
        double result = calculator.multiply(15, 20);
        System.out.println("testMultiplication: (15*20) = " + result);
    }

    @Test(priority = 4, description = "testing the division operation")
    public void testDivision() {
        double result = calculator.divide(0, 90);
        System.out.println("testDivision: (0/90) = " + result);
    }

    @Test(priority = 5, description = "testing division number by zero", expectedExceptions = ArithmeticException.class)
    public void testDivideNumberByZero() {
        double result = calculator.divide(1, 0);
        System.out.println("testDivideNumberByZero (1/0) = " + result);
    }

    @Test(priority = 6, description = "testing division zero by zero", expectedExceptions = ArithmeticException.class)
    public void testDivideZeroByZero() {
        double result = calculator.divide(0, 0);
        System.out.println("testDivideZeroByZero: (0/0) = " + result);
    }


}
