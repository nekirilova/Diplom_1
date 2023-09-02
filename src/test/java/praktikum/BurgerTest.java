package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock //создаем мок Ингредиента
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock //создаем мок булочки
    private Bun bun;
    //создаем константы с тестовыми данными
    private final float BUN_PRICE = 100;
    private final float INGREDIENT_PRICE = 75;
    private final String BUN_NAME = "some bun";
    private final String INGREDIENT_NAME = "some ingredient";

    @Test //провреям метод для добавления ингредиентов
    public void addIngredientTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.addIngredient(ingredient1); //добавляем ингредиент
        burger.addIngredient(ingredient2);//добавляем второй ингредиент
        int actualSize = burger.ingredients.size(); //узнаем размер списка ингредиентов
        int expectedSize = 2; //задаем ожидаемый размер списка
        Assert.assertEquals("Incorrect number of ingredients", expectedSize, actualSize); //проверяем, что размеры списков совпадают

    }

    @Test //проверяем метод перемещения ингредиента
    public void moveIngredientTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.addIngredient(ingredient1); //добавляем ингредиент
        burger.addIngredient(ingredient2);//добавляем второй ингредиент
        int firstIngredientIndex = burger.ingredients.indexOf(ingredient1); //получаем индекс первого ингредиента
        int newIngredientIndex = burger.ingredients.indexOf(ingredient2); // получаем индекс второго ингредиента
        burger.moveIngredient(firstIngredientIndex, newIngredientIndex); //меняем местами ингредиенты
        //проверяем, что второй ингредиент теперь оказался на месте первого:
        Assert.assertEquals("Ingredient wasn't moved", ingredient2, burger.ingredients.get(0));

    }

    @Test //тест на удаление ингредиентов
    public void removeIngredientTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.addIngredient(ingredient1); //добавляем ингредиент
        burger.addIngredient(ingredient2);//добавляем ингредиент
        burger.addIngredient(ingredient1);//добавляем ингредиент
        int sizeBeforeRemove = burger.ingredients.size(); //получаем размер списка до удаления ингредиента
        burger.removeIngredient(1); //удаляем второй ингредиент
        int sizeAfterRemove = burger.ingredients.size();//получаем размер списка после удаления
        //проверяем, что третий ингредиент теперь на месте второго
        Assert.assertEquals("Ingredient wasn't removed", ingredient1, burger.ingredients.get(1));
        //проверяем, что размер списка уменьшился на 1
        Assert.assertEquals("Incorrect size of ingredients list", (sizeBeforeRemove - 1), sizeAfterRemove);
    }

    @Test //проверяем расчет цены
    public void getPriceTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.setBuns(bun);//задаем булку
        //создаем стаб, который возвращает цену булки из константы
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        burger.addIngredient(ingredient1);//добавляем ингредиент
        burger.addIngredient(ingredient2);//добавляем ингредиент
        burger.addIngredient(ingredient1);//добавляем ингредиент
        //создаем стабы, которые возвращают цену ингредиентов из константы
        Mockito.when(ingredient1.getPrice()).thenReturn(INGREDIENT_PRICE);
        Mockito.when(ingredient2.getPrice()).thenReturn(INGREDIENT_PRICE);
        //задаем ожидаемую итоговую цену
        float totalPrice = (BUN_PRICE * 2) + (INGREDIENT_PRICE * 3);
        //проверяем, что метод возвращает корректную стоимость:
        Assert.assertEquals("Incorrect price", totalPrice, burger.getPrice(), 0);

    }

    @Test //проверяем метод создания чека
    public void getReceiptTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.setBuns(bun); //задаем булку
        Mockito.when(bun.getName()).thenReturn(BUN_NAME); //задаем стаб, который возвращает название булки
        burger.addIngredient(ingredient1); //добавляем ингредиент
        Mockito.when(ingredient1.getName()).thenReturn(INGREDIENT_NAME);//задаем стаб, который возвращает название ингредиента
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredient1.getPrice()).thenReturn(INGREDIENT_PRICE);
        String expectedBurger =
                "(==== some bun ====)\r\n" +
                "= filling some ingredient =\r\n" +
                "(==== some bun ====)\r\n" +
                "\r\n" +
                "Price: 275,000000\r\n";
        String finalBurger = burger.getReceipt(); //вызываем метод создания чека
        //проверяем, что рецепты совпадают
        Assert.assertEquals("Incorrect receipt", expectedBurger, finalBurger);
    }
}
