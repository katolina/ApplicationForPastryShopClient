/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import domain.Manufacturer;
import domain.MeasurementUnit;
import domain.Product;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ProductTableModel extends AbstractTableModel{
    
    private final List<Product> products;
    private final String[] columns = new String[]{"ID","Name","Quantity","MeasurementUnit","Manufacturer","Price without VAT","Price with VAT"};
    private final Class[] columnsType = new Class[]{int.class, String.class, int.class, MeasurementUnit.class, Manufacturer.class,double.class,double.class};
    
    
    public ProductTableModel(List<Product> products) {
        this.products = products;
    }

 
    

    @Override
    public int getRowCount() {
        if(products == null){
            return 0;
        } 
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Product product = products.get(row);
        switch(column){
            case 0: return product.getID();
            case 1: return product.getName();
            case 2: return product.getQuantity(); 
            case 3: return product.getMeasurementUnit(); 
            case 4: return product.getManufacturer(); 
            case 5: return product.getPriceWithoutVAT();
            case 6: return product.getPriceWithVat(); 
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 2 || column == 3 || column == 4 || column == 5;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Product product = products.get(row);
        
        switch(column){
            case 2: 
                String quantity = (String) value;
                int quanti = Integer.parseInt(quantity);
                product.setQuantity(quanti);
            break;
            case 3: 
                //String mu = (String) value;
                
                product.setMeasurementUnit((MeasurementUnit) value);
            break;
            case 4: 
               
            break;
            case 5: 
               product.setPriceWithoutVAT((double) value);
            break;
            case 6:
                product.setPriceWithVat((double) value);
        
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnsType[columnIndex];
    }

    public Product getProduct(int idSelectedProduct) {
        for(Product prod:products){
            if(prod.getID() == idSelectedProduct){
                return prod;
            }
            
    }
    
    
   return null;
    
}

    public void delete(Product product) {
        for(Product prod: products){
            if(prod.getID() == product.getID()){
                 products.remove(prod);
                 fireTableDataChanged();
            }
        }
       
        
    }

    public void addProduct(Product product) {
        products.add(product);
        fireTableDataChanged();
    }
}
