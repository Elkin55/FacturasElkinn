package Pucem.edu.ec;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = VentanaPrincipal.getInstance();
            ventana.setVisible(true);
        });
    }
}
