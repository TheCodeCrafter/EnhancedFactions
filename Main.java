package net.fathomtech.plugins.EnhancedFactions;

public class Main extends JavaPlugin {
  
  FocusFile focus;
  UpgradeFile upgrades;
  
  HashMap<String, ArrayList<String>> cache = new HashMap<String, ArrayList<String>>();
  HashMap<Faction, FactionUpgrades> upgrades = new HashMap<Faction, FactionUpgrades>();
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    
    focus = new FocusFile(this);
    upgradeFile = new UpgradeFile(this);
    
    for(String key : focus.data.getSection("data").getKeys(false)) {
      ArrayList<String> recipients = focus.data.getList("data." + key);
      cache.put(key, recipients);
    }
    
    for(Faction faction : Factions.getAllFactions()) {
      // Get data, then create new FactionUpgrades class and alter data
      if(upgradeFile.data.get(faction.getName() == null)) {
        createNewUpgradeData(faction.getName());
        upgrades.put(faction, new FactionUpgrades(faction));
      }
      
      FactionUpgrades fUpgrades = new FactionUpgrades(faction);
      fUpgrades.setFly(upgradeFile.data.getBool(faction.getName() + ".fly"));
      fUpgrades.setFlySpeedMultiplier(upgradeFile.data.getFloat(faction.getName() + ".flyMultiplier"));
      fUpgrades.setDamageMultiplier(upgradeFile.data.getFloat(faction.getName() + ".damageMultiplier"));
      fUpgrades.setDamageModifier(upgradeFile.data.getFloat(faction.getName() + ".damageTakenModifier"));
      fUpgrades.setHasteMultiplier(upgradeFile.data.getFloat(faction.getName() + ".hasteMultiplier"));
      fUpgrades.setCostMultiplier(upgradeFile.data.getFloat(faction.getName() + ".costMultiplier"));
      
      upgrades.put(faction, fUpgrades);
    }
  }
  
  @Override
  public void onDisable() {
    saveConfig();
    focus.save();
    upgrades.save();
  }
  
  public void addFocusedPlayer(FPlayer target, Faction faction) {
    Set<FPlayer> members = faction.getFPlayersWhereOnline(true);
    faction.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + target.getName() + " has been focused!");
    focus.data.setList(target.getName() + ".recipients", members);
    this.focusPlayer(target, members);
  }
  
  public void addFocusedPlayer(FPlayer target, Faction faction, FPlayer sender, String message) {
    Set<FPlayer> members = faction.getFPlayersWhereOnline(true);
    faction.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + target.getName() + " has been focused!");
    faction.addAnnouncement(sender, message);
    focus.data.setList(target.getName() + ".recipients", members);
    this.focusPlayer(target, members);
  }
  
  private void focusPlayer(FPlayer target, Set<FPlayer> recipients) {
    for(FPlayer player : recipients) {
      changeNameTag(player.getPlayer(), target.getPlayer(), getConfig().getString("focusedPrefix"), getConfig().getString("focusedSuffix"), TeamAction.CREATE);
    }
  }
  
  private void unfocusPlayer(FPlayer target, Set<FPlayer> recipients) {
    for(FPlayer player : recipients) {
      changeNameTag(player.getPlayer(), target.getPlayer(), "", "", TeamAction.DESTROY);
    }
  }
  
  private void createNewUpgradeData(String factionName) {
    upgradeFile.data.set(factionName + ".fly", false);
    upgradeFile.data.set(factionName + ".flyMultiplier", 1.0);
    upgradeFile.data.set(factionName + ".damageMultiplier", 1.0);
    upgradeFile.data.set(factionName + ".damageTakenModifier", 1.0);
    upgradeFile.data.set(factionName + ".hasteMultiplier", 1.0);
    upgradeFile.data.set(factionName + ".speedMultiplier", 1.0);
    upgradeFile.data.set(factionName + ".costModifier", 1.0);
  }
}
