package mods.johan24.examplemod.util.enums;

import mods.johan24.examplemod.ExampleMod;
import mods.johan24.examplemod.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * The enum which holds the ArmorMaterial for the Mod ExampleMod.
 * ArmorMaterials are essential for the creation of Armor.
 *
 * @author Johan24
 */
public enum ModArmorMaterial implements IArmorMaterial {

    /**
     * The ArmorMaterial for the Obsidianite set of armor.
     */
    OBSIDIANITE(ExampleMod.MODID + ":obsidianite", 25, new int[] { 2, 5, 6, 3 }, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1, 0.2F, () -> { return Ingredient.fromItems(ModItems.OBSIDIANITE_INGOT.get());});

    private final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    /**
     * Gets the durability of the armor provided by the armor
     * of the material.
     * @param slotIn - Equipment Slot
     */
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return this.MAX_DAMAGE_ARRAY[slotIn.getIndex()] * maxDamageFactor;
    }

    /**
     * Gets the damage reduction percent provided by the armor of
     * the material.
     * @param slotIn - Equipment Slot
     */
    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    /**
     * Gets the Enchantability provided by the armor of the material.
     */
    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    /**
     * Gets the sound while equipping the armor provided by the armor of
     * the material.
     */
    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    /**
     * Gets the repair material provided by the armor of the material.
     */
    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    /**
     * Gets the name of the armor material.
     * @return - Name of armor material
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the toughness provided by armor of the material.
     */
    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
