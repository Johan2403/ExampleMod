package johan24.mods.examplemod.config;

import johan24.mods.examplemod.ExampleMod;
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
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Server SERVER;

    static {
        final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
        final Pair<Server, ForgeConfigSpec> server = new ForgeConfigSpec.Builder().configure(Server::new);

        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();

        SERVER_SPEC = server.getRight();
        SERVER = server.getLeft();
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

    /**
     * Contains Server Settings.
     */
    public static class Server {
        public final ForgeConfigSpec.BooleanValue spawnZombieKnight;

        Server(final ForgeConfigSpec.Builder builder) {
            builder.comment("Server config settings")
                    .push("server");

            spawnZombieKnight = builder.comment("Set to true if you want to spawn zombie knights.")
                    .translation("examplemod.config.server.spawnZombieKnights")
                    .worldRestart()
                    .define("spawnZombieKnights", true);

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
