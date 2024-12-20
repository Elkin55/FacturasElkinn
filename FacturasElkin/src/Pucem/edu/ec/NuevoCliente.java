package Pucem.edu.ec;

import javax.swing.*;
import java.awt.*;

public class NuevoCliente extends JDialog {
    private JTextField txtCedula, txtNombres, txtApellidos, txtDireccion, 
                       txtTelefono, txtEmail;
    private ListaClientes parent;
    private Cliente clienteEditar;
    
    public NuevoCliente(ListaClientes parent, Cliente cliente) {
        super(parent, true);
        this.parent = parent;
        this.clienteEditar = cliente;
        
        setTitle("Nuevo Cliente");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        
        panel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panel.add(txtNombres);
        
        panel.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panel.add(txtApellidos);
        
        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);
        
        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);
        
        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);
        
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancel");
        
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        
        if (cliente != null) {
            llenarCampos(cliente);
        }
        
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());
        
        add(panel);
    }
    
    private void llenarCampos(Cliente cliente) {
        txtCedula.setText(cliente.getCedula());
        txtNombres.setText(cliente.getNombres());
        txtApellidos.setText(cliente.getApellidos());
        txtDireccion.setText(cliente.getDireccion());
        txtTelefono.setText(cliente.getTelefono());
        txtEmail.setText(cliente.getEmail());
        
        txtCedula.setEditable(false);
    }
    
    private void guardar() {
        Cliente cliente = new Cliente(
            txtCedula.getText(),
            txtNombres.getText(),
            txtApellidos.getText(),
            txtDireccion.getText(),
            txtTelefono.getText(),
            txtEmail.getText()
        );
        
        parent.agregarOActualizarCliente(cliente, clienteEditar == null);
        dispose();
    }
}
