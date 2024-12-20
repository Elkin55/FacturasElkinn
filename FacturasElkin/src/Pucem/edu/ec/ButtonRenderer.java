package Pucem.edu.ec;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
    
    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JTable tabla = ((JTable)button.getParent());
            int fila = tabla.getSelectedRow();
            
            Cliente cliente = new Cliente(
                tabla.getValueAt(fila, 0).toString(),
                tabla.getValueAt(fila, 1).toString(),
                tabla.getValueAt(fila, 2).toString(),
                tabla.getValueAt(fila, 3).toString(),
                tabla.getValueAt(fila, 4).toString(),
                tabla.getValueAt(fila, 5).toString()
            );
            
            ((ListaClientes)tabla.getTopLevelAncestor()).mostrarNuevoCliente(cliente);
        }
        isPushed = false;
        return label;
    }
    
    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
