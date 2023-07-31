/***************************************************************************
This is to certify that this project is our own work, based on our personal 
efforts in studying and applying the concepts learned. We have constructed 
the functions and their respective algorithms and corresponding code by 
ourselves. The program was run, tested, and debugged by our own efforts. We 
further certify that We have not copied in part or whole or otherwise 
plagiarized the work of other students and/or persons.

                                Alexis Maureen D. Cosue,    DLSU ID# 12206172
                                Riia Lindsey G. Pangilinan, DLSU ID# 12278912 
***************************************************************************/

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a graphical user interface for a Vending Machine Maker.
 * This class extends JFrame and provides functionality to create and test vending machines.
 */
public class VendingMachineMakerGUI extends JFrame {
    private static VendingMachine vendingMachine;
    
    private static int type = 0;
    public static final int width = 800;
    public static final int height = 500;

    private JPanel cardPanel; 
    private CardLayout cardLayout;

    /**
     * Constructs a new VendingMachineMakerGUI instance.
     */
    public VendingMachineMakerGUI() {
        initUI();
    }

    /**
     * Initializes the user interface components and sets up the layout.
     */
    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel mainMenuPanel = mainMenuPanel();
        JPanel createVendingPanel = createVendingPanel();
        JPanel testVendingPanel = testVendingPanel();
        JPanel testSpecialVendingPanel = testSpecialVendingPanel();

        setTitle("Vending Machine");
        cardPanel.add(mainMenuPanel, "Main Menu");
        cardPanel.add(createVendingPanel, "Create a Vending Machine");
        cardPanel.add(testVendingPanel, "Test a Vending Machine"); 
        cardPanel.add(testSpecialVendingPanel, "Test a Special Vending Machine"); 

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Main Menu");

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates the main menu panel that allows users to choose various options.
     * 
     * @return The JPanel representing the main menu panel.
     */
    private JPanel mainMenuPanel() {
        JPanel mainMenuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = createTitleLabel("Vending Machine");
        JButton createVendingButton = new JButton("Create a Vending Machine");
        JButton testVendingButton = new JButton("Test a Vending Machine");
        JButton exitButton = new JButton("Exit");

        createVendingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Create a Vending Machine");
            }
        });

        testVendingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Test a Vending Machine");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainMenuPanel.setPreferredSize(new Dimension(width, height));
        mainMenuPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);
        mainMenuPanel.add(createVendingButton);
        mainMenuPanel.add(testVendingButton);
        mainMenuPanel.add(exitButton);

        return mainMenuPanel;
    }

    /**
     * Creates the panel to choose the type of vending machine to create.
     * 
     * @return The JPanel representing the panel to choose the type of vending machine.
     */
    private JPanel createVendingPanel() {
        JPanel createVendingPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = createTitleLabel("Type of Vending Machine");
        JButton regularButton = new JButton("Regular Vending Machine");
        JButton specialButton = new JButton("Special Vending Machine");
        JButton returnButton = new JButton("Return to Main Menu");

        regularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vendingMachine = new VendingMachine();
                type = 0;
                JOptionPane.showMessageDialog(
                    null, 
                    "Regular Vending Machine created successfully.",
                    "Vending Machine", JOptionPane.PLAIN_MESSAGE);
            }
        });

        specialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vendingMachine = new VendingMachine();
                type = 1;
                JOptionPane.showMessageDialog(
                    null, 
                    "Special Vending Machine created successfully.",
                    "Vending Machine", JOptionPane.PLAIN_MESSAGE);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main Menu");
            }
        });

        createVendingPanel.setPreferredSize(new Dimension(width, height));
        createVendingPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
        createVendingPanel.add(titleLabel, BorderLayout.NORTH);
        createVendingPanel.add(regularButton);
        createVendingPanel.add(specialButton);
        createVendingPanel.add(returnButton);

        return createVendingPanel;
    }

    /**
     * Creates the panel to test a regular vending machine or a special vending machine.
     * 
     * @return The JPanel representing the panel to test a vending machine.
     */
    private JPanel testVendingPanel() {
        JPanel testVendingPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = createTitleLabel("Test a Vending Machine");
        
        JButton vendingFeaturesButton = new JButton("Test Vending Features");
        JButton maintenanceFeaturesButton = new JButton("Test Maintenance Features");
        JButton returnButton = new JButton("Return to Main Menu");

        vendingFeaturesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine == null) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Please create a Vending Machine first.",
                        "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (type == 0) {
                        MaintenanceFeaturesGUI maintenanceFeaturesGUI = new MaintenanceFeaturesGUI(vendingMachine, VendingMachineMakerGUI.this);
                        VendingFeaturesGUI vendingFeaturesGUI = new VendingFeaturesGUI(vendingMachine, maintenanceFeaturesGUI, VendingMachineMakerGUI.this);
                        dispose();
                        vendingFeaturesGUI.setVisible(true);
                    } else if (type == 1) {
                        cardLayout.show(cardPanel, "Test a Special Vending Machine");
                    }
                }
            }
        });

        maintenanceFeaturesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine == null) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Please create a Vending Machine first.",
                        "Vending Machine", JOptionPane.PLAIN_MESSAGE);
                } else {
                    MaintenanceFeaturesGUI maintenanceFeaturesGUI = new MaintenanceFeaturesGUI(vendingMachine, VendingMachineMakerGUI.this);
                    dispose();
                    maintenanceFeaturesGUI.setVisible(true);
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main Menu");
            }
        });

        testVendingPanel.setPreferredSize(new Dimension(width, height));
        testVendingPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
        testVendingPanel.add(titleLabel, BorderLayout.NORTH);
        testVendingPanel.add(vendingFeaturesButton);
        testVendingPanel.add(maintenanceFeaturesButton);
        testVendingPanel.add(returnButton);

        return testVendingPanel;
    }

    /**
     * Creates the panel to test the features of a special vending machine.
     * 
     * @return The JPanel representing the panel to test a special vending machine.
     */
    private JPanel testSpecialVendingPanel() {
        JPanel testSpecialVendingPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = createTitleLabel("Test a Special Vending Machine");
        
        JButton pickItemsButton = new JButton("Pick Individual Items");
        JButton customizeProductsButton = new JButton("Customize Products");
        JButton returnButton = new JButton("Return to Main Menu");

        pickItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MaintenanceFeaturesGUI maintenanceFeaturesGUI = new MaintenanceFeaturesGUI(vendingMachine, VendingMachineMakerGUI.this);
                VendingFeaturesGUI vendingFeaturesGUI = new VendingFeaturesGUI(vendingMachine, maintenanceFeaturesGUI, VendingMachineMakerGUI.this);
                vendingFeaturesGUI.setVisible(true);
            }
        });

        customizeProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MaintenanceFeaturesGUI maintenanceFeaturesGUI = new MaintenanceFeaturesGUI(vendingMachine, VendingMachineMakerGUI.this);
                SpecialVendingFeaturesGUI specialVendingFeaturesGUI = new SpecialVendingFeaturesGUI(vendingMachine, maintenanceFeaturesGUI, VendingMachineMakerGUI.this);
                dispose();
                specialVendingFeaturesGUI.setVisible(true);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main Menu");
            }
        });

        testSpecialVendingPanel.setPreferredSize(new Dimension(width, height));
        testSpecialVendingPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
        testSpecialVendingPanel.add(titleLabel, BorderLayout.NORTH);
        testSpecialVendingPanel.add(pickItemsButton);
        testSpecialVendingPanel.add(customizeProductsButton);
        testSpecialVendingPanel.add(returnButton);

        return testSpecialVendingPanel;
    }

    /**
     * Creates a JLabel with the specified title text.
     * 
     * @param titleText The text to be displayed as the title.
     * @return The created JLabel with the specified title text.
     */
    public static JLabel createTitleLabel(String titleText) {
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        return titleLabel;
    }

    /**
     * Creates a JLabel with the specified text.
     * 
     * @param text The text to be displayed.
     * @return The created JLabel with the specified text.
     */
    public JLabel createTextLabel(String text) {
        JLabel textLabel = new JLabel("<html>" + text + "</html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        return textLabel;
    }

     /**
     * The entry point of the application.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VendingMachineMakerGUI vendingMachineMakerGUI = new VendingMachineMakerGUI();
                vendingMachineMakerGUI.setVisible(true);
            }
        });
    }
}
