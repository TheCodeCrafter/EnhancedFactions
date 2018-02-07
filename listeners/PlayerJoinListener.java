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
    Set<String> focusedPlayers = (Set<String>) this.plugin.focus.data.getKeys(false);
    
    // LOOP THROUGH RECIPIENTS TO SEE IF YOU ARE FOCUSED
    for(String name : focusedPlayers) {
      if(this.plugin.focus.data.getSet(name + ".recipients").contains(player.getName)) {
        // SEND NEW PACKET
        this.plugin.focusPlayer(FPlayers.getInstance().getByName(name), FPlayers.getInstance().getByPlayer(player));
      }
    }
  }
}
