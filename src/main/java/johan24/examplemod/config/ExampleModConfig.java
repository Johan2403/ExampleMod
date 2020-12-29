package johan24.examplemod.config;

import johan24.examplemod.ExampleMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This class builds the configs for this mod.
 *
 * @author Johan24
 */
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleModConfig {
    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    /**
     * Contains Common Settings.
     */
    public static class Common {
        public final ForgeConfigSpec.BooleanValue spawnZombieKnight;

        Common(final ForgeConfigSpec.Builder builder) {
            builder.comment("Common config settings")
                   .push("common");

            spawnZombieKnight = builder.comment("Set to true if you want to spawn zombie knights.")
                                       .translation("examplemod.config.common.spawnZombieKnights")
                                       .worldRestart()
                                       .define("spawnZombieKnights", true);

            builder.pop();
        }
    }

    /**
     * Contains Client Settings.
     */
    public static class Client {
        public final ForgeConfigSpec.BooleanValue enableHitNotification;
        public final ForgeConfigSpec.BooleanValue disableSmithing;

        Client(final ForgeConfigSpec.Builder builder) {
            builder.comment("Client-only settings")
                   .push("client");

            enableHitNotification = builder.comment("Set to true if you want entity hit notifications.")
                                           .translation("examplemod.config.client.enableHitNotification")
                                           .define("enableHitNotification", true);

            disableSmithing = builder.comment("Set to true if you want to disable smithing.")
                                     .translation("examplemod.config.client.disableSmithing")
                                     .define("disableSmithing", false);

            builder.pop();
        }
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        ExampleMod.LOGGER.debug("Loaded ExampleMod Config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
        ExampleMod.LOGGER.debug("ExampleMod Config just got changed on the file system!");
    }
}
