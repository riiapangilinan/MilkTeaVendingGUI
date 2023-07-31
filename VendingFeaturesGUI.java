import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;

/**
 * The VendingFeaturesGUI class represents a graphical user interface for testing vending machine features.
 * It allows users to insert money, select items, select quantity, view summary, and produce change.
 */
public class VendingFeaturesGUI extends JFrame{
    private VendingMachine vendingMachine;
    private MaintenanceFeaturesGUI maintenanceFeaturesGUI;
    private VendingMachineMakerGUI vendingMachineMakerGUI;

    public static final int width = 800;
    public static final int height = 500;

    private JPanel cardPanel; 
    private CardLayout cardLayout;

    private int denomination; 
    private int itemCode;
    private int quantity;
    private int totalPrice;

    /**
     * Constructs a new VendingFeaturesGUI instance.
     * 
     * @param vendingMachine The VendingMachine instance to be used for testing.
     * @param maintenanceFeaturesGUI The MaintenanceFeaturesGUI instance to interact with maintenance features.
     * @param vendingMachineMakerGUI The VendingMachineMakerGUI instance to control the main menu.
     */
    public VendingFeaturesGUI(VendingMachine vendingMachine, MaintenanceFeaturesGUI maintenanceFeaturesGUI, VendingMachineMakerGUI vendingMachineMakerGUI) {
        this.vendingMachine = vendingMachine;
        this.maintenanceFeaturesGUI = maintenanceFeaturesGUI;
        this.vendingMachineMakerGUI = vendingMachineMakerGUI;
        this.denomination = 0;
        this.itemCode = 0;
        this.quantity = 0;
        this.totalPrice = 0;
        initGUI();
    }

    /**
     * Initializes the user interface components and sets up the layout.
     */
    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel proceedTestingPanel = proceedTestingPanel();
        JPanel insertMoneyPanel = insertMoneyPanel();
        JPanel selectItemPanel = selectItemPanel();
        JPanel selectQuantityPanel = selectQuantityPanel();
        JPanel showSummaryPanel = showSummaryPanel();
        JPanel produceChangePanel = produceChangePanel();

        setTitle("Vending Machine");
        cardPanel.add(proceedTestingPanel, "Instructions");
        cardPanel.add(insertMoneyPanel, "Insert Money");
        cardPanel.add(selectItemPanel, "Select Item");
        cardPanel.add(selectQuantityPanel, "Select Quantity");
        cardPanel.add(showSummaryPanel, "Show Summary");
        cardPanel.add(produceChangePanel, "Produce Change");
        setSize(width, height);

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Instructions");

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * Creates the panel with instructions on testing the vending machine.
     * 
     * @return The JPanel representing the instructions panel.
     */
    private JPanel proceedTestingPanel() {
        JPanel proceedTestingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Test Vending Features");
        JLabel textLabel = vendingMachineMakerGUI.createTextLabel(
                "Step 1: Insert Money<br>" +
                "Step 2: Select Item<br>" +
                "Step 3: Select Quantity<br>" +
                "Step 4: Produce Change");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton proceedTestingButton = new JButton("Proceed to test");
        JButton returnButton = new JButton("Back");

        proceedTestingButton.setPreferredSize(new Dimension(300, 70));
        returnButton.setPreferredSize(new Dimension(300, 70));
        proceedTestingPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        proceedTestingButton.addActionListener(e -> cardLayout.show(cardPanel, "Insert Money"));
        returnButton.addActionListener(e -> vendingMachineMakerGUI.setVisible(true));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        proceedTestingPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 10, 5, 10);
        proceedTestingPanel.add(textLabel, gbc);

        gbc.weighty = 0.0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        buttonPanel.add(proceedTestingButton);
        gbc.gridx = 0;
        proceedTestingPanel.add(buttonPanel, gbc);

        gbc.gridx = 1;
        buttonPanel.add(returnButton);
        proceedTestingPanel.add(buttonPanel, gbc);

        return proceedTestingPanel;
    }

    /**
     * Creates the panel for inserting money into the vending machine.
     * 
     * @return The JPanel representing the insert money panel.
     */
    private JPanel insertMoneyPanel() {
        JPanel insertMoneyPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Insert Money");

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(40, 0, 0, 0));

        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        for (int denomination : denominations) {
            JButton moneyButton = new JButton("\u20B1" + denomination);
            moneyButton.setPreferredSize(new Dimension(100, 50));
            moneyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setDenomination(denomination);
                    cardLayout.show(cardPanel, "Select Item");
                }
            });
            buttonPanel.add(moneyButton);
        }

        insertMoneyPanel.setPreferredSize(new Dimension(width, height));
        insertMoneyPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
        insertMoneyPanel.add(titleLabel, BorderLayout.NORTH);
        insertMoneyPanel.add(buttonPanel, BorderLayout.CENTER);

        return insertMoneyPanel;
    }

    /**
     * Creates the panel for selecting an item from the vending machine.
     * 
     * @return The JPanel representing the select item panel.
     */
    private JPanel selectItemPanel() {
        JPanel selectItemPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Item");
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        for (ItemSlot slot : vendingMachine.getItemSlots()) {
            int itemCode = slot.getItemCode();
            JButton itemButton = new JButton(Integer.toString(itemCode));
            itemButton.setPreferredSize(new Dimension(100, 80));
            itemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setItemCode(slot.getItemCode());
                    cardLayout.show(cardPanel, "Select Quantity");
                }
            });
            buttonPanel.add(itemButton);
        }

        JPanel itemInfoPanelWrapper = new JPanel(new GridBagLayout());
        JPanel itemInfoPanel = new JPanel(new GridLayout(9, 2, 10, 5));

        JLabel itemInfoHeading1 = new JLabel("Code");
        JLabel itemInfoHeading2 = new JLabel("Name");
        JLabel itemInfoHeading3 = new JLabel("Price");
        JLabel itemInfoHeading4 = new JLabel("Stock");
        JLabel itemInfoHeading5 = new JLabel("Calories");

        itemInfoHeading1.setHorizontalAlignment(SwingConstants.CENTER);
        itemInfoHeading2.setHorizontalAlignment(SwingConstants.CENTER);
        itemInfoHeading3.setHorizontalAlignment(SwingConstants.CENTER);
        itemInfoHeading4.setHorizontalAlignment(SwingConstants.CENTER);
        itemInfoHeading5.setHorizontalAlignment(SwingConstants.CENTER);

        itemInfoPanel.add(itemInfoHeading1);
        itemInfoPanel.add(itemInfoHeading2);
        itemInfoPanel.add(itemInfoHeading3);
        itemInfoPanel.add(itemInfoHeading4);
        itemInfoPanel.add(itemInfoHeading5);

        for (ItemSlot slot : vendingMachine.getItemSlots()) {
            int itemCode = slot.getItemCode();
            String itemName = slot.getItemName();
            int itemPrice = vendingMachine.getItemPrices().get(itemCode);
            int itemStock = vendingMachine.getItemStock().get(itemCode);
            int itemCalories = vendingMachine.getItemCalories().get(itemCode);

            JLabel itemCodeLabel = new JLabel("[" + itemCode + "]");
            itemCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel itemNameLabel = new JLabel(itemName);
            itemNameLabel.setHorizontalAlignment(SwingConstants.LEFT);

            JLabel itemPriceLabel = new JLabel("₱" + itemPrice);
            itemPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel itemStockLabel = new JLabel(Integer.toString(itemStock));
            itemStockLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel itemCaloriesLabel = new JLabel(Integer.toString(itemCalories));
            itemCaloriesLabel.setHorizontalAlignment(SwingConstants.CENTER);

            itemInfoPanel.add(itemCodeLabel);
            itemInfoPanel.add(itemNameLabel);
            itemInfoPanel.add(itemPriceLabel);
            itemInfoPanel.add(itemStockLabel);
            itemInfoPanel.add(itemCaloriesLabel);
        }

        itemInfoPanelWrapper.add(itemInfoPanel);
        selectItemPanel.setPreferredSize(new Dimension(width, height));
        selectItemPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
        selectItemPanel.add(titleLabel, BorderLayout.NORTH);
        selectItemPanel.add(itemInfoPanelWrapper, BorderLayout.CENTER);
        selectItemPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectItemPanel;
    }

    /**
     * Creates the panel for selecting the quantity of the selected item.
     * 
     * @return The JPanel representing the select quantity panel.
     */
    private JPanel selectQuantityPanel() {
        JPanel selectQuantityPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Quantity");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 10, 0));

        for (int i = 1; i <= 10; i++) {
            int quantity = i;
            JButton quantityButton = new JButton(Integer.toString(quantity));
            quantityButton.setPreferredSize(new Dimension(100, 50));
            quantityButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedItemStock = vendingMachine.getItemStock().get(getItemCode());
                    if (quantity <= selectedItemStock) {
                        setQuantity(quantity);
                        cardLayout.show(cardPanel, "Show Summary");
                    } else {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Insufficient stock. Please select a lower quantity.",
                            "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
            buttonPanel.add(quantityButton);
        }

        selectQuantityPanel.setPreferredSize(new Dimension(width, height));
        selectQuantityPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
        selectQuantityPanel.add(titleLabel, BorderLayout.NORTH);
        selectQuantityPanel.add(buttonPanel, BorderLayout.CENTER);

        return selectQuantityPanel;
    }

    /**
     * Creates the panel to show the summary of the selected item and quantity.
     * 
     * @return The JPanel representing the show summary panel.
     */
    private JPanel showSummaryPanel() {
        JPanel showSummaryPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Show Summary");
        JLabel summaryLabel = vendingMachineMakerGUI.createTextLabel("Press 'View Summary' first.");

        JButton summaryButton = new JButton("View Summary");
        JButton proceedButton = new JButton("Proceed Transaction");
        JButton repeatButton = new JButton("Repeat Transaction");

        summaryButton.setPreferredSize(new Dimension(200, 70));
        proceedButton.setPreferredSize(new Dimension(200, 70));
        repeatButton.setPreferredSize(new Dimension(200, 70));
        showSummaryPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String summaryText = "<html>Item: " + vendingMachine.getItemSlots().get(getItemCode() - 1).getItemName() +
                        "<br>Quantity: " + getQuantity() +
                        "<br>Total Price: ₱" + (getQuantity() * vendingMachine.getItemPrices().get(getItemCode())) +
                        "<br>Total Calories: " + (getQuantity() * vendingMachine.getItemCalories().get(getItemCode())) + "</html>";

                summaryLabel.setText(summaryText);
                setTotalPrice(getQuantity() * vendingMachine.getItemPrices().get(getItemCode()));
            }
        });

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Produce Change");
                summaryLabel.setText("Press 'View Summary' first.");
            }
        });

        repeatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetInfo();
                cardLayout.show(cardPanel, "Insert Money");
                summaryLabel.setText("Press 'View Summary' first.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(summaryButton);
        buttonPanel.add(proceedButton);
        buttonPanel.add(repeatButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        showSummaryPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 0, 10);
        showSummaryPanel.add(summaryLabel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        showSummaryPanel.add(buttonPanel, gbc);

        return showSummaryPanel;
    }

    /**
     * Creates the panel to produce change after completing the transaction.
     * 
     * @return The JPanel representing the produce change panel.
     */
    private JPanel produceChangePanel() {
        JPanel produceChangePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Produce Change");
        JLabel changeLabel = vendingMachineMakerGUI.createTextLabel("Press 'Produce Change' first.");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton produceButton = new JButton("Produce Change");
        JButton exitButton = new JButton("Exit");

        produceButton.setPreferredSize(new Dimension(300, 70));
        exitButton.setPreferredSize(new Dimension(300, 70));
        produceChangePanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        produceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int change = getDenomination() - getTotalPrice();
                if (change >= 0) {
                    vendingMachine.getItemStock().put(getItemCode(), vendingMachine.getItemStock().get(getItemCode()) - getQuantity());
                    vendingMachine.getItemsSold().put(getItemCode(), vendingMachine.getItemsSold().get(getItemCode()) + getQuantity());

                    vendingMachine.collectMoney(getTotalPrice());

                    Map<String, Integer> denominatedBills = maintenanceFeaturesGUI.denominateBills(change);
                    StringBuilder changeText = new StringBuilder("<html>Transaction Successful.<br>Change:<br>");
                    Comparator<String> denominationComparator = new Comparator<String>() {
                        public int compare(String denomination1, String denomination2) {
                            int value1 = Integer.parseInt(denomination1.substring(1));
                            int value2 = Integer.parseInt(denomination2.substring(1));
                            return Integer.compare(value2, value1);
                        }
                    };

                    if (denominatedBills.isEmpty()) {
                        changeText.append("Transaction Unsuccessful.\nNo denomination bills left to give the change.");
                    } else {
                        denominatedBills.entrySet().stream()
                                .sorted(Map.Entry.<String, Integer>comparingByKey(denominationComparator))
                                .forEach(entry -> {
                                    String billName = entry.getKey();
                                    int billCount = entry.getValue();
                                    changeText.append(billCount).append(" piece of ").append(billName).append("<br>");
                                });
                    }

                    if (change == 0) {
                        changeText.append("Transaction Successful.\nExact Change, no additional bills to give.");
                    }

                    changeText.append("</html>");
                    changeLabel.setText(changeText.toString());
                } else {
                changeLabel.setText("<html>Transaction Unsuccessful.<br>Insufficient denomination.<br>Please insert more money.</html>");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetInfo();
                dispose();
                vendingMachineMakerGUI.setVisible(true);
                changeLabel.setText("Press 'Produce Change' first.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        produceChangePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 10, 0, 10);
        produceChangePanel.add(changeLabel, gbc);

        gbc.weighty = 0.0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        buttonPanel.add(produceButton);
        gbc.gridx = 0;
        produceChangePanel.add(buttonPanel, gbc);

        gbc.gridx = 1;
        buttonPanel.add(exitButton);
        produceChangePanel.add(buttonPanel, gbc);
        
        return produceChangePanel;
    }

    /**
     * Resets the information related to the current transaction.
     */
    public void resetInfo(){
        setDenomination(0);
        setItemCode(0);
        setQuantity(0);
        setTotalPrice(0);
    }

    /**
     * Retrieves the current denomination of money inserted by the user.
     * 
     * @return The denomination of money inserted by the user.
     */
    public int getDenomination() {
        return denomination;
    }

    /**
     * Retrieves the item code of the selected item from the vending machine.
     * 
     * @return The item code of the selected item.
     */
    public int getItemCode() {
        return itemCode;
    }

    /**
     * Retrieves the selected quantity of the item to be purchased.
     * 
     * @return The selected quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the total price of the items to be purchased.
     * 
     * @return The total price of the items.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the denomination of money inserted by the user.
     * 
     * @param denomination The denomination of money to set.
     */
    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }
    
    /**
     * Sets the item code of the selected item from the vending machine.
     * 
     * @param itemCode The item code of the selected item to set.
     */
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Sets the selected quantity of the item to be purchased.
     * 
     * @param quantity The selected quantity of the item to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Sets the total price of the items to be purchased.
     * 
     * @param totalPrice The total price of the items (in centavos) to set.
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}