import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyTextField extends JPanel {

    private JLabel label1;
    private JTextField textField;
    private JLabel hintLabel;

    public MyTextField(String labelText, String hintText) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        label1 = new JLabel(labelText);
        textField = new JTextField(40);
        hintLabel = new JLabel(hintText);
        hintLabel.setForeground(Color.RED);

        add(label1);
        add(textField);
        add(hintLabel);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateHint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateHint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateHint();
            }
        });

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Do nothing
            }

            @Override
            public void focusLost(FocusEvent e) {
                updateHint();
            }
        });
    }

    private void updateHint() {
        if (textField.getText().trim().isEmpty()) {
            hintLabel.setForeground(Color.RED);
        } else {
            hintLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyTextField Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        MyTextField textField1 = new MyTextField("Patienname:", "Nachname eingeben");
        MyTextField textField2 = new MyTextField("Vorname:", "Nachname eingeben. Max.10 zeichen, keine leerzeichen");
        MyTextField textField3 = new MyTextField("Strasse:", "Strasse und hausnummer eingeben <Flower-Strasse 13>");

        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
