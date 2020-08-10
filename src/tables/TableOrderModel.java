/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import domain.Order;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Katica
 */
public class TableOrderModel extends AbstractTableModel{
 
    List<Order> orders;

    public TableOrderModel(List<Order> orders) {
        this.orders = orders;
    }
    
    
    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch(columnIndex){
            case 0: return order.getId();
            case 1: return order.getOrderDate();
            default: return "Nothing";
                
        }
                
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "id";
            case 1: return "Date";
            default: return "Nothing";
        }
    }

    public Order getOrder(int idSelectedOrder) {
        for(Order ord:orders){
            if(ord.getId() == idSelectedOrder){
                return ord;
            }
        }
        return null;
    }
    
    
}
