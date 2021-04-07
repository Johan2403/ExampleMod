package johan24.mods.examplemod.util.enums;

import johan24.mods.examplemod.ExampleMod;
import johan24.mods.examplemod.init.ModItems;
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
            SoundEvents.ARMOR_EQUIP_GOLD, 1, 0.2F, () -> { return Ingredient.of(ModItems.OBSIDIANITE_INGOT.get());});

    private final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    /**
     * Gets the durability of the armor provided by the armor
     * of the material.
     * @param slotIn - Equipment Slot
     */
    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return this.HEALTH_PER_SLOT[slotIn.getIndex()] * durabilityMultiplier;
    }

    /**
     * Gets the damage reduction percent provided by the armor of
     * the material.
     * @param slotIn - Equipment Slot
     */
    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return this.slotProtections[slotIn.getIndex()];
    }

    /**
     * Gets the Enchantability provided by the armor of the material.
     */
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    /**
     * Gets the sound while equipping the armor provided by the armor of
     * the material.
     */
    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    /**
     * Gets the repair material provided by the armor of the material.
     */
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
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
