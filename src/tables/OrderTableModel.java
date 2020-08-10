/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import controller.Controller;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Katica
 */
public class OrderTableModel extends AbstractTableModel{

    private final Order order;
   
    String[] columns = new String[]{"Number","Product name","Quantity"};

    public OrderTableModel(Order order) {
        this.order = order;
    }

    public OrderTableModel() {
        this.order = null;
    }

   

  
    
    @Override
    public int getRowCount() {
        if(order == null){
            return 0;
        }
        return order.getOrderItems().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 2) return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        OrderItem orderItem = order.getOrderItems().get(rowIndex);
        switch(columnIndex){
            case 2: String value = (String) aValue;
            int quanty = Integer.parseInt(value);
            orderItem.setQuantityOrder(quanty);
            break;
            
        }
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderItem orderItem = order.getOrderItems().get(rowIndex);
        switch(columnIndex){
            case 0: return orderItem.getNumberOrderItem();
            case 1: return orderItem.getProduct().getName();
            case 2: return orderItem.getQuantityOrder();
            default: return "Nothing";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void addProduct(Product selectedProduct, Date dateOrder, int quanty) {
        OrderItem orderItem = new OrderItem(order,order.getOrderItems().size()+1, quanty, selectedProduct);
        order.getOrderItems().add(orderItem);
        fireTableDataChanged();
    }

    public void deleteOrderItem(int row) {
        order.getOrderItems().remove(row);
       setOrderNumber();
        fireTableDataChanged();
    }

    public Order getOrder() {
        return order;
    }

    public void addInvoice(int row) throws Exception {
        OrderItem orderItem = order.getOrderItems().get(row);
        order.getOrderItems().add(orderItem);
        fireTableDataChanged();
    }

    private void setOrderNumber() {
        int orderNo = 0;
        for (OrderItem item : order.getOrderItems()) {
            item.setNumberOrderItem(++orderNo);
        }
    }

   

    

   

   
   
}
