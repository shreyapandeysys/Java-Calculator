import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    Font myFont = new Font("Segoe UI", Font.PLAIN, 28); // Modern font (Segoe UI)
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600); // Size for a better layout
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(48, 25, 52)); // Dark purple theme background

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(new Color(61, 31, 68)); // Darker purple background for the textfield
        textfield.setForeground(Color.WHITE); // White text color for contrast
        textfield.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right

        // Buttons for operations
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        // Add action listeners to function buttons and style them
        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(new Color(85, 44, 99)); // Deep purple for function buttons
            functionButtons[i].setForeground(Color.WHITE); // White text color for contrast
            functionButtons[i].setBorder(BorderFactory.createLineBorder(new Color(115, 59, 120))); // Border color
        }

        // Add action listeners and style number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(115, 59, 120)); // Lighter purple for number buttons
            numberButtons[i].setForeground(Color.WHITE); // White text color for visibility
            numberButtons[i].setBorder(BorderFactory.createLineBorder(new Color(138, 75, 148))); // Border color
        }

        // Position the function buttons
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // Create a panel for the number pad with better layout
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(48, 25, 52)); // Same dark purple background as frame

        // Add number buttons and function buttons to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // Add components to the frame
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);

        // Set frame visibility
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                // Append the number to the current text in the textfield
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            // Append decimal point if needed
            textfield.setText(textfield.getText().concat("."));
        }

        // Handling the operators: we store num1 and the operator, and leave the textfield for the second number
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());  // Save the first number
                operator = ((JButton) e.getSource()).getText().charAt(0); // Save the operator (+, -, *, /)
                textfield.setText(""); // Clear the textfield for the second number
            }
        }

        if (e.getSource() == equButton) {
            if (!textfield.getText().isEmpty()) {
                num2 = Double.parseDouble(textfield.getText()); // Get the second number

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            result = Double.NaN; // Handle division by zero
                        }
                        break;
                }

                textfield.setText(String.valueOf(result)); // Show the result in the textfield
                num1 = result;  // Keep the result in num1 for further calculations
            }
        }

        if (e.getSource() == clrButton) {
            // Clear the textfield when 'Clr' is pressed
            textfield.setText("");
        }

        if (e.getSource() == delButton) {
            // Delete the last character when 'Del' is pressed
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }

        if (e.getSource() == negButton) {
            // Change the sign of the current number
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
    }
}
