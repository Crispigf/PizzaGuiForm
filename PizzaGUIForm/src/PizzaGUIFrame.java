import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PizzaGUIFrame extends JFrame {
    JPanel crustTypePanel;
    JPanel crustSizePanel;
    JPanel toppingsPanel;
    JPanel orderPanel;
    JTextArea orderTextArea;
    JButton orderButton;
    JButton clearButton;
    JButton quitButton;

    JRadioButton thinRB;
    JRadioButton regularRB;
    JRadioButton ddRB;

    JComboBox sizeCB;

    JCheckBox top1CB;
    JCheckBox top2CB;
    JCheckBox top3CB;
    JCheckBox top4CB;
    JCheckBox top5CB;
    JCheckBox top6CB;


    // Constructor
    public PizzaGUIFrame() {
        setTitle("Pizza Order Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        initCrustTypePanel();
        initCrustSizePanel();
        initToppingsPanel();
        initOrderPanel();


        add(crustTypePanel, BorderLayout.NORTH);

        add(crustSizePanel, BorderLayout.CENTER);
        add(toppingsPanel, BorderLayout.EAST);
        add(orderPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initCrustTypePanel() {
        crustTypePanel = new JPanel();
        crustTypePanel.setLayout(new GridLayout(1,3));
        crustTypePanel.setBorder(new TitledBorder(new EtchedBorder(), "Crust Type"));

        thinRB = new JRadioButton("Thin");
        regularRB = new JRadioButton("Regular");
        ddRB = new JRadioButton("Deep-dish");

        crustTypePanel.add(thinRB);
        crustTypePanel.add(regularRB);
        crustTypePanel.add(ddRB);

        regularRB.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thinRB);
        group.add(regularRB);
        group.add(ddRB);

    }

    private void initCrustSizePanel() {
        crustSizePanel = new JPanel();
        crustSizePanel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));

        sizeCB = new JComboBox();
        sizeCB.addItem("Small ($8.00)");
        sizeCB.addItem("Medium ($12.00)");
        sizeCB.addItem("Large ($14.00)");
        sizeCB.addItem("Super ($20.00)");
        crustSizePanel.add(sizeCB);

    }


    private void initToppingsPanel() {
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(3, 2));
        toppingsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        top1CB = new JCheckBox("Alien Slime");
        top2CB = new JCheckBox("Monster teeth");
        top3CB = new JCheckBox("Pineapple");
        top4CB = new JCheckBox("Goblin Coins");
        top5CB = new JCheckBox("Zombie Brains");
        top6CB = new JCheckBox("Vampire Fangs");

        toppingsPanel.add(top1CB);
        toppingsPanel.add(top2CB);
        toppingsPanel.add(top3CB);
        toppingsPanel.add(top4CB);
        toppingsPanel.add(top5CB);
        toppingsPanel.add(top6CB);


    }

    private void initOrderPanel() {
        orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderTextArea = new JTextArea(10, 40);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        orderPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new GridLayout(0, 2));


        JPanel orderButtonsPanel = new JPanel();
        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double baseCost = 0.0;
                double toppingCost = 0.0;

                // Calculate base cost based on selected size
                String selectedSize = sizeCB.getSelectedItem().toString();
                String priceWithoutDollar = selectedSize.replaceAll("[^0-9.]", ""); // Remove non-numeric characters
                baseCost = Double.parseDouble(priceWithoutDollar);

                // Calculate topping cost
                int toppingCount = 0;
                if (top1CB.isSelected()) {
                    toppingCount++;
                }
                if (top2CB.isSelected()) {
                    toppingCount++;
                }
                if (top3CB.isSelected()) {
                    toppingCount++;
                }
                if (top4CB.isSelected()) {
                    toppingCount++;
                }
                if (top5CB.isSelected()) {
                    toppingCount++;
                }
                if (top6CB.isSelected()) {
                    toppingCount++;
                }

                toppingCost = toppingCount * 1.0;

                double subTotal = baseCost + toppingCost;
                double tax = subTotal * 0.07; // 7% tax
                double total = subTotal + tax;


                // Display order details in orderTextArea
                orderTextArea.setText("=========================================\n");
                orderTextArea.append("Type of Crust & Size\t\t$" + baseCost +"\n");
                orderTextArea.append("Ingredient\t\t\t$" + toppingCost + "\n");
                orderTextArea.append("-----------------------------------------\n");

                orderTextArea.append(String.format("Sub-total:\t\t\t$%.2f\n", subTotal));
                orderTextArea.append(String.format("Tax:\t\t\t$%.2f\n", tax));
                orderTextArea.append("-----------------------------------------\n");
                orderTextArea.append(String.format("Total:\t\t\t$%.2f\n", total));
                orderTextArea.append("=========================================\n");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderTextArea.setText("");

                ButtonGroup group = new ButtonGroup();
                group.add(thinRB);
                group.add(regularRB);
                group.add(ddRB);
                group.clearSelection();


                sizeCB.setSelectedIndex(0);

                top1CB.setSelected(false);
                top2CB.setSelected(false);
                top3CB.setSelected(false);
                top4CB.setSelected(false);
                top5CB.setSelected(false);
                top6CB.setSelected(false);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        orderButtonsPanel.add(orderButton);
        orderButtonsPanel.add(clearButton);
        orderButtonsPanel.add(quitButton);

        orderPanel.add(summaryPanel, BorderLayout.NORTH);
        orderPanel.add(orderButtonsPanel, BorderLayout.SOUTH);
    }
}
