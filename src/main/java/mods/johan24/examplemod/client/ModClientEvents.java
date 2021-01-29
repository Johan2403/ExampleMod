package mods.johan24.examplemod.client;

import mods.johan24.examplemod.ExampleMod;
import mods.johan24.examplemod.config.ExampleModConfig;
import net.minecraft.client.gui.screen.inventory.SmithingTableScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

/**
 * Contains events that fire client side for this mod.
 *
 * @author Johan24
 */
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    /**
     * Notifies the player on what mob they hit.
     * @param event - AttackEntityEvent
     */
    @SubscribeEvent
    public static void hitNotification(AttackEntityEvent event) {
        if(ExampleModConfig.CLIENT.enableHitNotification.get()) {
            if(event.getTarget().isAlive()) {
                LivingEntity entity = (LivingEntity) event.getTarget();
                PlayerEntity player = event.getPlayer();
                World world = player.getEntityWorld();

                if(!world.isRemote) {
                    String msg = TextFormatting.AQUA + "You hit the ";
                    player.sendMessage(new StringTextComponent(msg + removeNamespace(entity.getEntityString())), player.getUniqueID());
                }
            }
        }
    }

    /**
     * Doesn't let the player open the Smithing table Gui by
     * cancelling the GuiOpenEvent.
     * @param event - GuiOpenEvent
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void noSmithing(GuiOpenEvent event) {
        if(ExampleModConfig.CLIENT.disableSmithing.get()) {
            if(event.isCancelable()) {
                if(event.getGui() instanceof SmithingTableScreen) {
                    event.setCanceled(true);
                }
            }
        }
    }

    /**
     * Removes namespaces in a String. Also removes the underscores and replaces
     * with spaces.
     *
     * @param string - The string to be checked for namespace.
     * @return - The string without the namespace.
     */
    public static String removeNamespace(@Nullable String string) {
        if(string != null) {
            if(string.contains("minecraft")) {
                string = string.replaceAll("minecraft" + ":", "");
                if(string.contains("_")) {
                    string = string.replaceAll("_", " ");
                }
            }
        }
        return string;
    }
}
