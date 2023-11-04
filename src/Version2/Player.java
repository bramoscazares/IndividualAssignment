package Version2;

import java.util.ArrayList;

public class Player {

    public ArrayList<Item> playerInventory = new ArrayList<>();

    public ArrayList<Item> equippedInventory = new ArrayList<>();

    public int healthPoints;
    public int attackPoints;

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
