package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BunTest {
    private Bun bun;
    private final String NAME = "black bun";
    private final float PRICE = 100;

    @Before //создаем булочку перед каждым тестом
    public void setUp() {
        bun = new Bun(NAME, PRICE);
    }
    @Test //проверяем, что геттер возвращает правильное название булочки
    public void getBunNameReturnsCorrectName() {

    String expectedName = "black bun";
    Assert.assertEquals("Incorrect bun name", expectedName, bun.getName());
}
@Test//проверяем, что геттер возвращает правильную цену булочки
    public void getBunPriceReturnsCorrectPrice() {
    float expectedPrice = 100;
    Assert.assertEquals("Incorrect price", expectedPrice, bun.getPrice(), 0);
}
}
