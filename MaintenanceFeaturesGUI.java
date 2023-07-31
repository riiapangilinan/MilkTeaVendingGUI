import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The MaintenanceFeaturesGUI class represents a GUI for maintaining the vending machine.
 * It allows the user to restock items, set item prices, collect money, and replenish change.
 */
public class MaintenanceFeaturesGUI extends JFrame {
    private VendingMachine vendingMachine;
    private VendingMachineMakerGUI vendingMachineMakerGUI;
    private static Map<String, Integer> availableChange;

    /**
     * Constructs a MaintenanceFeaturesGUI with the specified VendingMachine.
     *
     * @param vendingMachine The VendingMachine object to interact with.
     */
    public MaintenanceFeaturesGUI(VendingMachine vendingMachine, VendingMachineMakerGUI vendingMachineMakerGUI) {
        this.vendingMachine = vendingMachine;
        this.vendingMachineMakerGUI = vendingMachineMakerGUI;
        if (availableChange == null) {
            availableChange = new HashMap<>();
            initializeAvailableChange();
        }
        setTitle("Vending Machine Maintenance");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }
    
    /**
     * Initializes the available change denominations and their quantities in the vending machine.
     * This method sets the initial quantities for different denominations of bills/coins that the vending machine
     * uses to return change to customers.
     */
    private void initializeAvailableChange() {
        availableChange.put("₱1000", 10);
        availableChange.put("₱500", 10);
        availableChange.put("₱200", 10);
        availableChange.put("₱100", 10);
        availableChange.put("₱50", 10);
        availableChange.put("₱20", 10);
        availableChange.put("₱10", 10);
        availableChange.put("₱5", 10);
        availableChange.put("₱1", 10);
    }

    /**
     * Initializes the components of the maintenance GUI.
     */
    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Maintenance Features");

        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);

        JButton displayItemsButton = new JButton("Display Items");
        displayItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showItemsDialog(vendingMachine);
            }
        });

        JButton restockButton = new JButton("Restock Items");
        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemCodeInput = JOptionPane.showInputDialog(null, "Enter the item code to restock:", "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                if (itemCodeInput != null) {
                    try {
                        int itemCode = Integer.parseInt(itemCodeInput);
                        if (itemCode >= 1 && itemCode <= 8) {
                            if (vendingMachine.getItemStock().containsKey(itemCode)) {
                                String quantityInput = JOptionPane.showInputDialog(null, "Enter the quantity to restock:", "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                                if (quantityInput != null) {
                                    try {
                                        int quantity = Integer.parseInt(quantityInput);
                                        int currentStock = vendingMachine.getItemStock().get(itemCode);
                                        int maxStock = 10;
                                        int newStock = currentStock + quantity;

                                        if (newStock > maxStock) {
                                            JOptionPane.showMessageDialog(
                                                null, "Stock cannot exceed " + maxStock + " for item [" + itemCode + "].", 
                                                "Stock Limit Exceeded", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            vendingMachine.restockItem(itemCode, quantity);
                                            textArea.setText("Item [" + itemCode + "] restocked by " + quantity + " item(s).\n");
                                        }
                                    } catch (NumberFormatException ex) {
                                        textArea.setText("Invalid quantity. Please enter a valid number.");
                                    }
                                }
                            } else {
                                textArea.setText("Invalid item code.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null, "Invalid item code. Item code should be between 1 and 8.", 
                                "Invalid Item Code", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        textArea.setText("Invalid item code. Please enter a valid number.");
                    }
                }
            }
        });

        JButton setPriceButton = new JButton("Set Item Price");
        setPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemCodeInput = JOptionPane.showInputDialog(null, "Enter the item code to set the price:", "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                if (itemCodeInput != null) {
                    try {
                        int itemCode = Integer.parseInt(itemCodeInput);
                        if (itemCode >= 1 && itemCode <= 8) {
                            if (vendingMachine.getItemPrices().containsKey(itemCode)) {
                                String newPriceInput = JOptionPane.showInputDialog(null, "Enter the new price for item [" + itemCode + "] in ₱:", "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                                if (newPriceInput != null) {
                                    try {
                                        int newPrice = Integer.parseInt(newPriceInput);
                                        if (newPrice >= 0) {
                                            vendingMachine.setItemPrice(itemCode, newPrice);
                                            JOptionPane.showMessageDialog(
                                                null, "New price set for Item [" + itemCode + "] is ₱" + newPrice + ".", 
                                                "Price Set Successfully", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                null, "Invalid price. Price should be a non-negative number.", 
                                                "Invalid Price", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(
                                            null, "Invalid price. Please enter a valid number.", 
                                            "Invalid Price", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                    null, "Invalid item code.", 
                                    "Invalid Item Code", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null, "Invalid item code. Item code should be between 1 and 8.", 
                                "Invalid Item Code", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            null, "Invalid item code. Please enter a valid number.", 
                            "Invalid Item Code", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton collectMoneyButton = new JButton("Collect Money");
        collectMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int moneyCollected = vendingMachine.getMoneyCollected();
                if (moneyCollected > 0) {
                    vendingMachine.setMoneyCollected(0);
                    JOptionPane.showMessageDialog(
                        null, "Money collected: ₱" + moneyCollected + "\nEmptied the machine.",
                        "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                        null, "No money to collect.",
                        "Maintenance Features", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JButton replenishChangeButton = new JButton("Replenish Change");
        replenishChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel replenishPanel = new JPanel();
                replenishPanel.setLayout(new GridLayout(0, 2));
                String[] billNames = {"₱1000", "₱500", "₱200", "₱100", "₱50", "₱20", "₱10", "₱5", "₱1"};
                JTextField[] billFields = new JTextField[billNames.length];
                for (int i = 0; i < billNames.length; i++) {
                    replenishPanel.add(new JLabel(billNames[i]));
                    billFields[i] = new JTextField(5);
                    replenishPanel.add(billFields[i]);
                }

                int result = JOptionPane.showConfirmDialog(
                    null, replenishPanel, "Replenish Change",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    int totalChange = 0;
                    for (int i = 0; i < billNames.length; i++) {
                        String billName = billNames[i];
                        try {
                            int billCount = Integer.parseInt(billFields[i].getText());
                            int billValue = Integer.parseInt(billName.substring(1));
                            totalChange += billCount * billValue;
                            availableChange.put(billName, availableChange.get(billName) + billCount);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(
                                null, "Invalid input for " + billName + ". Please enter a valid number.",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(
                        null, "Total change replenished: ₱" + totalChange,
                        "Change Replenished", JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        JButton printSummaryButton = new JButton("Print Summary");
        printSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSummaryDialog();
            }
        });

        JButton returnToTestMenuButton = new JButton("Return to Test Menu");
        returnToTestMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vendingMachineMakerGUI.setVisible(true);
            }
        });
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(displayItemsButton);
        mainPanel.add(displayItemsButton);
        mainPanel.add(restockButton);
        mainPanel.add(setPriceButton);
        mainPanel.add(collectMoneyButton);
        mainPanel.add(replenishChangeButton);
        mainPanel.add(printSummaryButton);
        mainPanel.add(returnToTestMenuButton);

        add(mainPanel);
    }
    
    /**
     * Shows a dialog displaying the items available in the vending machine.
     *
     * @param vendingMachine The VendingMachine object to get the items from.
     */
    private void showItemsDialog(VendingMachine vendingMachine) {
        JDialog itemsDialog = new JDialog(this, "Items Available", true);
        itemsDialog.setSize(600, 400);
        itemsDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        itemsDialog.setLayout(new BorderLayout());

        List<ItemSlot> itemSlots = vendingMachine.getItemSlots();
        Object[][] rowData = new Object[itemSlots.size()][5];

        for (int i = 0; i < itemSlots.size(); i++) {
            ItemSlot slot = itemSlots.get(i);
            int itemCode = slot.getItemCode();
            String itemName = slot.getItemName();
            int itemPrice = vendingMachine.getItemPrices().get(itemCode);
            int itemQuantity = vendingMachine.getItemStock().get(itemCode);
            int itemCalories = slot.getCalories(); 

            rowData[i] = new Object[]{itemCode, itemName, "₱" + itemPrice, itemQuantity, itemCalories};
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><div style='text-align: center;'>");
        sb.append("<h2>Items Available</h2>");
        sb.append("<table style='margin: 0 auto;'>");

        sb.append("<tr style='background-color: #e0e0e0;'>");
        sb.append("<th align='center'>Code</th>");
        sb.append("<th align='center'>Name</th>");
        sb.append("<th align='center'>Price</th>");
        sb.append("<th align='center'>Quantity</th>");
        sb.append("<th align='center'>Calories</th>");
        sb.append("</tr>");

        for (Object[] row : rowData) {
            sb.append("<tr>");
            for (int i = 0; i < row.length; i++) {
                String alignment = (i == 0 || i == 3 || i == 4) ? "center" : "left";
                sb.append("<td align='").append(alignment).append("'>").append(row[i]).append("</td>");
            }
            sb.append("</tr>");
        }

        sb.append("</table></div></html>");

        JLabel tableLabel = new JLabel(sb.toString());
        tableLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        itemsDialog.add(tableLabel, BorderLayout.CENTER);

        itemsDialog.pack();
        itemsDialog.setLocationRelativeTo(this); 
        itemsDialog.setVisible(true);
    }

    /**
     * Shows a dialog displaying the summary of transactions and available change.
     */
    private void showSummaryDialog() {
        JPanel summaryPanel = new JPanel(new BorderLayout());

        JTextArea transactionTextArea = new JTextArea();
        transactionTextArea.setEditable(false);
        transactionTextArea.setText("Transaction Summary:\n" + vendingMachine.getTransactionSummary());

        JTable availableChangeTable = new JTable(getAvailableChangeTableModel());
        availableChangeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        availableChangeTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
        availableChangeTable.getColumnModel().getColumn(1).setPreferredWidth(100); 

        // Get the data from the JTable
        DefaultTableModel tableModel = (DefaultTableModel) availableChangeTable.getModel();
        int numRows = tableModel.getRowCount();
        StringBuilder changeData = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            changeData.append(tableModel.getValueAt(row, 0)).append("\t").append(tableModel.getValueAt(row, 1));
            
            // Add a newline character ("\n") if it's not the last row
            if (row < numRows - 1) {
                changeData.append("\n");
            }
        }

        // Append the change data to the transaction text area along with the headers
        transactionTextArea.append("\n\nAvailable Change:\n");
        transactionTextArea.append("Denomination\tRemaining Bills\n");
        transactionTextArea.append(changeData.toString());

        summaryPanel.add(transactionTextArea, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, summaryPanel, "Summary", JOptionPane.PLAIN_MESSAGE);
    }

        
    /**
     * Creates a DefaultTableModel for displaying the available change summary.
     *
     * @return The DefaultTableModel with the available change data.
     */
    private DefaultTableModel getAvailableChangeTableModel() {
        String[] columnNames = {"Denomination", "Remaining Bills"};
        String[] billNames = {"₱1000", "₱500", "₱200", "₱100", "₱50", "₱20", "₱10", "₱5", "₱1"};
        Object[][] data = new Object[billNames.length][2];

        for (int i = 0; i < billNames.length; i++) {
            String billName = billNames[i];
            int remainingBills = availableChange.get(billName);
            data[i] = new Object[]{billName, remainingBills};
        }

        return new DefaultTableModel(data, columnNames);
    }

    /**
     * Denominates the given change amount into available bills and decreases their quantity.
     * @param change the amount of change to denominate
     * @return a Map containing the denominated bills and their quantities
     */
    public Map<String, Integer> denominateBills(int change) {
        String[] billNames = {"₱1000", "₱500", "₱200", "₱100", "₱50", "₱20", "₱10", "₱5", "₱1"};

        Map<String, Integer> denominatedBills = new HashMap<>();

        for (int i = 0; i < billNames.length; i++) {
            String billName = billNames[i];
            int billValue = Integer.parseInt(billName.substring(1));

            Integer availableCount = availableChange.get(billName);
            if (availableCount == null) {
                availableCount = 0;
            }

            int billCount = change / billValue;
            billCount = Math.min(billCount, availableCount);
            change %= billValue;

            if (billCount > 0) {
                int newCount = availableCount - billCount;
                availableChange.put(billName, newCount);
                denominatedBills.put(billName, billCount);
            }
        }

        return denominatedBills;
    }

    /**
     * Returns the availableChange map.
     * @return the availableChange map
     */
    public Map<String, Integer> getAvailableChange() {
        return availableChange;
    }


}
