
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerRemoveIngredientParameterizedTest extends BaseBurgerTest {

    private final int indexToRemove;
    private final String expectedRemainingName;


    public BurgerRemoveIngredientParameterizedTest(int indexToRemove, String expectedRemainingName) {
        this.indexToRemove = indexToRemove;
        this.expectedRemainingName = expectedRemainingName;
    }

    @Parameterized.Parameters(name = "Удалить индекс {0}, останется: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Second"},
                {1, "First"},
        });
    }

    @Test
    public void testRemoveIngredient_parametrized() {
        Ingredient ing1 = new Ingredient(IngredientType.FILLING, "First", 100);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "Second", 50);

        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.removeIngredient(indexToRemove);

        assertEquals(expectedRemainingName, burger.ingredients.get(0).getName());
    }

}