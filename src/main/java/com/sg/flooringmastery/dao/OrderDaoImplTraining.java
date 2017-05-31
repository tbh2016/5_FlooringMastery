/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.PersistenceException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author THUAN HUYNH
 */
public class OrderDaoImplTraining implements OrderDao{
    
    private Map<Integer, Order> orders = new HashMap<>();

   @Override
    public  void addOrder(Order order) throws PersistenceException{
         //nothing 
    }

    @Override
    public Order getOrder(Order order) throws PersistenceException{
        order = orders.get(order.getOrderNumber());
        return order;
    }
    
    @Override
    public Order editOrder(Order order) throws PersistenceException{
//        ORDER_FILE = "Order"+ order.getOrderDate() +".txt";
//        File file = new File(ORDER_FILE);
//        order = orders.remove(order.getOrderNumber());
//        write(order.getOrderDate());
//        if(orders.isEmpty()){
//            file.delete();
//        }
//        return order;
        return null;
    }
    
    @Override
    public void removeOrder(Order order) throws PersistenceException{
        //nothing 
    }
    
    @Override
    public void updateOrder(Order order) throws PersistenceException{
//        orders.put(order.getOrderNumber(), order);
//        write(order.getOrderDate());
    }
    
    @Override
    public void checkEmpty(LocalDate date) throws PersistenceException{
//        ORDER_FILE = "Orders_"+date+".txt";
//        File file = new File(ORDER_FILE);
//        if(orders.isEmpty()){
//            file.delete();
//        }
    }
    
//    @Override
//    public Order saveWork(int orderNumber, Order order) throws PersistenceException{
//        Order saveOrder = orders.put(orderNumber, order);
//        return saveOrder;
//    }
    
    
    @Override
    public ArrayList<Order> getAllAvailableOrders() throws PersistenceException{        //list is the interface
        return new ArrayList<Order>(orders.values());                               //ArrayList is the class that implements the List
    }
    
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
    
     @Override
    public void loadAllDates(LocalDate date) throws PersistenceException{
//        try {
//            load(date);
//        } catch (Exception ex) {
//            throw new PersistenceException("ERROR! ERROR! CANNOT LOAD!", ex);
//        }
    }
    
    @Override
    public List<Order> getAllOrders(LocalDate date) throws PersistenceException{
        return new ArrayList<Order>(orders.values());
    }
    
}
