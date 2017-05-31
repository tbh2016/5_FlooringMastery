/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author THUAN HUYNH
 */
public class Product {
    String product;
    BigDecimal materialCostPerSQFT;
    BigDecimal laborCostPerSQFT;

    public Product(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getMaterialCostPerSQFT() {
        return materialCostPerSQFT;
    }

    public void setMaterialCostPerSQFT(BigDecimal materialCostPerSQFT) {
        this.materialCostPerSQFT = materialCostPerSQFT;
    }

    public BigDecimal getLaborCostPerSQFT() {
        return laborCostPerSQFT;
    }

    public void setLaborCostPerSQFT(BigDecimal laborCostPerSQFT) {
        this.laborCostPerSQFT = laborCostPerSQFT;
    }
    
    
}
