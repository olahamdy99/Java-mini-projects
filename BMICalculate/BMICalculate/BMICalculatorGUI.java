import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMICalculatorGUI extends JFrame {
    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultLabel;

    public BMICalculatorGUI() {
        setTitle("BMI Rechner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setPreferredSize(new Dimension(400, 150)); 

        JLabel weightLabel = new JLabel("Gewicht (kg):");
        weightField = new JTextField();
        weightField.setToolTipText("Geben Sie Ihr Gewicht in Kilogramm ein.");
        panel.add(weightLabel);
        panel.add(weightField);

        JLabel heightLabel = new JLabel("Groesse (m):");
        heightField = new JTextField();
        heightField.setToolTipText("Geben Sie Ihre Groesse in Metern ein.");
        panel.add(heightLabel);
        panel.add(heightField);

        JButton calculateButton = new JButton("Berechnen");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        resultLabel = new JLabel();
        panel.add(resultLabel);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String weightText = weightField.getText();
            String heightText = heightField.getText();

            if (weightText.isEmpty() || heightText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bitte Gewichts- und Groessenwerte eingeben.");
                return;
            }

            try {
                double weight = Double.parseDouble(weightText);
                double height = Double.parseDouble(heightText);

                double bmi = BMICalculator.calculateBMI(weight, height);
                String description = BMICalculator.getBMIStatus(bmi);

                resultLabel.setText(String.format("BMI: %.2f - %s", bmi, description));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bitte gueltige numerische Werte fuer Gewicht und Groesse eingeben.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BMICalculatorGUI();
            }
        });
    }
}
