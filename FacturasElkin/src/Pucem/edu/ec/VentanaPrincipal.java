package Pucem.edu.ec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private static VentanaPrincipal instance;
    private ListaClientes listaClientesFrame;
    
    public VentanaPrincipal() {
        setTitle("SISTEMA DE FACTURACIÓN ELELCTRÓNICA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu archivoMenu = new JMenu("Archivo");
        JMenu clientesMenu = new JMenu("Clientes");
        JMenu redesMenu = new JMenu("Redes sociales");
        
        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(e -> System.exit(0));
        archivoMenu.add(salirItem);
        
        JMenuItem listaClientesItem = new JMenuItem("Lista de Clientes");
        listaClientesItem.addActionListener(e -> mostrarListaClientes());
        clientesMenu.add(listaClientesItem);
        
        menuBar.add(archivoMenu);
        menuBar.add(clientesMenu);
        menuBar.add(redesMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void mostrarListaClientes() {
        if (listaClientesFrame == null || !listaClientesFrame.isVisible()) {
            listaClientesFrame = new ListaClientes();
            listaClientesFrame.setVisible(true);
        }
    }
    
    public static VentanaPrincipal getInstance() {
        if (instance == null) {
            instance = new VentanaPrincipal();
        }
        return instance;
    }
}