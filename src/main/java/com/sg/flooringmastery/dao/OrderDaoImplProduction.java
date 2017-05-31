/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;


import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.PersistenceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author THUAN HUYNH
 */
public class OrderDaoImplProduction implements OrderDao{
    
    //************************************************************************//
    //***************************HASHMAP, textfile, delimiter************************************//
    //************************************************************************//
    private Map<Integer, Order> orders = new HashMap<>();
    public String ORDER_FILE;
    public String DELIMITER = ", ";
    
    
    //************************************************************************//
    //***************************CONTROLLER DAO METHODS************************************//
    //************************************************************************//
    @Override
    public  void addOrder(Order order) throws PersistenceException{
        Order newOrder = orders.put(order.getOrderNumber(), order);
        write(order.getOrderDate());
    }
    
    @Override
    public Order getOrder(Order order) throws PersistenceException{
        order = orders.get(order.getOrderNumber());
        return order;
    }
    
    
    @Override
    public Order editOrder(Order order) throws PersistenceException{
        ORDER_FILE = "Order_" + order.getOrderDate() +".txt";
        File file = new File(ORDER_FILE);
        order = orders.remove(order.getOrderNumber());
        write(order.getOrderDate());
        if(orders.isEmpty()){
            file.delete();
        }
        return order;
    }
    
    @Override
    public void removeOrder(Order order) throws PersistenceException{ 
        ORDER_FILE = "Order_"+ order.getOrderDate() +".txt";
        File file = new File(ORDER_FILE);
        Order remove = orders.remove(order.getOrderNumber());
        write(order.getOrderDate());
        if(orders.isEmpty()){
            file.delete();
        }
    }
    
    @Override
    public void updateOrder(Order order) throws PersistenceException{
        orders.put(order.getOrderNumber(), order);
        write(order.getOrderDate());
    }
    
    @Override
    public void checkEmpty(LocalDate date) throws PersistenceException{
        ORDER_FILE = "Order_"+date+".txt";
        File file = new File(ORDER_FILE);
        if(orders.isEmpty()){
            file.delete();
        }
    }
    
//    @Override
//    public Order saveWork(int orderNumber, Order order) throws PersistenceException{
//        Order saveOrder = orders.put(orderNumber, order);
//        write(order.getOrderDate());
//        return saveOrder;
//    }
    
//    @Override
//    public Order emptyOrder(int orderNumber, Order order) throws PersistenceException{
//        
//    }
    
    
    
     //************************************************************************//
    //***************************GET ORDER NUMBER METHOD************************************//
    //**************************************************************************//        
    
    @Override
    public Integer getOrderNumber(ArrayList<Order> orderList) throws PersistenceException{
        int count =0;
        for (Order currentOrderNumber : orderList) {
            int countNum = currentOrderNumber.getOrderNumber();

            while (countNum > count) {
                count = countNum;    
            }  
        }
        return count+1;
    }
    
    
    
    
    //************************************************************************//
    //***************************PRIVATE METHOD************************************//
    //****************Need method for Write method*********************//        
    @Override
    public ArrayList<Order> getAllAvailableOrders() throws PersistenceException{        //list is the interface
        return new ArrayList<Order>(orders.values());                               //ArrayList is the class that implements the List
    }
    
    
    
    //************************************************************************//
    //***************************GET ALL ORDERS BY DATE************************************//
    //****************Need method for Write method*********************// 
    @Override
    public List<Order> getAllOrders(LocalDate date) throws PersistenceException{
        return new ArrayList<Order>(orders.values());
    }
    
    //************************************************************************//
    //***************************WRITE AND LOAD METHODS************************************//
    //************************************************************************//  
    private void loadOrder(LocalDate date) throws PersistenceException{
        orders.clear();
        ORDER_FILE = "Order_"+ date+".txt";
        Scanner scanner;
        File file = new File(ORDER_FILE);
        if(file.exists()){
            
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load data into memory.",e);
        }     
        scanner.nextLine();
        String currentLine;
        String[] currentTokens = new String[13]; 
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            

            Order currentOrder = new Order(Integer.parseInt((currentTokens[0])));
            
            currentOrder.setOrderDate(LocalDate.parse(currentTokens[1]));
            currentOrder.setClientName(currentTokens[2]);
            currentOrder.setState(currentTokens[3]);
            currentOrder.setStateTax(new BigDecimal(currentTokens[4]));
            currentOrder.setProduct(currentTokens[5]);
            currentOrder.setArea(new BigDecimal(currentTokens[6]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[7]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[8]));
            currentOrder.setTotalMaterialCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[11]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[12]));
            
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
         scanner.close();
        } else{
            try{
                file.createNewFile();
            } catch (IOException ex){
                throw new PersistenceException("Error!  No orders from that date.", ex);
            }
        }
    }

    
    private void write(LocalDate date) throws PersistenceException{
        ORDER_FILE = "Order_" + date + ".txt";
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }
        if(!ORDER_FILE.contains(",")){ 
            out.println("Order Number" + DELIMITER +
                    "Date" + DELIMITER +
                    "Client Name" + DELIMITER +
                    "State"  + DELIMITER +
                    "Tax Rate"  + DELIMITER +
                    "Product Type" + DELIMITER +
                    "Area"  + DELIMITER +
                    "Cost per squarefoot" + DELIMITER +
                    "Labor per squarefoot" + DELIMITER +
                    "Material Cost" + DELIMITER +
                    "Labor Cost" + DELIMITER +
                    "Tax" + DELIMITER +
                    "Total" + DELIMITER);
            out.flush();
        }
        List<Order> orderList = this.getAllAvailableOrders();
        for (Order i : orderList) {
            out.println(i.getOrderNumber()+ DELIMITER ///out is name for PrintWrtie library 
                    + i.getOrderDate() + DELIMITER
                    + i.getClientName() + DELIMITER
                    + i.getState() + DELIMITER
                    + i.getStateTax() + DELIMITER
                    + i.getProduct() + DELIMITER
                    + i.getArea() + DELIMITER
                    + i.getMaterialCost() + DELIMITER
                    + i.getLaborCost() + DELIMITER
                    + i.getTotalMaterialCost() + DELIMITER
                    + i.getTotalLaborCost() + DELIMITER
                    + i.getTotalTax() + DELIMITER 
                    + i.getTotalCost() + DELIMITER);
            out.flush(); //flush the printer
        }
        out.close();
    }
    
    
    
    //************************************************************************//
    //***************************LOAD ORDER DATES************************************//
    //************************************************************************//  
     @Override
    public void loadAllDates(LocalDate date) throws PersistenceException{
        try {
            loadOrder(date);
        } catch (PersistenceException ex) {
            throw new PersistenceException("ERROR! ERROR! CANNOT LOAD!", ex);
        }
    }
    
}
