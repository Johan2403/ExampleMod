package johan24.forge.examplemod.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.versions.forge.ForgeVersion;

public class ExampleTags {

    public static class Items {
        public static final ITag.INamedTag<Item> INGOT_OBSIDIANITE = forge("ingots/obsidianite");
        public static final ITag.INamedTag<Item> ORES_OBSIDIANITE = forge("ores/obsidianite");

        /*
        private static ITag.INamedTag<Item> tag(String id) {
            return ItemTags.makeWrapperTag(ExampleMod.MODID + ":" + id);
        }
        */
        private static ITag.INamedTag<Item> forge(String id) {
            return ItemTags.bind(ForgeVersion.MOD_ID + ":" + id);
        }
    }

    public static class Blocks {
        public static final ITag.INamedTag<Block> ORES_OBSIDIANITE = forge("ores/obsidianite");

        /*
        private static ITag.INamedTag<Block> tag(String id) {
            return BlockTags.makeWrapperTag(ExampleMod.MODID + ":" + id);
        }
        */

        private static ITag.INamedTag<Block> forge(String id) {
            return BlockTags.bind(ForgeVersion.MOD_ID + ":" + id);
        }
    }
}
