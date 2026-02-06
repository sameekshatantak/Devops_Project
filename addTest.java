package Devops_Project;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.beans.Transient;
import java.util.Random;

public class addTest {
    add adder = new add();

    @BeforeEach
    public void setUp() {
        adder = new add();
    }
    @Test
    public void testAddNumbers() {
        Assert.assertEquals(Math.addExact(2, 3), adder.addnumbers(2, 3));
        Assert.assertEquals(Math.addExact(-2, 1), adder.addnumbers(-2, 1));
        Assert.assertEquals(Math.addExact(0, 0), adder.addnumbers(0, 0));
        //Assert.assertThrows(ArithmeticException.class, () -> adder.addnumbers(Integer.MAX_VALUE, 1));
        //Assert.assertEquals(1, adder.addnumbers(1, 2));    
    }
    @Test
    public void testAddTrue() {
        Assert.assertTrue(adder.addnumbers(10, 20) == 30);
    }

    @Test
    public void testAddFalse() {
        Assert.assertFalse(adder.addnumbers(10, 20) == 25);
    }     

    @Test
    public void testAddNotEquals() {
        Assert.assertNotEquals(100, adder.addnumbers(50, 30));
    }   

    @Test
    public void testAddNotNull() {
        Assert.assertNotNull(adder.addnumbers(5, 10));
    }

    @RepeatedTest(5)
    @Test
    public void testAddNumbersRepeated() {
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        Assert.assertEquals(Math.addExact(a, b), adder.addnumbers(a, b));
    }
}


