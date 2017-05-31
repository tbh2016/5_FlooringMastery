/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.PersistenceException;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public interface StateTaxDao {
    
    public List<StateTax> getAllStateTax() throws PersistenceException;
    public StateTax getStateByName(String name);
//    BigDecimal calculateStateTax(int choice) throws PersistenceException;
//    
//    String showState(int choice) throws PersistenceException;
}
