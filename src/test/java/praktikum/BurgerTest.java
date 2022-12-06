package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Ingredient ingredient;

    @Mock
    private Bun bun;

    private final Ingredient NEW_INGREDIENT = new Ingredient(IngredientType.FILLING, "Meat", 115);
    private final float BUN_PRICE = 100;
    private final float INGREDIENT_PRICE = 75;

    @Test
    public void addIngredientTest() {
    Burger burger = new Burger();
    burger.addIngredient(ingredient);
    burger.addIngredient(NEW_INGREDIENT);
    int actualSize = burger.ingredients.size();
    int expectedSize = 2;
    Assert.assertEquals("Incorrect number of ingredients", expectedSize, actualSize);

    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(NEW_INGREDIENT);
        int firstIngredientIndex = burger.ingredients.indexOf(ingredient);
        int newIngredientIndex = burger.ingredients.indexOf(NEW_INGREDIENT);
        burger.moveIngredient(firstIngredientIndex, newIngredientIndex);
        Assert.assertEquals("Ingredient wasn't moved", NEW_INGREDIENT, burger.ingredients.get(0));

    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(NEW_INGREDIENT);
        int sizeBeforeRemove = burger.ingredients.size();
        burger.removeIngredient(1);
        int sizeAfterRemove = burger.ingredients.size();
        Assert.assertEquals("Ingredient wasn't removed", NEW_INGREDIENT, burger.ingredients.get(1));
        Assert.assertEquals("Incorrect size of ingredients list", (sizeBeforeRemove - 1), sizeAfterRemove);
    }

    @Test
    public void getPriceTest() {
    Burger burger = new Burger();
    burger.setBuns(bun);
    Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
    burger.addIngredient(ingredient);
    burger.addIngredient(ingredient);
    burger.addIngredient(ingredient);
    Mockito.when(ingredient.getPrice()).thenReturn(INGREDIENT_PRICE);
    float totalPrice = (BUN_PRICE * 2) + (INGREDIENT_PRICE * 3);
    Assert.assertEquals("Incorrect price", totalPrice, burger.getPrice(), 0);

    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("black bun");
        burger.addIngredient(NEW_INGREDIENT);
        String receipt = burger.getReceipt();
       Mockito.verify(bun, Mockito.times(2)).getName();
    }
}
