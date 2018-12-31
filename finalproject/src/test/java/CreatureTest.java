import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureTest {

    @Test
    public void getX() {
        Creature test = new Creature(3,4);
        assertEquals(test.getX(),3);
    }

    @Test
    public void isAlive() {
        Creature test = new Creature();
        assertEquals(test.isAlive(),true);
    }
}