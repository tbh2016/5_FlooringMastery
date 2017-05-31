/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class StateTaxDaoImpl implements StateTaxDao{
    
//    private List<BigDecimal> stateAndTax = new ArrayList<>();
    
    
    
//    public BigDecimal calculateStateTax(int choice) throws PersistenceException {      //want to return BigDecimal, not the order object
//        BigDecimal bd = new BigDecimal("0");
//        switch (choice) {
//            case 1:
//                bd = new BigDecimal("6.25");
//            case 2:
//                bd = new BigDecimal("6.75");
//            case 3:
//                bd = new BigDecimal("5.75");
//            case 4:
//                bd = new BigDecimal("6.00");
//        }
//        return bd;
//    }
//
//    public String showState(int choice) throws PersistenceException {
//        String state = new String("");
//        switch (choice) {
//            case 1:
//                state = new String("OH");
//            case 2:
//                state = new String("PA");
//            case 3:
//                state = new String("MI");
//            case 4:
//                state = new String("IN");
//        }
//        return state;
//    }
    
    
    
    
    private Map<String, StateTax> tax = new HashMap<>();
    public static final String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";
    
    @Override
    public List<StateTax> getAllStateTax() throws PersistenceException{
        loadState();

        return new ArrayList<StateTax>(tax.values());
    }
    
    @Override
    public StateTax getStateByName(String name){
        return tax.get(name);
    }
    
    private void loadState() throws PersistenceException{
        Scanner scanner;
     
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load Taxes.txt data into memory.",e);
        }       
        String currentLine;
        String[] currentTokens; 
        
        //This is needed bacause the first line is not actual data. Check later or leave 
        scanner.nextLine();//************************IMPORTANT*****************************//
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
           
            StateTax currentStateTax = new StateTax(currentTokens[0]);
            currentStateTax.setTaxRate(new BigDecimal(currentTokens[1]));
     
            
            tax.put(currentStateTax.getState(), currentStateTax);
        }
         scanner.close();
    }
}
