package johan24.examplemod.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModClientEventsTest {

    @Test
    public void removeNamespaceTest() {
        assertEquals(ModClientEvents.removeNamespace("minecraft:tropical_fish"), "tropical fish");
        assertEquals(ModClientEvents.removeNamespace("examplemod:obsidianite"), "examplemod:obsidianite");
    }
}
