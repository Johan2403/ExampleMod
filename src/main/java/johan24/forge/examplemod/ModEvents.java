package johan24.forge.examplemod;

import johan24.forge.examplemod.config.ExampleModConfig;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Contains events that fire synced between client and server for this mod.
 *
 * @author Johan24
 */
@EventBusSubscriber(modid = ExampleMod.MODID, bus = Bus.FORGE)
public class ModEvents {

    /**
     * Makes zombies spawn with Diamond Axes.
     *
     * @param event - EntityJoinWorldEvent
     */
    @SubscribeEvent
    public static void zombieKnights(EntityJoinWorldEvent event) {
        if(ExampleModConfig.SERVER.spawnZombieKnight.get()) {
            if(!event.getWorld().isClientSide()) {
                if(!(event.getEntity() instanceof ZombieEntity))
                    return;

                ZombieEntity zombie = (ZombieEntity) event.getEntity();
                zombie.setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
            }
        }
    }
}
