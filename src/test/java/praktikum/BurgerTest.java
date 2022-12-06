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
    private Ingredient ingredient;

    @Mock //создаем мок булочки
    private Bun bun;
    //создаем константы с тестовыми данными
    private final Ingredient NEW_INGREDIENT = new Ingredient(IngredientType.FILLING, "Meat", 115);
    private final float BUN_PRICE = 100;
    private final float INGREDIENT_PRICE = 75;

    @Test //провреям метод для добавления ингредиентов
    public void addIngredientTest() {
    Burger burger = new Burger(); //создаем бургер
    burger.addIngredient(ingredient); //добавляем ингредиент
    burger.addIngredient(NEW_INGREDIENT);//добавляем второй ингредиент
    int actualSize = burger.ingredients.size(); //узнаем размер списка ингредиентов
    int expectedSize = 2; //задаем ожидаемый размер списка
    Assert.assertEquals("Incorrect number of ingredients", expectedSize, actualSize); //проверяем, что размеры списков совпадают

    }

    @Test //проверяем метод перемещения ингредиента
    public void moveIngredientTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.addIngredient(ingredient); //добавляем ингредиент
        burger.addIngredient(NEW_INGREDIENT);//добавляем второй ингредиент
        int firstIngredientIndex = burger.ingredients.indexOf(ingredient); //получаем индекс первого ингредиента
        int newIngredientIndex = burger.ingredients.indexOf(NEW_INGREDIENT); // получаем индекс второго ингредиента
        burger.moveIngredient(firstIngredientIndex, newIngredientIndex); //меняем местами ингредиенты
        //проверяем, что второй ингредиент теперь оказался на месте первого:
        Assert.assertEquals("Ingredient wasn't moved", NEW_INGREDIENT, burger.ingredients.get(0));

    }

    @Test //тест на удаление ингредиентов
    public void removeIngredientTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.addIngredient(ingredient); //добавляем ингредиент
        burger.addIngredient(ingredient);//добавляем ингредиент
        burger.addIngredient(NEW_INGREDIENT);//добавляем ингредиент
        int sizeBeforeRemove = burger.ingredients.size(); //получаем размер списка до удаления ингредиента
        burger.removeIngredient(1); //удаляем второй ингредиент
        int sizeAfterRemove = burger.ingredients.size();//получаем размер списка после удаления
        //проверяем, что третий ингредиент теперь на месте второго
        Assert.assertEquals("Ingredient wasn't removed", NEW_INGREDIENT, burger.ingredients.get(1));
        //проверяем, что размер списка уменьшился на 1
        Assert.assertEquals("Incorrect size of ingredients list", (sizeBeforeRemove - 1), sizeAfterRemove);
    }

    @Test //проверяем расчет цены
    public void getPriceTest() {
    Burger burger = new Burger(); //создаем бургер
    burger.setBuns(bun);//задаем булку
        //создаем стаб, который возвращает цену булки из константы
    Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
    burger.addIngredient(ingredient);//добавляем ингредиент
    burger.addIngredient(ingredient);//добавляем ингредиент
    burger.addIngredient(ingredient);//добавляем ингредиент
        //создаем стаб, который возвращает цену ингредиента из константы
    Mockito.when(ingredient.getPrice()).thenReturn(INGREDIENT_PRICE);
    //задаем ожидаемую итоговую цену
    float totalPrice = (BUN_PRICE * 2) + (INGREDIENT_PRICE * 3);
    //проверяем, что метод возвращает корректную стоимость:
    Assert.assertEquals("Incorrect price", totalPrice, burger.getPrice(), 0);

    }

    @Test //проверяем метод создания чека
    public void getReceiptTest() {
        Burger burger = new Burger(); //создаем бургер
        burger.setBuns(bun); //задаем булку
        burger.addIngredient(NEW_INGREDIENT); //добавляем ингредиент
        burger.getReceipt(); //вызываем метод создания чека
        //проверяем, что при печати чека имя булки запрашивалось 2 раза:
       Mockito.verify(bun, Mockito.times(2)).getName();
    }
}
