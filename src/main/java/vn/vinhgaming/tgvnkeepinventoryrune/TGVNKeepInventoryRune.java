package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vn.vinhgaming.tgvnkeepinventoryrune.rune.InventoryRune;
import vn.vinhgaming.tgvnkeepinventoryrune.rune.ItemRune;

public final class TGVNKeepInventoryRune extends JavaPlugin {
    private static final InventoryRune inventoryRune = new InventoryRune();
    private static final ItemRune itemRune = new ItemRune();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getCommand("keepinventoryrune").setExecutor(new Commands());
        ConfigManager.init();
    }

    public static InventoryRune getInventoryRune() {
        return inventoryRune;
    }

    public static ItemRune getItemRune() {
        return itemRune;
    }
}
