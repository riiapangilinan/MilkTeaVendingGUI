/**
 * Represents a slot in a vending machine that holds an item.
 */
public class ItemSlot {
    private int itemCode;
    private String itemName;
    private int price;
    private int quantity;
    private int calories;

    /**
     * Creates a new ItemSlot with the given parameters.
     * @param itemCode the unique code of the item
     * @param itemName the name of the item
     * @param price the price of the item in the vending machine
     * @param quantity the quantity of the item available in the slot
     * @param calories the number of calories in the item
     */
    public ItemSlot(int itemCode, String itemName, int price, int quantity, int calories) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.calories = calories;
    }

    /**
     * Returns the unique code of the item.
     * @return the item code
     */
    public int getItemCode() {
        return itemCode;
    }

    /**
     * Returns the name of the item.
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the price of the item in the vending machine.
     * @return the item price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the item available in the slot.
     * @return the item quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the number of calories in the item.
     * @return the item calories
     */
    public int getCalories() {
        return calories;
    }
}
