package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTest {
    //создаем поля для тестовых данных
    private final IngredientType type;
    private final String name;
    private final float price;

 //создаем конструктор для тестовых данных
    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }
//создаем объект с тестовыми данными
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {IngredientType.SAUCE, "meat", 150},
                {IngredientType.FILLING, "onion", 200}
        };
    }

    @Test //проверяем, что метод возвращает правильную цену ингредиента
    public void getIngredientPriceReturnsCorrectPrice() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect price",
                this.price, expectedIngredient.getPrice(),0 );
    }
    @Test //проверяем, что метод возвращает правильное название ингредиента
    public void getIngredientNameReturnsCorrectName() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect name",
                this.name, expectedIngredient.getName());
    }
    @Test //проверяем, что метод возвращает правильный тип ингредиента
    public void getIngredientTypeReturnsCorrectType() {
        Ingredient expectedIngredient = new Ingredient(this.type, this.name, this.price);
        Assert.assertEquals("Incorrect type",
                this.type, expectedIngredient.getType());
    }

}
