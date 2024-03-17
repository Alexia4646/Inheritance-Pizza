import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoPizza {
    private JTextField toppingsField;
    private JRadioButton pickupRadioButton;
    private JRadioButton deliveryRadioButton;
    private JTextField addressField;
    private JComboBox<String> sizeComboBox;
    private JButton submitButton;
    private JTextArea resultTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DemoPizza().createAndShowGUI();
        });
    }

    private void createAndShowGUI() { //method to create and display the GUI
        JFrame frame = new JFrame("Pizza Order");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // I am initiating GUI components
        toppingsField = new JTextField(20);
        toppingsField.setForeground(Color.BLUE);

        pickupRadioButton = new JRadioButton("Dine-In");
        pickupRadioButton.setForeground(Color.BLUE);

        deliveryRadioButton = new JRadioButton("Delivery");
        deliveryRadioButton.setForeground(Color.BLUE);

        addressField = new JTextField(20);
        addressField.setForeground(Color.BLUE);

        sizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        sizeComboBox.setForeground(Color.BLUE);

        submitButton = new JButton("Submit");
        submitButton.setForeground(Color.BLUE);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(pickupRadioButton);
        group.add(deliveryRadioButton);

        // I am adding components to the main panel
        panel.add(createLabelPanel("Enter Pizza Toppings (comma-separated):"));
        panel.add(toppingsField);
        panel.add(createRadioButtonsPanel());
        panel.add(createLabelPanel("Select Pizza Size:"));
        panel.add(sizeComboBox);
        panel.add(createLabelPanel("Enter Delivery Address:"));
        panel.add(addressField);
        panel.add(createButtonPanel());
        panel.add(createResultPanel());

        frame.add(panel);
        frame.setVisible(true);
    }
//I am creating a panel with a label
    private JPanel createLabelPanel(String labelText) {
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel(labelText);
        labelPanel.add(label);
        return labelPanel;
    }
// I am creating a panel with submit button
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmission();
            }
        });
        buttonPanel.add(submitButton);
        return buttonPanel;
    }
// I am creating a panel to display the result
    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel();
        resultPanel.add(new JScrollPane(resultTextArea));
        return resultPanel;
    }
//I am creating a panel with radio buttons
    private JPanel createRadioButtonsPanel() {
        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridLayout(1, 2));

        pickupRadioButton.setSelected(true); // setting dine-in as the default option

        radioButtonsPanel.add(pickupRadioButton);
        radioButtonsPanel.add(deliveryRadioButton);

        return radioButtonsPanel;
    }
    private void handleSubmission() {
        try {
            String toppingsInput = toppingsField.getText().trim();
            if (toppingsInput.isEmpty()) {
                throw new IllegalArgumentException("Please enter at least one topping.");
            }
            String[] toppings = validateToppings(toppingsInput);// validate toppings
            String size = (String) sizeComboBox.getSelectedItem(); //select size
            double totalPrice = calculatePrice(toppings);//calculate total price

            if (deliveryRadioButton.isSelected()) {
                String address = validateAddress(addressField.getText());// validate delivery address
                double deliveryFee = calculateDeliveryFee(totalPrice);// calculate delivery fee
                displayOrderDetails(toppings, size, totalPrice, deliveryFee, address); // I am displaying order details
            } else {
                displayOrderDetails(toppings, size, totalPrice, 0, "Dine-In");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] validateToppings(String toppingsInput) {
        if (toppingsInput.isEmpty()) {
            throw new IllegalArgumentException("Please enter at least one topping.");
        }
        String[] toppings = toppingsInput.split(",");
        if (toppings.length > 10) {
            throw new IllegalArgumentException("Maximum of 10 toppings allowed.");
        }
        return toppings;
    }
// I am calculating the price based on toppings
    private double calculatePrice(String[] toppings) {
        return 14 + 2 * toppings.length;
    }

    private String validateAddress(String address) {
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery address cannot be empty.");
        }
        return address;
    }
    private double calculateDeliveryFee(double totalPrice) {
        double deliveryFee;
        if (totalPrice > 18) {
            deliveryFee = 3;
        } else {
            deliveryFee = 5;
        }
        return deliveryFee;
    }
    private void displayOrderDetails(String[] toppings, String size, double totalPrice, double deliveryFee, String address) {
        StringBuilder orderDetails = new StringBuilder("Pizza Toppings: " + String.join(", ", toppings) +
                "\nSize: " + size +
                "\nPrice: $" + totalPrice);

        if (deliveryRadioButton.isSelected()) {
            orderDetails.append("\nDelivery Fee: $" + deliveryFee + "\nDelivery Address: " + address);
            JOptionPane.showMessageDialog(null, orderDetails.toString(), "Order Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, orderDetails.toString(), "Order Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        resultTextArea.setText(orderDetails.toString());
    }
}
