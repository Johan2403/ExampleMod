package johan24.forge.examplemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class CommandExample implements Command<CommandSource> {
    private static final CommandExample EXAMPLE = new CommandExample();

    /**
     * Argument builder which builds an argument.
     * Can be used as command when registered with a dispatcher.
     * @return - result
     */
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("thanks")
                .requires(commandSource -> commandSource.hasPermission(0)).executes(EXAMPLE);
    }

    /**
     * defines what this command should do.
     * @param context - Command Context
     * @return - ¯\_(ツ)_/¯
     */
    @Override
    public int run(CommandContext<CommandSource> context) {
        context.getSource().sendSuccess(new StringTextComponent("Thanks for using Example Mod!"), false);
        return 0;
    }
}
