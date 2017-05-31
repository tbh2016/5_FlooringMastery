/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author THUAN HUYNH
 */
public class ProductDaoImpl implements ProductDao{
    
//    public String materialType(int choice) throws PersistenceException {
//        String productType = new String("");
//        switch (choice) {
//            case 1:
//                productType = new String("Carpet");
//            case 2:
//                productType = new String("Laminate");
//            case 3:
//                productType = new String("Tile");
//            case 4:
//                productType = new String("Wood");
//        }
//        return productType;
//    }
//    
//    public BigDecimal costPerSquareFoot(int choice) throws PersistenceException {
//        BigDecimal CPSQFT = new BigDecimal("0");
//        switch (choice) {
//            case 1:
//                CPSQFT = new BigDecimal("2.25");
//            case 2:
//                CPSQFT = new BigDecimal("1.75");
//            case 3:
//                CPSQFT = new BigDecimal("3.50");
//            case 4:
//                CPSQFT = new BigDecimal("5.15");
//        }
//        return CPSQFT;
//    }
//    
//    public BigDecimal laborCostPerSquareFoot(int choice) throws PersistenceException {
//        BigDecimal laborCPSQFT = new BigDecimal("0");
//        switch (choice) {
//            case 1:
//                laborCPSQFT = new BigDecimal("2.10");
//            case 2:
//                laborCPSQFT = new BigDecimal("2.10");
//            case 3:
//                laborCPSQFT = new BigDecimal("4.15");
//            case 4:
//                laborCPSQFT = new BigDecimal("4.75");
//        }
//        return laborCPSQFT;
//    }
    
    
    
    
    private Map<String, Product> product = new HashMap<>();
    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    
    @Override
    public List<Product> getAllProducts() throws PersistenceException{
        loadProduct();

        return new ArrayList<Product>(product.values());
    }
    
    @Override
    public Product getProductByName(String name){
        return product.get(name);
    }
    
    private void loadProduct() throws PersistenceException{
        Scanner scanner;
     
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
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
            
           
            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setMaterialCostPerSQFT(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSQFT(new BigDecimal(currentTokens[2]));
     
            
            product.put(currentProduct.getProduct(), currentProduct);
        }
         scanner.close();
    }
}
