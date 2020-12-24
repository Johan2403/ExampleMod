package johan24.examplemod.client;

import johan24.examplemod.ExampleMod;
import net.minecraft.client.gui.screen.inventory.SmithingTableScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import javax.annotation.Nullable;

/**
 * Contains events that fire client side for this mod.
 *
 * @author Johan24
 */
@EventBusSubscriber(modid = ExampleMod.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    /**
     * Doesn't let the player open the Smithing table Gui by
     * cancelling the GuiOpenEvent.
     * @param event - GuiOpenEvent
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void noSmithing(GuiOpenEvent event) {
        if(event.isCancelable()) {
            if(event.getGui() instanceof SmithingTableScreen) {
                event.setCanceled(true);
            }
        }
    }

    /**
     * Notifies the player on what mob they hit.
     * @param event - AttackEntityEvent
     */
    @SubscribeEvent
    public static void hitNotification(AttackEntityEvent event) {
        if(event.getTarget().isAlive()) {
            LivingEntity entity = (LivingEntity) event.getTarget();
            PlayerEntity player = event.getPlayer();

            if(!player.getEntityWorld().isRemote) {
                String msg = TextFormatting.AQUA + "You hit the ";
                player.sendMessage(new StringTextComponent(msg + removeNamespace(entity.getEntityString())), player.getUniqueID());
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
    private static String removeNamespace(@Nullable String string) {
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
