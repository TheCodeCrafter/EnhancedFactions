package net.fathomtech.plugins.EnhancedFactions.commands;

public class UpgradeCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(!(sender instanceof Player)) {
      sender.sendMessage("You must be a player and in a faction to use this command!");
    }
    
    
    Player player = (Player) sender;
    FPlayer factionPlayer = FPlayers.getByPlayer(player);
    Faction faction = factionPlayer.getFaction();
    
    if(faction == null || faction.isWilderness()) {
      factionPlayer.sendMessage("You must be in a faction to use this command!");
      return true;
    }
    
    // Since we now know they're in a faction, let's check to see if they have permission
    
  }
}
