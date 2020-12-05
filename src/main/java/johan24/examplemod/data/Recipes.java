package johan24.examplemod.data;

import johan24.examplemod.init.ModItems;
import johan24.examplemod.tags.ExampleTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider implements IConditionBuilder {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        addBasicArmorRecipes(consumer, ExampleTags.Items.INGOT_OBSIDIANITE, ModItems.OBSIDIANITE_HELMET.get(), ModItems.OBSIDIANITE_CHESTPLATE.get(), ModItems.OBSIDIANITE_LEGGINGS.get(), ModItems.OBSIDIANITE_BOOTS.get());
    }

    protected static void addBasicArmorRecipes(Consumer<IFinishedRecipe> consumer, INamedTag<Item> material, @Nullable Item head, @Nullable Item chest, @Nullable Item legs, @Nullable Item feet) {
        if(head != null) ShapedRecipeBuilder.shapedRecipe(head).key('X', material).patternLine("XXX").patternLine("X X").setGroup("helmets").addCriterion("has_material", hasItem(material)).build(consumer);
        if(chest != null) ShapedRecipeBuilder.shapedRecipe(chest).key('X', material).patternLine("X X").patternLine("XXX").patternLine("XXX").setGroup("chestplates").addCriterion("has_material", hasItem(material)).build(consumer);
        if(legs != null) ShapedRecipeBuilder.shapedRecipe(legs).key('X', material).patternLine("XXX").patternLine("X X").patternLine("X X").setGroup("leggings").addCriterion("has_material", hasItem(material)).build(consumer);
        if(feet != null) ShapedRecipeBuilder.shapedRecipe(feet).key('X', material).patternLine("X X").patternLine("X X").setGroup("boots").addCriterion("has_material", hasItem(material)).build(consumer);
    }
}
