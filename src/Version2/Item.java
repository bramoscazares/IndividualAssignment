package Version2;

public class Item {
    private String itemName;
    private String itemDesciption;

    public Item(String itemName, String itemDesciption) {
        this.itemName = itemName;
        this.itemDesciption = itemDesciption;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesciption() {
        return itemDesciption;
    }
}
