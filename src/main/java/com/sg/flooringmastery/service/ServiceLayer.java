/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public interface ServiceLayer {
     void addOrder(Order order) throws PersistenceException;
     Order getOrder(Order order) throws PersistenceException;
     Order editOrder(Order order) throws PersistenceException;
     void removeOrder(Order order) throws PersistenceException;
//     Order saveWork(int orderNumber, Order order) throws PersistenceException;
     
     List<StateTax> getAllStateTax() throws PersistenceException;
     List<Product> getAllProducts() throws PersistenceException;
     
     BigDecimal totalMaterialCost(BigDecimal area, BigDecimal matCost) throws PersistenceException;
     BigDecimal totalLaborCost(BigDecimal area, BigDecimal labCost) throws PersistenceException;
     BigDecimal totalTax(BigDecimal totalMaterialCost, BigDecimal totalLaborCost, BigDecimal taxRate) throws PersistenceException;
     BigDecimal totalCost(BigDecimal totalMaterialCost, BigDecimal totalLaborCost, BigDecimal totalTax) throws PersistenceException;
     
     ArrayList<Order> getAllAvailableOrders() throws PersistenceException;
    Integer getOrderNumber(ArrayList<Order> orderList) throws PersistenceException;

    Order generateOrder(int orderNumber, LocalDate date,String name,String state,BigDecimal taxRate,String product,BigDecimal area,BigDecimal matCost,BigDecimal labCost,BigDecimal TMC,BigDecimal TLC,BigDecimal TT,BigDecimal TC);
    Order updatedGenerateOrder(LocalDate date, BigDecimal area, String name, Product prod, StateTax state, int id) throws PersistenceException;
    
    void loadAllDates(LocalDate date) throws PersistenceException;
    
    List<Order> getAllOrders(LocalDate date) throws PersistenceException;
    
    //************************************************************************//
    //***************************EDIT ORDER************************************//
    //************************************************************************//
    Product getProductByName(String name);
    StateTax getStateByName(String name);
    void updateOrder(Order order) throws PersistenceException;
    void checkEmpty(LocalDate date) throws PersistenceException;
    
    void setDaos(OrderDao dao);
}
