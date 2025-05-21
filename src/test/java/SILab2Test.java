import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SILab2Test {

    @Test
    void testEveryStatement() {
        Exception ex1 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", ex1.getMessage());

        List<Item> items2 = List.of(new Item(null, 1, 100, 0));
        Exception ex2 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items2, "1234567890123456"));
        assertEquals("Invalid item!", ex2.getMessage());

        List<Item> items3 = List.of(new Item("item1", 1, 100, 0));
        Exception ex3 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items3, "1234567890123"));
        assertEquals("Invalid card number!", ex3.getMessage());

        List<Item> items4 = List.of(new Item("item1", 1, 100, 0));
        Exception ex4 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items4, "12345678901234ab"));
        assertEquals("Invalid character in card number!", ex4.getMessage());

        List<Item> items5 = List.of(new Item("item1", 2, 100, 0.1));
        double result = SILab2.checkCart(items5, "1234567891234567");
        assertEquals(150, result, 0.01);
    }

    @Test
    public void testMultipleCondition() {
        // Price <= 300 | Discount == 0 | Quantity <= 10  => False | False | False
        Item a = new Item("A", 5, 250, 0);
        assertEquals(1250, SILab2.checkCart(List.of(a), "1234567812345678"));

        // Price <= 300 | Discount == 0 | Quantity > 10  => False | False | True
        Item b = new Item("B", 15, 250, 0);
        assertEquals((250 * 15) - 30, SILab2.checkCart(List.of(b), "1234567812345678"));

        // Price <= 300 | Discount > 0 | Quantity X       => False | True | X
        Item c = new Item("C", 5, 250, 0.2);
        assertEquals((250 * 0.8 * 5) - 30, SILab2.checkCart(List.of(c), "1234567812345678"));

        // Price > 300 | Discount X | Quantity X          => True | X | X
        Item d = new Item("D", 5, 350, 0);
        assertEquals((350 * 5) - 30, SILab2.checkCart(List.of(d), "1234567812345678"));
    }
}


