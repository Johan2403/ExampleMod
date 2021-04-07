package johan24.mods.examplemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import johan24.mods.examplemod.ExampleMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class ModCommands {

    public static void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> exampleCmd = dispatcher.register(
                Commands.literal(ExampleMod.MODID)
                .then(CommandExample.register())
        );

        dispatcher.register(Commands.literal("hehe").executes((context) -> {
            context.getSource().sendSuccess(new StringTextComponent("LOL"), false);
            return 0;
        }));
    }
}
