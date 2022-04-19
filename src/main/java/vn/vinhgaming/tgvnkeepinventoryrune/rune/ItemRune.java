package vn.vinhgaming.tgvnkeepinventoryrune.rune;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vn.vinhgaming.tgvnkeepinventoryrune.ConfigManager;
import vn.vinhgaming.tgvnkeepinventoryrune.Utils;

import java.util.*;

public class ItemRune extends Rune {
    String protectedLore;
    Map<UUID, ItemStack[]> invList;
    List<String> whitelistedMaterial;

    public void init(String material, String name, List<String> lore, String protectedLore, List<String> whitelistedMaterial) {
        this.whitelistedMaterial = whitelistedMaterial;
        this.protectedLore = Utils.translate(protectedLore);
        invList = new HashMap<>();
        init(material, name, lore);
    }

    /**
     * Check if item contain the defined lore
     *
     * @param item The item to check
     * @return true false, what else?
     */
    public boolean checkItem(ItemStack item) {
        return item != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains(protectedLore);
    }

    public ItemStack addLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        lore.add(protectedLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack removeLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return item;
        List<String> lore = meta.getLore();
        lore.remove(protectedLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void addInventory(Player player, ItemStack[] contents) {
        invList.put(player.getUniqueId(), contents);
    }

    public void returnInventory(Player player) {
        ItemStack[] contents = invList.remove(player.getUniqueId());
        if (contents == null) return;
        player.getInventory().setContents(contents);
        player.sendMessage(ConfigManager.getMessage("ItemRuneUsed"));
    }

    public boolean isWhitelisted(String name) {
        return whitelistedMaterial.contains("*") || whitelistedMaterial.contains(name);
    }
}
