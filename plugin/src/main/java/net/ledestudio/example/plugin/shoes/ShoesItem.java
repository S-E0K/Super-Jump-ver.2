package net.ledestudio.example.plugin.shoes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShoesItem {
    private final ItemStack shoes;

    public ShoesItem(Material material, String name) {
        shoes = new ItemStack(material);
        ItemMeta meta = shoes.getItemMeta();
        meta.setDisplayName(name);
        meta.setUnbreakable(true);
        shoes.setItemMeta(meta);
    }
    public ItemStack getShoes() {
        return shoes;
    }

}
