import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class GuiPassGen {
    private JFrame fenster;
    private JLabel passwortLabel;
    private JTextField zeichenAnzahlFeld;
    private JButton generierenButton;

    public GuiPassGen(String titel) {
        fenster = new JFrame(titel);
        Container behaelter = fenster.getContentPane();
        behaelter.setLayout(new GridLayout(0, 1));

        passwortLabel = new JLabel("Generiertes Passwort: ");
        zeichenAnzahlFeld = new JTextField("8", 5);
        generierenButton = new JButton("Passwort generieren");

        generierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwortGenerieren();
            }
        });

        behaelter.add(new JLabel("Anzahl der Zeichen (8-32): "));
        behaelter.add(zeichenAnzahlFeld);
        behaelter.add(generierenButton);
        behaelter.add(passwortLabel);

        fenster.setPreferredSize(new Dimension(300, 200)); // Setze bevorzugte Groeße
        fenster.pack();
        fenster.setVisible(true);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void passwortGenerieren() {
        String zeichenAnzahlStr = zeichenAnzahlFeld.getText();
        try {
            int zeichenAnzahl = Integer.parseInt(zeichenAnzahlStr);
            if (zeichenAnzahl < 8 || zeichenAnzahl > 32) {
          
                JOptionPane.showMessageDialog(fenster, "Passwortlaenge muss zwischen 8 und 32 Zeichen liegen.");
                return;
            }
            String passwort = zufaelligesPasswortGenerieren(zeichenAnzahl);
            passwortLabel.setText("Generiertes Passwort: " + passwort);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(fenster, "Ungueltige Eingabe. Bitte geben Sie eine gueltige Zahl ein.");
        }
    }

    private String zufaelligesPasswortGenerieren(int laenge) {
        String erlaubteZeichen = "qwertzuioASDFGGHJHJL!?-;.$";
        SecureRandom zufaellig = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < laenge; i++) {
            int index = zufaellig.nextInt(erlaubteZeichen.length());
            sb.append(erlaubteZeichen.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new GuiPassGen("Passwort Generator");
    }
}
