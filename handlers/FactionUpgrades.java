package net.fathomtech.plugins.EnhancedFactions.handlers;

public class FactionUpgrades {
  private boolean fly = false;
  private float flySpeedMultiplier = 1.0;
  
  private float damageMultiplier = 1.0;
  private float damageTakenModifier = 1.0;
  private float hasteMultiplier = 1.0;
  private float speedMultiplier = 1.0;
  private float costModifier = 1.0;
  
  public Faction faction;
  
  public FactionUpgrades(Faction faction) {
    this.faction = faction;
  }
  
  // GETTERS
  public boolean canFly() {
    return fly;
  }
  
  public float getFlySpeed() {
    return flySpeedMultiplier;
  }
  
  public float getDamageMultiplier() {
    return damageMultiplier;
  }
  
  public float getDamageTakenModifier() {
    return damageTakenModifier; // TODO: RETURN IN FRACTION FORM
  }
  
  public float getHasteMultiplier() {
    return hasteMultiplier;
  }
  
  public float getSpeedMultiplier() {
    return speedMultiplier;
  }
  
  public float getCostModifier() {
    return costModifier;
  }
  
  public String getFancyCostModifier() {
    return "" + (costModifier * 100) + "%";
  }
}
