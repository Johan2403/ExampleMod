package johan24.mods.examplemod.util.enums;

import johan24.mods.examplemod.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * The enum which holds the ItemTier for the Mod ExampleMod.
 * ItemTiers are essential for the creation of Tools.
 *
 * @author Johan24
 */
public enum ModItemTier implements IItemTier {

    /**
     * The ItemTier for the Obsidianite set of tools.
     */
    OBSIDIANITE(3, 1893, 7.5F, 2.5F, 22, () -> {
        return Ingredient.of(ModItems.OBSIDIANITE_INGOT.get());
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
