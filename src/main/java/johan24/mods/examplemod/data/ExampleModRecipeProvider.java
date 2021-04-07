package johan24.mods.examplemod.data;

import johan24.mods.examplemod.init.ModBlocks;
import johan24.mods.examplemod.init.ModItems;
import johan24.mods.examplemod.tags.ExampleTags;
import johan24.mods.examplemod.ExampleMod;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class ExampleModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ExampleModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        addBasicArmorRecipes(consumer, ExampleTags.Items.INGOT_OBSIDIANITE, ModItems.OBSIDIANITE_HELMET.get(), ModItems.OBSIDIANITE_CHESTPLATE.get(), ModItems.OBSIDIANITE_LEGGINGS.get(), ModItems.OBSIDIANITE_BOOTS.get());
        addBasicToolRecipes(consumer, ExampleTags.Items.INGOT_OBSIDIANITE, ModItems.OBSIDIANITE_PICKAXE.get(), ModItems.OBSIDIANITE_SWORD.get(), ModItems.OBSIDIANITE_AXE.get(), ModItems.OBSIDIANITE_SHOVEL.get(), ModItems.OBSIDIANITE_HOE.get());

        ShapedRecipeBuilder.shaped(ModBlocks.OBSIDIANITE_BLOCK.get())
                .define('X', ExampleTags.Items.INGOT_OBSIDIANITE)
                .pattern("XXX").pattern("XXX").pattern("XXX")
                .unlockedBy("has_obsidianite_ingot", has(ExampleTags.Items.INGOT_OBSIDIANITE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.OBSIDIANITE_INGOT.get(), 9)
                .requires(ModBlocks.OBSIDIANITE_BLOCK.get())
                .group("obsidianite_ingot")
                .unlockedBy("has_obsidianite_block", has(ModBlocks.OBSIDIANITE_BLOCK.get()))
                .save(consumer, "obsidianite_ingot_from_obsidianite_block");

        ShapelessRecipeBuilder.shapeless(ModItems.OBSIDIANITE_INGOT.get(), 4)
                .requires(ModItems.OBSIDIANITE_SCRAP.get())
                .requires(ModItems.OBSIDIANITE_SCRAP.get())
                .requires(ModItems.OBSIDIANITE_SCRAP.get())
                .requires(ModItems.OBSIDIANITE_SCRAP.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_obsidianite_scrap", has(ModItems.OBSIDIANITE_SCRAP.get()))
                .save(consumer, "obsidianite_ingot_from_obsidianite_scrap");

        addSmeltingRecipes(consumer, ExampleTags.Items.ORES_OBSIDIANITE, ModItems.OBSIDIANITE_INGOT.get(), 3.0F, 200);
        addSmeltingRecipes(consumer, Tags.Items.OBSIDIAN, ModItems.OBSIDIANITE_SCRAP.get(), 4.0F, 250);
    }

    /**
     * This is a template for creating basic armor recipes.
     * Call this in the registerRecipes method to use this.
     *
     * @param consumer - The consumer.
     * @param material - The meterial tag.
     * @param head     - The helmet item.
     * @param chest    - The chestplate item.
     * @param legs     - The leggings item.
     * @param feet     - The boots item.
     */
    protected static void addBasicArmorRecipes(Consumer<IFinishedRecipe> consumer, INamedTag<Item> material, @Nullable Item head, @Nullable Item chest, @Nullable Item legs, @Nullable Item feet) {
        if (head != null)
            ShapedRecipeBuilder.shaped(head).define('X', material).pattern("XXX").pattern("X X").group("helmets").unlockedBy("has_material", has(material)).save(consumer);
        if (chest != null)
            ShapedRecipeBuilder.shaped(chest).define('X', material).pattern("X X").pattern("XXX").pattern("XXX").group("chestplates").unlockedBy("has_material", has(material)).save(consumer);
        if (legs != null)
            ShapedRecipeBuilder.shaped(legs).define('X', material).pattern("XXX").pattern("X X").pattern("X X").group("leggings").unlockedBy("has_material", has(material)).save(consumer);
        if (feet != null)
            ShapedRecipeBuilder.shaped(feet).define('X', material).pattern("X X").pattern("X X").group("boots").unlockedBy("has_material", has(material)).save(consumer);
    }

    /**
     * This is a template for creating smelting recipes.
     * Call this in the registerRecipes method to use this.
     *
     * @param consumer   - The consumer.
     * @param ore        - The item to be smelted.
     * @param result     - The output of the smelting.
     * @param experience - The exp recieved after smelting.
     * @param time       - The time taken for smelting.
     */
    protected static void addSmeltingRecipes(Consumer<IFinishedRecipe> consumer, INamedTag<Item> ore, Item result, float experience, int time) {
        String name = Objects.requireNonNull(result.getRegistryName()).getPath();
        CookingRecipeBuilder.smelting(Ingredient.of(ore), result, experience, time).unlockedBy("has_ore", has(ore)).save(consumer, new ResourceLocation(ExampleMod.MODID, name + "_from_smelting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ore), result, experience, time / 2).unlockedBy("has_ore", has(ore)).save(consumer, new ResourceLocation(ExampleMod.MODID, name + "_from_blasting"));
    }

    /**
     * This is a template for creating basic tool recipes.
     * Call this in the registerRecipes method to use this.
     *
     * @param consumer - The consumer.
     * @param material - The meterial tag.
     * @param pickaxe  - The pickaxe item.
     * @param sword    - The sword item.
     * @param axe      - The axe item.
     * @param shovel   - The shovel item.
     * @param hoe      - The hoe item.
     */
    protected static void addBasicToolRecipes(Consumer<IFinishedRecipe> consumer, INamedTag<Item> material, @Nullable Item pickaxe, @Nullable Item sword, @Nullable Item axe, @Nullable Item shovel, @Nullable Item hoe) {
        if (pickaxe != null)
            ShapedRecipeBuilder.shaped(pickaxe)
                    .define('X', material).define('S', Items.STICK)
                    .pattern("XXX")
                    .pattern(" S ")
                    .pattern(" S ")
                    .group("pickaxes")
                    .unlockedBy("has_material", has(material))
                    .save(consumer);
        if (sword != null)
            ShapedRecipeBuilder.shaped(sword)
                    .define('X', material).define('S', Items.STICK)
                    .pattern("X")
                    .pattern("X")
                    .pattern("S")
                    .group("swords")
                    .unlockedBy("has_material", has(material))
                    .save(consumer);
        if (axe != null)
            ShapedRecipeBuilder.shaped(axe)
                    .define('X', material).define('S', Items.STICK)
                    .pattern("XX")
                    .pattern("SX")
                    .pattern("S ")
                    .group("axes")
                    .unlockedBy("has_material", has(material))
                    .save(consumer);
        if (shovel != null)
            ShapedRecipeBuilder.shaped(shovel)
                    .define('X', material).define('S', Items.STICK)
                    .pattern("X")
                    .pattern("S")
                    .pattern("S")
                    .group("shovels")
                    .unlockedBy("has_material", has(material))
                    .save(consumer);
        if (hoe != null)
            ShapedRecipeBuilder.shaped(hoe)
                    .define('X', material).define('S', Items.STICK)
                    .pattern("XX")
                    .pattern("S ")
                    .pattern("S ")
                    .group("hoes")
                    .unlockedBy("has_material", has(material))
                    .save(consumer);
    }
}
