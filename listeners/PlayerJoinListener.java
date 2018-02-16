package net.fathomtech.plugins.EnhancedFactions.listeners;

public class PlayerJoinListener implements Listener {
  @EventHandler
  public void isPlayerFocusedOnJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    
    for(String fPlayer : plugin.cache.keySet()) {
      if(fPlayer.equalsIgnoreCase(player.getName()) {
        ArrayList<String> recipients = plugin.cache.get(fPlayer);
        
        for(String recipientName : recipients) {
          FPlayer recipient = FPlayers.getInstance().getByName(recipientName);
          this.plugin.focusPlayer(FPlayers.getInstance().getByName(player.getName()), recipient);
        }
      }
    }
  }
  
  @EventHandler
  public void isPlayerRecipientFocusedPlayer(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    
    // FIND OUT WHO IS FOCUSED
    Set<String> focusedPlayers = plugin.cache.keySet();
    
    // LOOP THROUGH RECIPIENTS TO SEE IF YOU ARE FOCUSED
    for(String name : focusedPlayers) {
      if(this.plugin.focus.data.getSet(name + ".recipients").contains(player.getName())) {
        // SEND NEW PACKET
        this.plugin.focusPlayer(FPlayers.getInstance().getByName(name), FPlayers.getInstance().getByPlayer(player));
      }
    }
  }
}
