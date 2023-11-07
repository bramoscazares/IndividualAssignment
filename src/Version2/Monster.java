package Version2;

public class Monster {
    private String monsterName;
    private String[] description;
    private int roomLocation,healthPoints,attackPoints,threshold;

    private boolean defeated = false;

    public Monster(String monsterName,String[] description, int roomLocation, int healthPoints, int attackPoints,int threshold) {
        this.monsterName = monsterName;
        this.description = description;
        this.roomLocation = roomLocation;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
        this.threshold = threshold;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public String[] getDescription() {
        return description;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getRoomLocation() { return this.roomLocation;
    }

    public void monsterDefeated(){
        this.defeated = true;
    }

    public boolean getMonsterDefeat(){
        return this.defeated;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
