package Devops_Project;
import org.junit.*;
import java.beans.Transient;

public class addTest {
    @Test
    public void testAddNumbers() {
        add adder = new add();
        Assert.assertEquals(5, adder.addnumbers(2, 3));
        Assert.assertEquals(-1, adder.addnumbers(-2, 1));
        Assert.assertEquals(0, adder.addnumbers(0, 0));
        //Assert.assertEquals(1, adder.addnumbers(1, 2));

    
}
}
