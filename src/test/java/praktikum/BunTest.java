package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public class BunTest {
    private Bun bun;
    private final String NAME = "black bun";
    private final float PRICE = 100;

    @Before
    public void setUp() {
        bun = new Bun(NAME, PRICE);
    }
@Test
    public void getBunNameReturnsCorrectName() {

    String expectedName = "black bun";
    Assert.assertEquals("Incorrect bun name", expectedName, bun.getName());
}
@Test
    public void getBunPriceReturnsCorrectPrice() {
    float expectedPrice = 100;
    Assert.assertEquals("Incorrect price", expectedPrice, bun.getPrice(), 0);
}
}
