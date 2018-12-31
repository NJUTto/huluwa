import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void isEmpty() {
        Position position = new Position();
        assertEquals(position.isEmpty(),true);
    }
}