/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import domain.Invoice;
import domain.InvoiceItem;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Katica
 */
public class InvoiceTableModel extends AbstractTableModel{
    public Invoice invoice;

    public InvoiceTableModel(Invoice invoice) {
        this.invoice = invoice;
    }

    
    @Override
    public int getRowCount() {
        return invoice.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem invoiceItem = invoice.getItems().get(rowIndex);
        switch(columnIndex){
            case 0: return invoiceItem.getProduct().getName();
            case 1: return invoiceItem.getQuantity();
            
            default: return "Nothing";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Product";
            case 1: return "Quantity";
            
    }
    return "Nothing";
    
}  
}
