
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest extends BaseBurgerTest {



    @Test
    public void getPriceReturnsDoubleBunPriceWhenNoIngredients() {
        double price = burger.getPrice();
        assertEquals(200.0, price, 0.001);
    }

    @Test
    public void getPriceAddsIngredientPrices() {
        Ingredient ing1 = Mockito.mock(Ingredient.class);
        Ingredient ing2 = Mockito.mock(Ingredient.class);

        when(ing1.getPrice()).thenReturn(50f);
        when(ing2.getPrice()).thenReturn(30f);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        float price = burger.getPrice();
        assertEquals(280.0f, price, 0.001f);
    }

    @Test(expected = NullPointerException.class)
    public void getPrice_throws_whenBunIsNull() {
        Burger brokenBurger = new Burger();
        brokenBurger.getPrice();
    }

    @Test
    public void removeIngredientDecreasesSize() {
        Ingredient ing = Mockito.mock(Ingredient.class);
        burger.addIngredient(ing);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_throwsForEmptyList() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientChangesOrder() {
        Ingredient ing1 = new Ingredient(IngredientType.FILLING, "First", 10);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "Second", 20);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(0, 1);

        assertEquals(Arrays.asList("Second", "First"),
                burger.ingredients.stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toList()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_throwsForInvalidIndex() {
        burger.addIngredient(ingredientStub);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void getReceiptContainsBunName() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Test Bun ===="));
    }

    @Test
    public void getReceipt_containsIngredientInLowercase() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce Hot Sauce ="));
    }

    @Test
    public void getReceiptContainsPrice() {
        burger.addIngredient(ingredientStub);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price: 250,000000"));
    }

    @Test(expected = NullPointerException.class)
    public void getReceiptThrowsWhenBunIsNull() {
        Burger brokenBurger = new Burger();
        brokenBurger.addIngredient(ingredientStub);
        brokenBurger.getReceipt();
    }
}