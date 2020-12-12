package johan24.examplemod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleModTest {

    @Test
    public void testMODID() {
        assertEquals(ExampleMod.MODID, "examplemod");
    }
}
