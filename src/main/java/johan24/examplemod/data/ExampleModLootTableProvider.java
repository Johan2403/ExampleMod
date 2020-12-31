package johan24.examplemod.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import johan24.examplemod.ExampleMod;
import johan24.examplemod.data.loot.ExampleModBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.function.Consumer;

/**
 * Generates the mod's Loot Tables.
 *
 * @author Johan24
 */
public class ExampleModLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTableGenerators = ImmutableList.of(
            Pair.of(ExampleModBlockLootTables::new, LootParameterSets.BLOCK)
    );

    public ExampleModLootTableProvider(final DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return lootTableGenerators;
    }

    @Override
    protected void validate(final Map<ResourceLocation, LootTable> map, @Nonnull final ValidationTracker validationtracker) {
        final Set<ResourceLocation> modLootTableIds = LootTables
                .getReadOnlyLootTables()
                .stream()
                .filter(lootTable -> lootTable.getNamespace().equals(ExampleMod.MODID))
                .collect(Collectors.toSet());

        for (final ResourceLocation id : Sets.difference(modLootTableIds, map.keySet())) {
            validationtracker.addProblem("Missing mod loot table: " + id);
        }

        map.forEach((id, lootTable) -> LootTableManager.validateLootTable(validationtracker, id, lootTable));
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "ExampleModLootTables";
    }
}
