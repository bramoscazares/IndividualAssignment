package Version2;

public class Item {
    private String itemName;
    private String itemDesciption;
    public int roomLocation;

    public Item(String itemName, String itemDesciption, int roomLocation) {
        this.itemName = itemName;
        this.itemDesciption = itemDesciption;
        this.roomLocation = roomLocation;
    }


    //GETTERS ================================================
    public String getItemName() {
        return itemName;
    }

    public String getItemDesciption() {
        return itemDesciption;
    }
}
