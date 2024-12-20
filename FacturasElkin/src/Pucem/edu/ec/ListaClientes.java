package Pucem.edu.ec;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ListaClientes extends JFrame {
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private static Vector<Cliente> listaClientes = new Vector<>();
    
    public ListaClientes() {
        setTitle("Lista de Clientes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        String[] columnas = {"Cédula", "Nombres", "Apellidos", "Dirección", 
                            "Teléfono", "Email", "Acción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        
        tablaClientes = new JTable(modeloTabla);
        
        JButton btnAgregar = new JButton("Agregar nuevo");
        btnAgregar.addActionListener(e -> mostrarNuevoCliente(null));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.NORTH);
        
        add(panel);
        
        configurarBotonEditar();
        cargarClientesExistentes();
    }
    
    private void configurarBotonEditar() {
        tablaClientes.getColumnModel().getColumn(6).setCellRenderer(
            new ButtonRenderer());
        tablaClientes.getColumnModel().getColumn(6).setCellEditor(
            new ButtonEditor(new JCheckBox()));
    }
    
    private void cargarClientesExistentes() {
        for(Cliente cliente : listaClientes) {
            Object[] fila = {
                cliente.getCedula(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                "Editar"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    public void mostrarNuevoCliente(Cliente clienteExistente) {
        NuevoCliente dialogo = new NuevoCliente(this, clienteExistente);
        dialogo.setVisible(true);
    }
    
    public void agregarOActualizarCliente(Cliente cliente, boolean esNuevo) {
        if (esNuevo) {
            listaClientes.add(cliente);
            Object[] fila = {
                cliente.getCedula(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                "Editar"
            };
            modeloTabla.addRow(fila);
        } else {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                if (modeloTabla.getValueAt(i, 0).equals(cliente.getCedula())) {
                    modeloTabla.setValueAt(cliente.getNombres(), i, 1);
                    modeloTabla.setValueAt(cliente.getApellidos(), i, 2);
                    modeloTabla.setValueAt(cliente.getDireccion(), i, 3);
                    modeloTabla.setValueAt(cliente.getTelefono(), i, 4);
                    modeloTabla.setValueAt(cliente.getEmail(), i, 5);
                    
                    for(int j = 0; j < listaClientes.size(); j++) {
                        if(listaClientes.get(j).getCedula().equals(cliente.getCedula())) {
                            listaClientes.set(j, cliente);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}