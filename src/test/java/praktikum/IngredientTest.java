package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;


    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {IngredientType.SAUCE, "meat", 150},
                {IngredientType.FILLING, "onion", 200}
        };
    }

    @Test
    public void getIngredientPriceReturnsCorrectPrice() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect price",
                this.price, expectedIngredient.getPrice(),0 );
    }
    @Test
    public void getIngredientNameReturnsCorrectName() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect name",
                this.name, expectedIngredient.getName());
    }
    @Test
    public void getIngredientTypeReturnsCorrectType() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect type",
                this.type, expectedIngredient.getType());
    }

}
