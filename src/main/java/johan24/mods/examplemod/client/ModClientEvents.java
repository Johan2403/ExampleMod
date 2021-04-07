package johan24.forge.examplemod.client;

import johan24.forge.examplemod.ExampleMod;
import johan24.forge.examplemod.client.renderer.entity.layers.ModCapeLayer;
import johan24.forge.examplemod.config.ExampleModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.inventory.SmithingTableScreen;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
            if(event.getTarget().isAlive() && event.getTarget() instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) event.getTarget();
                PlayerEntity player = event.getPlayer();
                World world = player.getCommandSenderWorld();

                if(world.isClientSide()) {
                    String msg = TextFormatting.AQUA + "You hit the ";
                    player.sendMessage(new StringTextComponent(msg + entity.getName().getString()), player.getUUID());
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

    @SubscribeEvent
    public static void addModCapes(RenderPlayerEvent event) {
        if(event.getPlayer() instanceof AbstractClientPlayerEntity) {
            PlayerRenderer renderer = event.getRenderer();
            renderer.addLayer(new ModCapeLayer(renderer));
        }
    }

    @SubscribeEvent
    public static void shrug(ClientChatEvent event) {
        if(event.getMessage().equals("/shrug")) {
            event.setMessage("¯\\_(ツ)_/¯");
        }
    }
}
