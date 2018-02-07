package net.fathomtech.plugins.EnhancedFactions.listeners;

public class PlayerJoinListener implements Listener {
  @EventHandler
  public void isPlayerFocusedOnJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    
    Player focusedPlayer = null;
    
    // GET DATA
    Set<String> focusedPlayers = this.plugin.focus.data.getKeys(false);
    
    // CHECK IF PLAYER IS FOCUSED
    for(String name : focusedPlayers) {
      if(name.equalsIgnoreCase(player.getName())) {
        Set<FPlayers> recipients = (Set<FPlayers>) this.plugin.focus.data.getSet(name + ".recipients");
        this.plugin.focusPlayer(FPlayers.getInstance().getByPlayer(player), recipients);
      }
    }
         
  }
  
  @EventHandler
  public void isPlayerRecipientFocusedPlayer(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    
    // FIND OUT WHO IS FOCUSED
    
    // SEND PACKET TO RE-FOCUS ON THAT PLAYER
  }
}
