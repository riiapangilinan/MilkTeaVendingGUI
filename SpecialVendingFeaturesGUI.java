import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map; 

/**
 * This class represents a graphical user interface (GUI) for a special vending machine with additional features.
 * It allows users to interact with the vending machine, make selections, and view the summary of their order.
 */
public class SpecialVendingFeaturesGUI extends JFrame {
    private VendingMachine vendingMachine;
    private MaintenanceFeaturesGUI maintenanceFeaturesGUI;
    private VendingMachineMakerGUI vendingMachineMakerGUI;

    public static final int width = 800;
    public static final int height = 500;

    private JPanel cardPanel; 
    private CardLayout cardLayout;

    private int denomination; 
    private int tea;
    private int fruit;
    private int topping;
    private int milk;
    private int cup;
    private int sugar;
    private int totalPrice;

    /**
     * Creates a new SpecialVendingFeaturesGUI instance.
     * 
     * @param vendingMachine The vending machine instance to be used for processing orders.
     * @param maintenanceFeaturesGUI The maintenance GUI associated with the vending machine.
     * @param vendingMachineMakerGUI The GUI used to create the vending machine.
     */
    public SpecialVendingFeaturesGUI(VendingMachine vendingMachine, MaintenanceFeaturesGUI maintenanceFeaturesGUI, VendingMachineMakerGUI vendingMachineMakerGUI) {
        this.vendingMachine = vendingMachine;
        this.maintenanceFeaturesGUI = maintenanceFeaturesGUI;
        this.vendingMachineMakerGUI = vendingMachineMakerGUI;
        this.denomination = 0;
        this.tea = 0;
        this.fruit = 0;
        this.topping = 0;
        this.milk = 0;
        this.cup = 0;
        this.sugar = 0;
        this.totalPrice = 0;
        initUI();
    }

    /**
     * Initializes the user interface for the special vending machine.
     * Sets up the main layout, panels, and buttons.
     */
    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel proceedTestingPanel = proceedTestingPanel();
        JPanel insertMoneyPanel = insertMoneyPanel();
        JPanel selectTeaPanel = selectTeaPanel();
        JPanel selectFruitPanel = selectFruitPanel();
        JPanel selectToppingsPanel = selectToppingsPanel();
        JPanel selectMilkPanel = selectMilkPanel();
        JPanel selectCupPanel = selectCupPanel();
        JPanel selectSugarPanel = selectSugarPanel();
        JPanel showSummaryPanel = showSummaryPanel();
        JPanel prepareProductPanel = prepareProductPanel();
        JPanel produceChangePanel = produceChangePanel();

        setTitle("Special Vending Machine");
        cardPanel.add(proceedTestingPanel, "Instructions");
        cardPanel.add(insertMoneyPanel, "Insert Money");
        cardPanel.add(selectTeaPanel, "Select Base Tea");
        cardPanel.add(selectFruitPanel, "Select Fruit");
        cardPanel.add(selectToppingsPanel, "Select Topping");
        cardPanel.add(selectMilkPanel, "Select Milk");
        cardPanel.add(selectCupPanel, "Select Cup Size");
        cardPanel.add(selectSugarPanel, "Select Sugar Level");
        cardPanel.add(showSummaryPanel, "Show Summary");
        cardPanel.add(prepareProductPanel, "Preparing Product");
        cardPanel.add(produceChangePanel, "Produce Change");

        setSize(width, height);

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Instructions");

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * Creates the panel for the initial instructions screen.
     * 
     * @return The JPanel containing the initial instructions screen.
     */
    private JPanel proceedTestingPanel() {
        JPanel proceedTestingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Test Vending Features");
        JLabel textLabel = vendingMachineMakerGUI.createTextLabel(
                "Step 1: Insert Money<br>" + 
                "Step 2: Select Base Tea<br>" + 
                "Step 3: Select Fruit<br>" + 
                "Step 4: Select Toppings<br>" + 
                "Step 5: Select Milk<br>" + 
                "Step 6: Select Cup Size<br>" + 
                "Step 7: Select Tea<br>" + 
                "Step 8: Select Sugar Level<br>" + 
                "Step 9: Show Summary<br>" + 
                "Step 10: Prepare Product<br>" + 
                "Step 11: Produce Change");
        
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton proceedTestingButton = new JButton("Proceed to test");
        JButton returnButton = new JButton("Back");

        proceedTestingButton.setPreferredSize(new Dimension(300, 70));
        returnButton.setPreferredSize(new Dimension(300, 70));

        proceedTestingButton.addActionListener(e -> cardLayout.show(cardPanel, "Insert Money"));
        returnButton.addActionListener(e -> vendingMachineMakerGUI.setVisible(true));

        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        proceedTestingPanel.setPreferredSize(new Dimension(width, height));
        proceedTestingPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
        
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
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        proceedTestingPanel.add(proceedTestingButton, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 10);
        proceedTestingPanel.add(returnButton, gbc);

        return proceedTestingPanel;
    }

    /**
     * Creates the panel for the "Insert Money" screen.
     *
     * @return The JPanel containing the "Insert Money" screen.
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
                    cardLayout.show(cardPanel, "Select Base Tea");
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
     * Creates the panel for the "Select Base Tea" screen.
     *
     * @return The JPanel containing the "Select Base Tea" screen.
     */
    private JPanel selectTeaPanel() {
        JPanel selectTeaPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Base Tea");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton select1Button = new JButton("1");
        JButton select2Button = new JButton("2");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedTea = 1;
                if (isItemAvailable(selectedTea)) {
                    setTea(selectedTea);
                    cardLayout.show(cardPanel, "Select Fruit");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected tea has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedTea = 2;
                if (isItemAvailable(selectedTea)) {
                    setTea(selectedTea);
                    cardLayout.show(cardPanel, "Select Fruit");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected tea has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        selectTeaPanel.setPreferredSize(new Dimension(width, height));
        selectTeaPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);

        selectTeaPanel.add(titleLabel, BorderLayout.NORTH);
        selectTeaPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectTeaPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectTeaPanel;
    }

    /**
     * Creates the panel for the "Select Fruit" screen.
     *
     * @return The JPanel containing the "Select Fruit" screen.
     */
    private JPanel selectFruitPanel() {
        JPanel selectFruitPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Fruit");

        JButton select1Button = new JButton("3");
        JButton select2Button = new JButton("4");
        JButton noneButton = new JButton("None");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));
        noneButton.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedFruit = 3;
                if (isItemAvailable(selectedFruit)) {
                    setFruit(selectedFruit);
                    cardLayout.show(cardPanel, "Select Topping");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected fruit has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedFruit = 4;
                if (isItemAvailable(selectedFruit)) {
                    setFruit(selectedFruit);
                    cardLayout.show(cardPanel, "Select Topping");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected fruit has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        noneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFruit(0);
                cardLayout.show(cardPanel, "Select Topping");
            }
        });


        selectFruitPanel.setPreferredSize(new Dimension(width, height));
        selectFruitPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);
        buttonPanel.add(noneButton);

        selectFruitPanel.add(titleLabel, BorderLayout.NORTH);
        selectFruitPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectFruitPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectFruitPanel;
    }

    /**
     * Creates the panel for the "Select Topping" screen.
     *
     * @return The JPanel containing the "Select Topping" screen.
     */
    private JPanel selectToppingsPanel() {
        JPanel selectToppingsPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Topping");

        JButton select1Button = new JButton("5");
        JButton select2Button = new JButton("6");
        JButton noneButton = new JButton("None");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));
        noneButton.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedTopping = 5;
                if (isItemAvailable(selectedTopping)) {
                    setTopping(selectedTopping);
                    cardLayout.show(cardPanel, "Select Milk");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected topping has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedTopping = 6;
                if (isItemAvailable(selectedTopping)) {
                    setTopping(selectedTopping);
                    cardLayout.show(cardPanel, "Select Milk");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected topping has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        noneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setTopping(0);
                cardLayout.show(cardPanel, "Select Milk");
            }
        });


        selectToppingsPanel.setPreferredSize(new Dimension(width, height));
        selectToppingsPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);
        buttonPanel.add(noneButton);

        selectToppingsPanel.add(titleLabel, BorderLayout.NORTH);
        selectToppingsPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectToppingsPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectToppingsPanel;
    }

    /**
     * Creates the panel for the "Select Milk" screen.
     *
     * @return The JPanel containing the "Select Milk" screen.
     */
    private JPanel selectMilkPanel() {
        JPanel selectMilkPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Milk");

        JButton select1Button = new JButton("7");
        JButton select2Button = new JButton("8");
        JButton noneButton = new JButton("None");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));
        noneButton.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedMilk = 7;
                if (isItemAvailable(selectedMilk)) {
                    setMilk(selectedMilk);
                    cardLayout.show(cardPanel, "Select Cup Size");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected milk type has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedMilk = 7;
                if (isItemAvailable(selectedMilk)) {
                    setMilk(selectedMilk);
                    cardLayout.show(cardPanel, "Select Cup Size");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected milk type has insufficient stock.", "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        noneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setMilk(0);
                cardLayout.show(cardPanel, "Select Cup Size");
            }
        });

        selectMilkPanel.setPreferredSize(new Dimension(width, height));
        selectMilkPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);
        buttonPanel.add(noneButton);

        selectMilkPanel.add(titleLabel, BorderLayout.NORTH);
        selectMilkPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectMilkPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectMilkPanel;
    }

    /**
     * Creates the panel for the "Select Cup Size" screen.
     *
     * @return The JPanel containing the "Select Cup Size" screen.
     */
    private JPanel selectCupPanel() {
        JPanel selectCupPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Cup Size");

        JButton select1Button = new JButton("9");
        JButton select2Button = new JButton("10");
        JButton select3Button = new JButton("11");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));
        select3Button.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCup(9);
                cardLayout.show(cardPanel, "Select Sugar Level");
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCup(10);
                cardLayout.show(cardPanel, "Select Sugar Level");
            }
        });

        select3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCup(11);
                cardLayout.show(cardPanel, "Select Sugar Level");
            }
        });

        selectCupPanel.setPreferredSize(new Dimension(width, height));
        selectCupPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);
        buttonPanel.add(select3Button);

        selectCupPanel.add(titleLabel, BorderLayout.NORTH);
        selectCupPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectCupPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectCupPanel;
    }

    /**
     * Creates the panel for the "Select Sugar Level" screen.
     *
     * @return The JPanel containing the "Select Sugar Level" screen.
     */
    private JPanel selectSugarPanel() {
        JPanel selectSugarPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Select Sugar");

        JButton select1Button = new JButton("12");
        JButton select2Button = new JButton("13");
        JButton select3Button = new JButton("14");
        JButton select4Button = new JButton("15");

        select1Button.setPreferredSize(new Dimension(300, 70));
        select2Button.setPreferredSize(new Dimension(300, 70));
        select3Button.setPreferredSize(new Dimension(300, 70));
        select4Button.setPreferredSize(new Dimension(300, 70));

        select1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSugar(12);
                cardLayout.show(cardPanel, "Show Summary");
            }
        });

        select2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSugar(13);
                cardLayout.show(cardPanel, "Show Summary");
            }
        });

        select3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSugar(14);
                cardLayout.show(cardPanel, "Show Summary");
            }
        });

        select4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSugar(15);
                cardLayout.show(cardPanel, "Show Summary");
            }
        });

        selectSugarPanel.setPreferredSize(new Dimension(width, height));
        selectSugarPanel.setBorder(new EmptyBorder(30, 50, 50, 50));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(select1Button);
        buttonPanel.add(select2Button);
        buttonPanel.add(select3Button);
        buttonPanel.add(select4Button);
        
        selectSugarPanel.add(titleLabel, BorderLayout.NORTH);
        selectSugarPanel.add(createItemInfoPanel(), BorderLayout.CENTER);
        selectSugarPanel.add(buttonPanel, BorderLayout.SOUTH);

        return selectSugarPanel;
    }

    /**
     * Creates and returns a JPanel that displays the summary of the customized milk tea.
     *
     * @return JPanel containing the summary of the customized milk tea.
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

                String teaName = vendingMachine.getItemSlots().get(getTea() - 1).getItemName();
                String fruitName = (getFruit() == 0) ? "None" : vendingMachine.getItemSlots().get(getFruit() - 1).getItemName();
                String toppingName = (getTopping() == 0) ? "None" : vendingMachine.getItemSlots().get(getTopping() - 1).getItemName();
                String milkName = (getMilk() == 0) ? "None" : vendingMachine.getItemSlots().get(getMilk() - 1).getItemName();
                String cupName = vendingMachine.getItemSlots2().get(getCup() - 9).getItemName();
                String sugarName = vendingMachine.getItemSlots2().get(getSugar() - 9).getItemName();

                int teaPrice = vendingMachine.getItemSlots().get(getTea() - 1).getPrice();
                int fruitPrice = (getFruit() == 0) ? 0 : vendingMachine.getItemSlots().get(getFruit() - 1).getPrice();
                int toppingPrice = (getTopping() == 0) ? 0 : vendingMachine.getItemSlots().get(getTopping() - 1).getPrice();
                int milkPrice = (getMilk() == 0) ? 0 : vendingMachine.getItemSlots().get(getMilk() - 1).getPrice();
                int cupPrice = vendingMachine.getItemSlots2().get(getCup() - 9).getPrice();

                int teaCalories = vendingMachine.getItemSlots().get(getTea() - 1).getCalories();
                int fruitCalories = (getFruit() == 0) ? 0 : vendingMachine.getItemSlots().get(getFruit() - 1).getCalories();
                int toppingCalories = (getTopping() == 0) ? 0 : vendingMachine.getItemSlots().get(getTopping() - 1).getCalories();
                int milkCalories = (getMilk() == 0) ? 0 : vendingMachine.getItemSlots().get(getMilk() - 1).getCalories();
                int sugarCalories = vendingMachine.getItemSlots2().get(getSugar() - 9).getCalories();

                int totalCustomizedPrice = teaPrice + fruitPrice + toppingPrice + milkPrice + cupPrice;
                int totalCustomizedCalories = teaCalories + fruitCalories + toppingCalories + milkCalories + sugarCalories;

                String summaryText = "<html>Tea: " + teaName +
                        "<br>Fruit: " + fruitName +
                        "<br>Topping: " + toppingName +
                        "<br>Milk: " + milkName +
                        "<br>Cup Size: " + cupName +
                        "<br>Sugar Level: " + sugarName +
                        "<br>Total Price: ₱" + totalCustomizedPrice +
                        "<br>Total Calories: " + totalCustomizedCalories + "</html>";

                summaryLabel.setText(summaryText);
                setTotalPrice(totalCustomizedPrice);
            }
        });

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Preparing Product");
                summaryLabel.setText("Press 'View Summary' first.");
            }
        });

        repeatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetInfo();
                setTotalPrice(0);
                summaryLabel.setText("Press 'View Summary' first.");
                cardLayout.show(cardPanel, "Insert Money");
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
     * Creates and returns a JPanel for preparing the product, showing the steps involved.
     *
     * @return JPanel for preparing the product with the preparation steps.
     */
    private JPanel prepareProductPanel() {
        JPanel prepareProductPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel titleLabel = VendingMachineMakerGUI.createTitleLabel("Preparing Product");
        JLabel prepareLabel = vendingMachineMakerGUI.createTextLabel("Press 'Show Preparation' first.");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton showPreparationButton = new JButton("Show Preparation");
        JButton produceChangeButton = new JButton("Produce Change");

        showPreparationButton.setPreferredSize(new Dimension(300, 70));
        produceChangeButton.setPreferredSize(new Dimension(300, 70));
        prepareProductPanel.setBorder(new EmptyBorder(30, 50, 50, 50));
    
        showPreparationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String preparationSteps = "";

                if (getTea() == 1) {
                    preparationSteps += "<html>Preparing your Milk Tea...<br>Brewing Green Tea...";
                } else if (getTea() == 2) {
                    preparationSteps += "<html>Preparing your Milk Tea...<br>Brewing Black Tea...";
                }

                if (getFruit() == 3) {
                    preparationSteps += "<br>Cutting and adding Peaches...";
                } else if (getFruit() == 4) {
                    preparationSteps += "<br>Cutting and adding Watermelons...";
                }

                if (getTopping() == 5) {
                    preparationSteps += "<br>Adding Tapioca Pearls...";
                } else if (getTopping() == 6) {
                    preparationSteps += "<br>Adding Cream Cheese...";
                }

                if (getMilk() == 7) {
                    preparationSteps += "<br>Adding Cow's Milk...";
                } else if (getMilk() == 8) {
                    preparationSteps += "<br>Adding Oat Milk...";
                }

                if (getCup() == 9) {
                    preparationSteps += "<br>Placing in Small Cup...";
                } else if (getCup() == 10) {
                    preparationSteps += "<<br>Placing in Medium Cup...";
                } else if (getCup() == 11) {
                    preparationSteps += "<br>Placing in Large Cup...";
                }

                if (getSugar() == 12) {
                    preparationSteps += "<br>Milk Tea is ready!</html>";
                } else if (getSugar() == 13) {
                    preparationSteps += "<br>Adding 30% Sugar...<br>Milk Tea is ready!</html>";
                } else if (getSugar() == 14) {
                    preparationSteps += "<br>Adding 70% Sugar...<br>Milk Tea is ready!</html>";
                } else if (getSugar() == 15) {
                    preparationSteps += "<br>Adding 100% Sugar...<br>Milk Tea is ready!</html>";
                }

                prepareLabel.setText(preparationSteps);
            }
        });

        produceChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Produce Change");
                prepareLabel.setText("Press 'Show Preparation' first.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        prepareProductPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 10, 0, 10);
        prepareProductPanel.add(prepareLabel, gbc);

        gbc.weighty = 0.0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        buttonPanel.add(showPreparationButton);
        prepareProductPanel.add(buttonPanel, gbc);

        gbc.gridx = 1; 
        buttonPanel.add(produceChangeButton);
        prepareProductPanel.add(buttonPanel, gbc);

        return prepareProductPanel;
    }

    /**
     * Creates and returns a JPanel for producing change after purchase.
     *
     * @return JPanel for producing change with the denominated bills.
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

                    vendingMachine.getItemStock().put(getTea(), vendingMachine.getItemStock().get(getTea()) - 1);
                    vendingMachine.getItemsSold().put(getTea(), vendingMachine.getItemsSold().get(getTea()) + 1);
                 
                    if (getFruit() != 0) {
                        vendingMachine.getItemStock().put(getFruit(), vendingMachine.getItemStock().get(getFruit()) - 1);
                        vendingMachine.getItemsSold().put(getFruit(), vendingMachine.getItemsSold().get(getFruit()) + 1);
                    }

                    if (getTopping() != 0) {
                        vendingMachine.getItemStock().put(getTopping(), vendingMachine.getItemStock().get(getTopping()) - 1);
                        vendingMachine.getItemsSold().put(getTopping(), vendingMachine.getItemsSold().get(getTopping()) + 1);
                    }

                    if (getMilk() != 0) {
                        vendingMachine.getItemStock().put(getMilk(), vendingMachine.getItemStock().get(getMilk()) - 1);
                        vendingMachine.getItemsSold().put(getMilk(), vendingMachine.getItemsSold().get(getMilk()) + 1);
                    }

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
                        changeText.append("Transaction Successful.\nExact change, no additional bills to give.");
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
        gbc.gridy = 2;
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
     * Creates and returns a JPanel with item information, such as code, name, price, quantity, and calories.
     *
     * @return JPanel displaying the item information.
     */
    private JPanel createItemInfoPanel() {
        JPanel itemInfoPanelWrapper = new JPanel(new GridBagLayout());
        JPanel itemInfoPanel = new JPanel(new GridLayout(16, 2, 10, 5));
        itemInfoPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

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

        for (ItemSlot slot2 : vendingMachine.getItemSlots2()) {
            int itemCode2 = slot2.getItemCode();
            String itemName2 = slot2.getItemName();
            int itemPrice2 = vendingMachine.getItemPrices().get(itemCode2);
            int itemCalories2 = vendingMachine.getItemCalories().get(itemCode2);

            JLabel itemCodeLabel2 = new JLabel("[" + itemCode2 + "]");
            itemCodeLabel2.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel itemNameLabel2 = new JLabel(itemName2);
            itemNameLabel2.setHorizontalAlignment(SwingConstants.LEFT);

            JLabel itemPriceLabel2 = new JLabel("₱" + itemPrice2);
            itemPriceLabel2.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel blankLabel = new JLabel("");
            blankLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel itemCaloriesLabel2 = new JLabel(Integer.toString(itemCalories2));
            itemCaloriesLabel2.setHorizontalAlignment(SwingConstants.CENTER);

            itemInfoPanel.add(itemCodeLabel2);
            itemInfoPanel.add(itemNameLabel2);
            itemInfoPanel.add(itemPriceLabel2);
            itemInfoPanel.add(blankLabel);
            itemInfoPanel.add(itemCaloriesLabel2);
        }

        itemInfoPanelWrapper.add(itemInfoPanel);

        return itemInfoPanel;
    }

    /**
     * Checks if an item with the given item code is available in the vending machine.
     *
     * @param itemCode The code of the item to check availability for.
     * @return True if the item is available; otherwise, false.
     */
    private boolean isItemAvailable(int itemCode) {
        if (vendingMachine.getItemStock().containsKey(itemCode)) {
            int stock = vendingMachine.getItemStock().get(itemCode);
            return stock > 0;
        }
        return false;
    }
    
    /**
     * Resets the information related to the current transaction in the vending machine GUI.
     */
    public void resetInfo(){
        setDenomination(0);
        setTea(0);
        setFruit(0);
        setTopping(0);
        setMilk(0);
        setCup(0);
        setSugar(0);
        setTotalPrice(0);
    }

    /**
     * Gets the denomination amount entered by the user for the current transaction.
     *
     * @return The denomination amount entered by the user.
     */
    public int getDenomination() {
        return denomination;
    }

    /**
     * Gets the selected tea item code for the current transaction.
     *
     * @return The selected tea item code.
     */
    public int getTea() {
        return tea;
    }

    /**
     * Gets the selected fruit item code for the current transaction.
     *
     * @return The selected fruit item code.
     */
    public int getFruit() {
        return fruit;
    }

    /**
     * Gets the selected topping item code for the current transaction.
     *
     * @return The selected topping item code.
     */
    public int getTopping() {
        return topping;
    }

    /**
     * Gets the selected milk item code for the current transaction.
     *
     * @return The selected milk item code.
     */
    public int getMilk() {
        return milk;
    }

    /**
     * Gets the selected cup size item code for the current transaction.
     *
     * @return The selected cup size item code.
     */
    public int getCup() {
        return cup;
    }

    /**
     * Gets the selected sugar level item code for the current transaction.
     *
     * @return The selected sugar level item code.
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * Gets the total price of the customized milk tea for the current transaction.
     *
     * @return The total price of the customized milk tea.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the denomination amount entered by the user for the current transaction.
     *
     * @param denomination The denomination amount to set.
     */
    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    /**
     * Sets the selected tea item code for the current transaction.
     *
     * @param tea The selected tea item code to set.
     */
    public void setTea(int tea) {
        this.tea = tea;
    }

    /**
     * Sets the selected fruit item code for the current transaction.
     *
     * @param fruit The selected fruit item code to set.
     */
    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    /**
     * Sets the selected topping item code for the current transaction.
     *
     * @param topping The selected topping item code to set.
     */
    public void setTopping(int topping) {
        this.topping = topping;
    }

    /**
     * Sets the selected milk item code for the current transaction.
     *
     * @param milk The selected milk item code to set.
     */
    public void setMilk(int milk) {
        this.milk = milk;
    }

    /**
     * Sets the selected cup size item code for the current transaction.
     *
     * @param cup The selected cup size item code to set.
     */
    public void setCup(int cup) {
        this.cup = cup;
    }

    /**
     * Sets the selected sugar level item code for the current transaction.
     *
     * @param sugar The selected sugar level item code to set.
     */
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    /**
     * Sets the total price of the customized milk tea for the current transaction.
     *
     * @param totalPrice The total price of the customized milk tea to set.
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
