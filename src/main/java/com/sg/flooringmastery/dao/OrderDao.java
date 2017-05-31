/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public interface OrderDao {
     void addOrder(Order order) throws PersistenceException;
     Order getOrder(Order order) throws PersistenceException;
     Order editOrder(Order order) throws PersistenceException;
     void removeOrder(Order order) throws PersistenceException;
//     Order saveWork(int orderNumber, Order order) throws PersistenceException;
     
     ArrayList<Order> getAllAvailableOrders() throws PersistenceException;
     Integer getOrderNumber(ArrayList<Order> orderList) throws PersistenceException;
     
     void loadAllDates(LocalDate date) throws PersistenceException;
     
     List<Order> getAllOrders(LocalDate date) throws PersistenceException;
     
     void updateOrder(Order order) throws PersistenceException;
     
     void checkEmpty(LocalDate date) throws PersistenceException;
}
