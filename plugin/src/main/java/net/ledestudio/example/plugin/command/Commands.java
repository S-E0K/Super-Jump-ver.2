package net.ledestudio.example.plugin.command;

import net.ledestudio.example.plugin.shoes.ShoesInterface;
import net.ledestudio.example.plugin.shoes.ShoesManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands extends BukkitCommand {
    public Commands(String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String command, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        ShoesInterface shoesManager = new ShoesManager();
        shoesManager.shoesAbility(player);

        if (shoesManager.hasShoes(player)) player.sendMessage("이미 가지고 있습니다");
        else shoesManager.setShoes(player);

        return false;
    }
}
