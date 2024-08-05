package net.ledestudio.example.plugin.shoes;

import org.bukkit.entity.Player;

public interface ShoesInterface {
    public void shoesAbility(Player player);
    public boolean hasShoes(Player player);
    public boolean wearShoes(Player player);
    public void setShoes(Player player);
}
