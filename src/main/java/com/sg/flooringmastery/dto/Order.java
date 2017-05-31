/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author THUAN HUYNH
 */
public class Order {
    int orderNumber;
    LocalDate orderDate;
    String clientName;
    String state;
    BigDecimal stateTax;
    String product;
    BigDecimal area;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal totalMaterialCost;
    BigDecimal totalLaborCost;
    BigDecimal totalTax;
    BigDecimal totalCost;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    
    public int getOrderNumber() {
        return orderNumber;
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.orderNumber);      //
        hash = 89 * hash + Objects.hashCode(this.orderDate);
        hash = 89 * hash + Objects.hashCode(this.clientName);
        hash = 89 * hash + Objects.hashCode(this.state);
        hash = 89 * hash + Objects.hashCode(this.stateTax);
        hash = 89 * hash + Objects.hashCode(this.product);
        hash = 89 * hash + Objects.hashCode(this.area);
        hash = 89 * hash + Objects.hashCode(this.materialCost);
        hash = 89 * hash + Objects.hashCode(this.laborCost);
        hash = 89 * hash + Objects.hashCode(this.totalMaterialCost);
        hash = 89 * hash + Objects.hashCode(this.totalLaborCost);
        hash = 89 * hash + Objects.hashCode(this.totalTax);
        hash = 89 * hash + Objects.hashCode(this.totalCost);
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {                      //
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.clientName, other.clientName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.stateTax, other.stateTax)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalMaterialCost, other.totalMaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.totalLaborCost, other.totalLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
         if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }
     
    @Override
    public String toString(){
        return "Order Number. " + orderNumber + " |Order Date: " + orderDate + " |Client Name: " + clientName 
                + " |State: " + state + " |State Tax: " + stateTax + " |Product: " + product
                + " |Area: " + area + " |Material Cost: " + materialCost + " |Labor Cost: " + laborCost
                + " |Total Material Cost: " + totalMaterialCost + " |Total Labor Cost: " + totalLaborCost + " |Total Tax: " + totalTax
                + " |Total Cost: " + totalCost;
    }
}
