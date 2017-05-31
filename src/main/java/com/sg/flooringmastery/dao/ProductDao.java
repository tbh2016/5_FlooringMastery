/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public interface ProductDao {
    
//    String materialType(int choice) throws PersistenceException;
//    
//    BigDecimal costPerSquareFoot(int choice) throws PersistenceException;
//    
//    BigDecimal laborCostPerSquareFoot(int choice) throws PersistenceException;
    
     List<Product> getAllProducts() throws PersistenceException;
     
     Product getProductByName(String name);
}
