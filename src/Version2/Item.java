package Version2;

public class Item {
    private String itemName;
    private String itemDesciption;
    public int roomLocation;

    public String itemType;
    public int healthPoints;
    public int attackPoints;


    public Item(String itemName, String itemDesciption, int roomLocation, String itemType, int healthPoints,int attackPoints) {
        this.itemName = itemName;
        this.itemDesciption = itemDesciption;
        this.roomLocation = roomLocation;
        this.itemType = itemType;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
    }

    //GETTERS ================================================
    public String getItemName() {
        return itemName;
    }

    public String getItemDesciption() {
        return itemDesciption;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public int getAttackPoints() {
        return attackPoints;
    }

}
