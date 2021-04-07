package johan24.forge.examplemod.item;

import johan24.forge.examplemod.ExampleMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/**
 * Obsidianite Apple Item class.
 * Required because need to override hasEffect()
 *
 * @author Johan24
 */
public class ObsidianiteAppleItem extends Item {
    public ObsidianiteAppleItem() {
        super(new Item.Properties().food(new Food.Builder()
                                                 .nutrition(6)
                                                 .saturationMod(14.0F)
                                                 .alwaysEat()
                                                 .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, (60 * 30)), 1)
                                                 .effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, (60 * 15)), 1)
                                                 .build()).tab(ExampleMod.TAB));
    }

    /**
     * Adds a glint to the Item.
     * @param stack - ItemStack
     * @return - boolean
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
