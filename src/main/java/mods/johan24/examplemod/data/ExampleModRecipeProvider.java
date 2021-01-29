package mods.johan24.examplemod.data;

import mods.johan24.examplemod.ExampleMod;
import mods.johan24.examplemod.init.ModBlocks;
import mods.johan24.examplemod.init.ModItems;
import mods.johan24.examplemod.tags.ExampleTags;
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
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        addBasicArmorRecipes(consumer, ExampleTags.Items.INGOT_OBSIDIANITE, ModItems.OBSIDIANITE_HELMET.get(), ModItems.OBSIDIANITE_CHESTPLATE.get(), ModItems.OBSIDIANITE_LEGGINGS.get(), ModItems.OBSIDIANITE_BOOTS.get());
        addBasicToolRecipes(consumer, ExampleTags.Items.INGOT_OBSIDIANITE, ModItems.OBSIDIANITE_PICKAXE.get(), ModItems.OBSIDIANITE_SWORD.get(), ModItems.OBSIDIANITE_AXE.get(), ModItems.OBSIDIANITE_SHOVEL.get(), ModItems.OBSIDIANITE_HOE.get());

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.OBSIDIANITE_BLOCK.get())
                .key('X', ExampleTags.Items.INGOT_OBSIDIANITE)
                .patternLine("XXX").patternLine("XXX").patternLine("XXX")
                .addCriterion("has_obsidianite_ingot", hasItem(ExampleTags.Items.INGOT_OBSIDIANITE))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.OBSIDIANITE_INGOT.get(), 9)
                .addIngredient(ModBlocks.OBSIDIANITE_BLOCK.get())
                .setGroup("obsidianite_ingot")
                .addCriterion("has_obsidianite_block", hasItem(ModBlocks.OBSIDIANITE_BLOCK.get()))
                .build(consumer, "obsidianite_ingot_from_obsidianite_block");

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.OBSIDIANITE_INGOT.get(), 4)
                .addIngredient(ModItems.OBSIDIANITE_SCRAP.get())
                .addIngredient(ModItems.OBSIDIANITE_SCRAP.get())
                .addIngredient(ModItems.OBSIDIANITE_SCRAP.get())
                .addIngredient(ModItems.OBSIDIANITE_SCRAP.get())
                .addIngredient(Items.IRON_INGOT)
                .addIngredient(Items.IRON_INGOT)
                .addIngredient(Items.IRON_INGOT)
                .addIngredient(Items.IRON_INGOT)
                .addCriterion("has_obsidianite_scrap", hasItem(ModItems.OBSIDIANITE_SCRAP.get()))
                .build(consumer, "obsidianite_ingot_from_obsidianite_scrap");

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
            ShapedRecipeBuilder.shapedRecipe(head).key('X', material).patternLine("XXX").patternLine("X X").setGroup("helmets").addCriterion("has_material", hasItem(material)).build(consumer);
        if (chest != null)
            ShapedRecipeBuilder.shapedRecipe(chest).key('X', material).patternLine("X X").patternLine("XXX").patternLine("XXX").setGroup("chestplates").addCriterion("has_material", hasItem(material)).build(consumer);
        if (legs != null)
            ShapedRecipeBuilder.shapedRecipe(legs).key('X', material).patternLine("XXX").patternLine("X X").patternLine("X X").setGroup("leggings").addCriterion("has_material", hasItem(material)).build(consumer);
        if (feet != null)
            ShapedRecipeBuilder.shapedRecipe(feet).key('X', material).patternLine("X X").patternLine("X X").setGroup("boots").addCriterion("has_material", hasItem(material)).build(consumer);
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
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(ore), result, experience, time).addCriterion("has_ore", hasItem(ore)).build(consumer, new ResourceLocation(ExampleMod.MODID, name + "_from_smelting"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(ore), result, experience, time / 2).addCriterion("has_ore", hasItem(ore)).build(consumer, new ResourceLocation(ExampleMod.MODID, name + "_from_blasting"));
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
            ShapedRecipeBuilder.shapedRecipe(pickaxe)
                    .key('X', material).key('S', Items.STICK)
                    .patternLine("XXX")
                    .patternLine(" S ")
                    .patternLine(" S ")
                    .setGroup("pickaxes")
                    .addCriterion("has_material", hasItem(material))
                    .build(consumer);
        if (sword != null)
            ShapedRecipeBuilder.shapedRecipe(sword)
                    .key('X', material).key('S', Items.STICK)
                    .patternLine("X")
                    .patternLine("X")
                    .patternLine("S")
                    .setGroup("swords")
                    .addCriterion("has_material", hasItem(material))
                    .build(consumer);
        if (axe != null)
            ShapedRecipeBuilder.shapedRecipe(axe)
                    .key('X', material).key('S', Items.STICK)
                    .patternLine("XX")
                    .patternLine("SX")
                    .patternLine("S ")
                    .setGroup("axes")
                    .addCriterion("has_material", hasItem(material))
                    .build(consumer);
        if (shovel != null)
            ShapedRecipeBuilder.shapedRecipe(shovel)
                    .key('X', material).key('S', Items.STICK)
                    .patternLine("X")
                    .patternLine("S")
                    .patternLine("S")
                    .setGroup("shovels")
                    .addCriterion("has_material", hasItem(material))
                    .build(consumer);
        if (hoe != null)
            ShapedRecipeBuilder.shapedRecipe(hoe)
                    .key('X', material).key('S', Items.STICK)
                    .patternLine("XX")
                    .patternLine("S ")
                    .patternLine("S ")
                    .setGroup("hoes")
                    .addCriterion("has_material", hasItem(material))
                    .build(consumer);
    }
}
