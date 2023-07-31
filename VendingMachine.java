import java.util.*;

/**
 * The VendingMachine class represents a vending machine that sells various items.
 * It keeps track of the item slots, prices, calories, stock, and sales information.
 * The vending machine allows restocking items, setting item prices, collecting money, and generating transaction summaries.
 */
public class VendingMachine {

    private List<ItemSlot> itemSlots;
    private List<ItemSlot> itemSlots2;
    private Map<Integer, Integer> itemPrices;
    private Map<Integer, Integer> itemCalories;
    private Map<Integer, Integer> itemStock;
    private Map<Integer, Integer> itemsSold;
    private int moneyCollected;
    private int startingInventory;

    /**
     * Constructs a VendingMachine object with default settings.
     * Initializes the item slots and sets the starting inventory.
     */
    public VendingMachine() {
        itemSlots = new ArrayList<>();
        itemSlots2 = new ArrayList<>();
        itemPrices = new HashMap<>();
        itemCalories = new HashMap<>();
        itemStock = new HashMap<>();
        itemsSold = new HashMap<>();
        moneyCollected = 0;
        startingInventory = 0;
        initializeItemSlots();
        getAdditionalItems();
    }

    /**
     * Initializes the item slots with their respective codes, names, prices, quantities, and calories.
     * Also populates the item prices, calories, stock, and sales maps.
     */
    public void initializeItemSlots() {
        itemSlots.add(new ItemSlot(1, "Green Tea Leaves  ", 120, 10, 0));
        itemSlots.add(new ItemSlot(2, "Black Tea Leaves  ", 150, 10, 1));
        itemSlots.add(new ItemSlot(3, "Peaches            ", 30, 10, 42));
        itemSlots.add(new ItemSlot(4, "Winter Melon       ", 70, 10, 13));
        itemSlots.add(new ItemSlot(5, "Tapioca Pearls    ", 100, 10, 358));
        itemSlots.add(new ItemSlot(6, "Creamcheese       ", 130, 10, 342));
        itemSlots.add(new ItemSlot(7, "Cow's Milk         ", 99, 10, 52));
        itemSlots.add(new ItemSlot(8, "Oat Milk           ", 88, 10, 130));

        for (ItemSlot slot : itemSlots) {
            int itemCode = slot.getItemCode();
            itemPrices.put(itemCode, slot.getPrice()); // Fixed: Use getPrice() instead of (int) slot.getPrice()
            itemCalories.put(itemCode, slot.getCalories());
            itemStock.put(itemCode, slot.getQuantity());
            itemsSold.put(itemCode, 0);
        }
        startingInventory = 80; // item slots * maximum slot capacity
    }

    /**
     * Returns a list of additional items for cups and sugar.
     * 
     * @return a list of additional items
     */
    private void getAdditionalItems() {
        itemSlots2.add(new ItemSlot(9, "Small Cup          ", 0, 10, 0));
        itemSlots2.add(new ItemSlot(10, "Medium Cup       ", 20, 10, 0));
        itemSlots2.add(new ItemSlot(11, "Large Cup        ", 40, 10, 0));
        itemSlots2.add(new ItemSlot(12, "0% Sugar          ", 0, 10, 0));
        itemSlots2.add(new ItemSlot(13, "30% Sugar         ", 0, 10, 300));
        itemSlots2.add(new ItemSlot(14, "70% Sugar         ", 0, 10, 700));
        itemSlots2.add(new ItemSlot(15, "100% Sugar        ", 0, 10, 1000));

        for (ItemSlot slot : itemSlots2) {
            int itemCode = slot.getItemCode();
            itemPrices.put(itemCode, slot.getPrice());
            itemCalories.put(itemCode, slot.getCalories());
            itemStock.put(itemCode, slot.getQuantity());
        }
    }

    /**
     * Restocks the specified item with the given quantity.
     * Checks if the item code is valid and the restock quantity is within the maximum capacity per slot.
     * 
     * @param itemCode  the code of the item to be restocked
     * @param quantity  the quantity to restock
     */
    public void restockItem(int itemCode, int quantity) {
        if (itemStock.containsKey(itemCode)) {
            int currentStock = itemStock.get(itemCode);
            int additionalStock = 10 - currentStock;

            if (quantity <= additionalStock) {
                itemStock.put(itemCode, currentStock + quantity);
                System.out.print("\nItem [" + itemCode + "] restocked by " + quantity + " item/s.");
            } else {
                System.out.print("\nCannot restock more than the maximum capacity per slot.");
            }
        } else {
            System.out.print("\nInvalid item code.");
        }
    }

    /**
     * Sets the price of the specified item to the new price.
     * Checks if the item code is valid.
     * 
     * @param itemCode  the code of the item
     * @param newPrice  the new price to be set
     */
    public void setItemPrice(int itemCode, int newPrice) {
        if (itemPrices.containsKey(itemCode)) {
            itemPrices.put(itemCode, newPrice);
            System.out.print("\nNew price set for Item [" + itemCode + "] is ₱" + newPrice + ".");
        } else {
            System.out.print("\nInvalid item code.");
        }
    }

    /**
     * Generates a summary of transactions, including item sales and financial information.
     *
     * @return A formatted String containing the transaction summary.
     */
    public String getTransactionSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Code\tName\t\tPrice\tSold\tSales\n");

        for (ItemSlot slot : itemSlots) {
            int itemCode = slot.getItemCode();
            int sold = itemsSold.get(itemCode);
            int itemPrice = slot.getPrice(); // Get the original price from the ItemSlot
            int totalSales = sold * itemPrice; // Calculate sales using the original price
            sb.append("[").append(itemCode).append("]\t").append(slot.getItemName()).append("\t₱").append(itemPrice)
                    .append("\t").append(sold).append("\t₱").append(totalSales).append("\n");
        }

        sb.append("Starting Inventory: ").append(startingInventory).append(" items\n")
                .append("Ending Inventory: ").append(calculateEndingInventory()).append(" items\n")
                .append("Money Stored: ₱").append(moneyCollected);

        return sb.toString();
    }


    /**
     * Calculates the ending inventory by subtracting the total items sold from the starting inventory.
     * 
     * @return the ending inventory
     */
    private int calculateEndingInventory() {
        int totalItemsSold = itemsSold.values().stream().mapToInt(Integer::intValue).sum();
        return startingInventory - totalItemsSold;
    }

    /**
     * Collects the specified amount of money and adds it to the total money collected.
     * 
     * @param amount  the amount of money to collect
     */
    public void collectMoney(int amount) {
        moneyCollected += amount;
    }

    /**
     * Returns the map containing the item prices.
     * 
     * @return the item prices map
     */
    public Map<Integer, Integer> getItemPrices() {
        return itemPrices;
    }

    /**
     * Returns the map containing the item calories.
     * 
     * @return the item calories map
     */
    public Map<Integer, Integer> getItemCalories() {
        return itemCalories;
    }

    /**
     * Returns the map containing the item stock.
     * 
     * @return the item stock map
     */
    public Map<Integer, Integer> getItemStock() {
        return itemStock;
    }

    /**
     * Returns the map containing the items sold.
     * 
     * @return the items sold map
     */
    public Map<Integer, Integer> getItemsSold() {
        return itemsSold;
    }

    /**
     * Returns the list of item slots.
     * 
     * @return the item slots list
     */
    public List<ItemSlot> getItemSlots() {
        return itemSlots;
    }

    /**
     * Returns the list of item slots.
     * 
     * @return the item slots list number 2
     */
    public List<ItemSlot> getItemSlots2() {
        return itemSlots2;
    }

    /**
     * Returns the total money collected.
     * 
     * @return the total money collected
     */
    public int getMoneyCollected() {
        return moneyCollected;
    }

    /**
     * Sets the total money collected to the specified amount.
     * 
     * @param moneyCollected  the new total money collected
     */
    public void setMoneyCollected(int moneyCollected) {
        this.moneyCollected = moneyCollected;
    }
}
