package me.realized.duels.hooks;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.util.hook.AbstractHookManager;

public class HookManager extends AbstractHookManager<DuelsPlugin> {

    public HookManager(final DuelsPlugin plugin) {
        super(plugin);
        register("Vault", VaultHook.class);
        register("Essentials", EssentialsHook.class);
        register("MVdWPlaceholderAPI", MVdWPlaceholderHook.class);
        register("PvPManager", PvPManagerHook.class);
        register("CombatTagPlus", CombatTagPlusHook.class);
        register("Factions", FactionsHook.class);
        register("mcMMO", McMMOHook.class);
        register("WorldGuard", WorldGuardHook.class);
    }
}