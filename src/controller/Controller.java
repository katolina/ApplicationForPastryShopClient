/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
//import db.DBBroker;
import domain.Guest;
import domain.Invoice;
import domain.Manufacturer;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import service.impl.ServiceInvoiceImpl;
//import service.impl.ServiceManufacturerImpl;
//import service.impl.ServiceOrderImpl;
//import service.impl.ServiceProductImpl;
//import services.ServiceInvoice;
//import services.ServiceManufacturer;
//import services.ServiceOrder;
//import services.ServiceProduct;
//import storage.memory.StorageInvoice;
//import storage.memory.StorageOrder;
//import storages.MemoryStorageProduct;
//import storages.MemoryStorageUser;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author Korisnik
 */
public class Controller {

    private static Controller instance;
    private Map<String, Object> map;
    private Socket socket;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    private Controller() throws IOException {
        map = new HashMap<>();
        socket = new Socket("localhost", 9000);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public static Controller getInstance() throws IOException {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Product saveProduct(Product product) throws Exception {

        RequestObject requestObject = new RequestObject(Operation.OPERATION_SAVE_PRODUCT, product);
        objectOutputStream.writeObject(requestObject);
        ResponseObject response = (ResponseObject) objectInputStream.readObject();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {

            Product product1 = (Product) response.getData();
            this.map.put("current.product", product1);
            return product1;
        }

        throw new Exception(response.getErrorMessage());
    }

    public List<Product> getAllProducts() throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_GET_ALL_PRODUCTS);
        objectOutputStream.writeObject(requestObject);

        objectOutputStream.flush();

        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();

        ResponseStatus status = responseObject.getStatus();
        if (status == ResponseStatus.SUCCESS) {

            return (List<Product>) responseObject.getData();
        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }

    public void login(String username, String password) throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_LOGIN);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", username);
        userMap.put("password", password);
        requestObject.setData(userMap);

        objectOutputStream.writeObject(requestObject);
        objectOutputStream.flush();
        //waiting response

        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();

        ResponseStatus status = responseObject.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            map.put("user", responseObject.getData());
        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }

    public List<Manufacturer> getAllManufacturer() throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_GET_ALL_MANUFACTURERS);

        objectOutputStream.writeObject(requestObject);
        objectOutputStream.flush();
        //waiting response
        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();
        
        ResponseStatus status = responseObject.getStatus();
        System.out.println(status);
        if (status == ResponseStatus.SUCCESS) {
            return (List<Manufacturer>) responseObject.getData();

        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }

    private void getAllUsers() {

    }

    public Order saveOrder(Order order) throws Exception {
        RequestObject requestObject = new RequestObject(Operation.OPERATION_SAVE_ORDER, order);
        objectOutputStream.writeObject(requestObject);
        ResponseObject response = (ResponseObject) objectInputStream.readObject();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {

            Order order1 = (Order) response.getData();

            return order1;
        }

        throw new Exception(response.getErrorMessage());
    }

    public List<Order> getAllOrders() throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_GET_ALL_ORDERS);
                System.out.println("PRINTIC");
        objectOutputStream.writeObject(requestObject);
        objectOutputStream.flush();
        //waiting response
        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();
        
        ResponseStatus status = responseObject.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<Order>) responseObject.getData();
        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }

    public void setProduct(Product product) {
        map.put("current.product", product);
    }

    public Map getMap() {
        return map;
    }

    public List<OrderItem> getOrderItems() throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_GET_ALL_ORDER_ITEMS);

        objectOutputStream.writeObject(requestObject);
        objectOutputStream.flush();
        //waiting response
        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();

        ResponseStatus status = responseObject.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (List<OrderItem>) responseObject.getData();
        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }

    public Invoice saveInvoice(Invoice invoice) throws Exception {
        RequestObject requestObject = new RequestObject(Operation.OPERATION_SAVE_INVOICE, invoice);
        objectOutputStream.writeObject(requestObject);
        ResponseObject response = (ResponseObject) objectInputStream.readObject();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            Invoice invoice1 = (Invoice) response.getData();

            return invoice1;
        }

        throw new Exception(response.getErrorMessage());
    }

    public boolean deleteProduct(Product product) throws Exception {
        RequestObject requestObject = new RequestObject(Operation.OPERATION_DELETE_PRODUCT, product);
        objectOutputStream.writeObject(requestObject);
        ResponseObject response = (ResponseObject) objectInputStream.readObject();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            boolean delete = (boolean) response.getData();

            return delete;
        }

        throw new Exception(response.getErrorMessage());
    }

    public void sendRequest(RequestObject request) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(request);
    }

    public ResponseObject receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (ResponseObject) inSocket.readObject();
    }

    public Guest saveGuest(Guest guest) throws Exception {
        RequestObject requestObject = new RequestObject(Operation.OPERATION_SAVE_GUEST, guest);
        objectOutputStream.writeObject(requestObject);
        ResponseObject response = (ResponseObject) objectInputStream.readObject();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            Guest guest1 = (Guest) response.getData();
            return guest1;

        }
        throw new Exception(response.getErrorMessage());
    }

    public ArrayList<Guest> getAllGuest() throws Exception {
        RequestObject requestObject = new RequestObject();
        requestObject.setOperation(Operation.OPERATION_GET_ALL_GUESTS);

        objectOutputStream.writeObject(requestObject);
        objectOutputStream.flush();
        //waiting response
        ResponseObject responseObject = (ResponseObject) objectInputStream.readObject();

        ResponseStatus status = responseObject.getStatus();
        if (status == ResponseStatus.SUCCESS) {
            return (ArrayList<Guest>) responseObject.getData();
        } else {
            throw new Exception(responseObject.getErrorMessage());
        }
    }
}
