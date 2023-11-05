package Version2;

import java.util.ArrayList;

public class Player {

    public ArrayList<Item> playerInventory = new ArrayList<>();

    public ArrayList<Item> equippedInventory = new ArrayList<>();

    public String firstName,lastname,description;
    public int healthPoints;
    public int attackPoints;

    public Player(String firstName, String lastname, String description, int healthPoints, int attackPoints) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.description = description;
        this.healthPoints = healthPoints;
        this.attackPoints = attackPoints;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDescription() {
        return description;
    }
}
