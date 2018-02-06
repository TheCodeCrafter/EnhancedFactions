package net.fathomtech.plugins.EnhancedFactions.commands;

public class Focus implements CommandExecutor() {
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    public Focus(Main main) {
      this.plugin = main;
    }
    
    FPlayer fplayer;
    Faction myFaction;
    
    if(label.equalsIgnoreCase("f") || label.equalsIgnoreCase("faction")) {
      if(sender instanceof Player) {
        fplayer = FPlayers.getInstance().getByPlayer((Player) sender);
        myFaction = fplayer.getFaction();
        
        if(myFaction == null) {
          fplayer.sendMessage(ChatColor.RED + "You must be in a faction to do this! Join a faction with /f join <faction/player>");
          return false;
        }
        
        if(args.length == 1 && args[0].equalsIgnoreCase("focus")) {
          fplayer.sendMessage(ChatColor.GREEN + "Use this to focus on a player for your faction. This will highlight their username for you and your allies");
          return false;
        } else if(args.length == 2 && args[0].equalsIgnoreCase("focus")) {
          // GET THE PLAYER THEY'RE FOCUSING ON
          String name = args[1];
          FPlayer target;
          
          for (FPlayer pplayer : FPlayers.getInstance().getAllFPlayers()) {
            if (pplayer.getName().equalsIgnoreCase(name)) {
              target = pplayer;
              break;
            }
          }
          
          if(target == null) {
            fplayer.sendMessage(ChatColor.RED + "" + name + " could not be found.");
            return false;
          }
          
          this.plugin.addFocusedPlayer(target, myFaction);
          return true;
          
        } else if(args.length >= 3 && args[0].equalsIgnoreCase("focus")) {
          // GET THE PLAYER THEY'RE FOCUSING ON
          String name = args[1];
          String message = args[2];
          FPlayer target;
          
          for (FPlayer pplayer : FPlayers.getInstance().getAllFPlayers()) {
            if (pplayer.getName().equalsIgnoreCase(name)) {
              target = pplayer;
              break;
            }
          }
          
          if(target == null) {
            fplayer.sendMessage(ChatColor.RED + "" + name + " could not be found.");
            return false;
          }
          
          if(args.length > 4) {
            for(int i = 3; i < args.length; i++) {
              message += args[i];
            }
          }
          
          this.plugin.addFocusedPlayer(target, myFaction, fplayer, message);
          return true;
        }
        
      } else {
        // SENDER IS NOT A PLAYER
        return false;
      }
    }
    
    
  }
}
