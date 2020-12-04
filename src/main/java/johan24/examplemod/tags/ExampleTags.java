package johan24.examplemod.tags;

import johan24.examplemod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.versions.forge.ForgeVersion;

public class ExampleTags {

    public static class Items {
        public static final ITag.INamedTag<Item> INGOT_OBSIDIANITE = forge("ingots/obsidianite");
        public static final ITag.INamedTag<Item> ORE_OBSIDIANITE = forge("ores/obsidianite");
    }

    @SuppressWarnings("unused")
    private static ITag.INamedTag<Item> tag(String id) {
        return ItemTags.makeWrapperTag(ExampleMod.MODID + ":" + id);
    }

    private static ITag.INamedTag<Item> forge(String id) {
        return ItemTags.makeWrapperTag(ForgeVersion.MOD_ID + ":" + id);
    }
}
