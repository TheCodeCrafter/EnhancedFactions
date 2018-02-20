package net.fathomtech.plugins.EnhancedFactions.commands;

public class UpgradeCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(!(sender instanceof Player)) {
      sender.sendMessage("You must be a player and in a faction to use this command!");
    }
  }
}
