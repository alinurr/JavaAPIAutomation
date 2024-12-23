package homeworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringLength {
    @Test
    public void testCheckStringLength(){
        String text = "dfsdfgdsfslkdnjsknbjfhbdjhf";
        assertTrue(text.length() > 15, "The length of the string is not longer than 15");
    }
}
