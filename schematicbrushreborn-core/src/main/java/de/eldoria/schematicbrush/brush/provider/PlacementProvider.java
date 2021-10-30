package de.eldoria.schematicbrush.brush.provider;

import de.eldoria.eldoutilities.commands.command.util.Arguments;
import de.eldoria.eldoutilities.commands.exceptions.CommandException;
import de.eldoria.schematicbrush.brush.config.provider.Mutator;
import de.eldoria.schematicbrush.brush.config.placement.APlacement;
import de.eldoria.schematicbrush.brush.config.placement.Bottom;
import de.eldoria.schematicbrush.brush.config.placement.Drop;
import de.eldoria.schematicbrush.brush.config.placement.Middle;
import de.eldoria.schematicbrush.brush.config.placement.Original;
import de.eldoria.schematicbrush.brush.config.placement.Raise;
import de.eldoria.schematicbrush.brush.config.placement.Top;
import de.eldoria.schematicbrush.brush.config.provider.ModifierProvider;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public abstract class PlacementProvider extends ModifierProvider {
    private static final APlacement bottom = new Bottom();
    public static final PlacementProvider BOTTOM = of("bottom", bottom);
    private static final APlacement drop = new Drop();
    public static final PlacementProvider DROP = of("drop", drop);
    private static final APlacement middle = new Middle();
    public static final PlacementProvider MIDDLE = of("middle", middle);
    private static final APlacement original = new Original();
    public static final PlacementProvider ORIGINAL = of("original", original);
    private static final APlacement raise = new Raise();
    public static final PlacementProvider RAISE = of("raise", raise);
    private static final APlacement top = new Top();
    public static final PlacementProvider TOP = of("top", top);

    public PlacementProvider(Class<? extends ConfigurationSerializable> clazz, String name) {
        super(clazz, name);
    }

    private static PlacementProvider of(String name, APlacement placement) {
        return new PlacementProvider(placement.getClass(), name) {
            @Override
            public Mutator<?> parse(Arguments args) throws CommandException {
                return placement;
            }
        };
    }

    @Override
    public List<String> complete(Arguments args, Player player) {
        return Collections.emptyList();
    }

    @Override
    public Mutator<?> defaultSetting() {
        return new Drop();
    }

    @Override
    public boolean hasArguments() {
        return false;
    }
}