/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public class View {
    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }
    //************************************************************************//
    //***************************MENU************************************//
    //************************************************************************//
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Order");
        io.print("2. Add Order");
        io.print("3. Edit Order");
        io.print("4. Remove Order");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    
    
    //************************************************************************//
    //***************************DISPLAY ORDER************************************//
    //************************************************************************//
    public LocalDate promptDispayOrderDate() { 
        return io.readDate("Please enter a display date? ");
    }
    public void displayAllOrders(List<Order> orderList) {
        for (Order order : orderList) {
            io.print("*********************************");
            io.print("||Order Number:\t\t" + order.getOrderNumber());
            io.print("||Client's Name:\t" + order.getClientName());
            io.print("||State:\t\t" + order.getState());
            io.print("||Tax Rate:\t\t" + order.getStateTax());
            io.print("||Product:\t\t" + order.getProduct());
            io.print("||Area in squarefeet:\t" + order.getArea());
            io.print("||Material Cost:\t$" + order.getMaterialCost());
            io.print("||Labor Cost:\t\t$" + order.getLaborCost());
            io.print("||Total Material Cost: \t$" + order.getTotalMaterialCost());
            io.print("||Total Labor Cost: \t$" + order.getTotalLaborCost());
            io.print("||Total Tax:\t\t$" + order.getTotalTax().setScale(2, RoundingMode.HALF_UP));
            io.print("||Total: \t\t$" + order.getTotalCost().setScale(2, RoundingMode.HALF_UP));
            io.print("*********************************");
        }
    }
    
    
    
    //************************************************************************//
    //***************************ADD ORDER************************************//
    //************************************************************************//
    public LocalDate displayAskForOrderDate() {
 
        return io.readFutureDate("Please enter a future order date 7 Days from now? ");
    }
    public String displayAskForFullName() {
        return io.readString("What is your first and last name? ");
    }
    public int displayStateTaxList(List<StateTax> stateTaxList) {
        int count = 1;
        for (StateTax currentState : stateTaxList) {
            io.print(count + ": "
                    + currentState.getState() + " "
                    + currentState.getTaxRate());
            count++;
        }
        return io.readInt("State and TaxRate displayed respectively. \nFrom above, based on the state your from, please enter 1,2,3, or 4.", 1, stateTaxList.size());
    }
    
    public StateTax promptState(List<StateTax> stateList) {
        int selection = 0;
        int statePosition = 1;
        io.print("|State \t\t|Tax");
        for (StateTax state : stateList) {
            io.print("|" + statePosition + ") " + state.getState() + "\t\t|" + state.getTaxRate());
            statePosition++;
        }
        selection = io.readInt("Please choose a state.", 1, stateList.size());
        return stateList.get(selection - 1);
    }
    
    public BigDecimal displayAskForArea() {
        return io.readBigDecimal("How much area do you want(based on square foot)? ");
    }
   public int displayProductList(List<Product> productList) {
        int count = 1;
        for (Product currentProduct : productList) {
            io.print(count + ": "
                    + currentProduct.getProduct() + " "
                    + currentProduct.getMaterialCostPerSQFT() + " "
                    + currentProduct.getLaborCostPerSQFT());
            count++;
        }
        return io.readInt("Material, MaterialCostPerSQFT, LaborCostPerSQFT displayed respectively. \nFrom above please enter the material "
                + "you would like for your order, select 1,2,3,or 4.", 1, productList.size());
    }
   
   public Product promptProduct(List<Product> prodList) {
        int selection = 0;
        int prodPosition = 1;
        io.print("|Product Type\t|Cost per squarefoot\t|Labor per squarefoot\t|");
        for (Product prod : prodList) {
            io.print("|" + prodPosition + ") " + prod.getProduct() + "\t|" + prod.getMaterialCostPerSQFT() + "\t\t\t|" + prod.getLaborCostPerSQFT());
            prodPosition++;
        }
        selection = io.readInt("Please choose a product.", 1, prodList.size());
        return prodList.get(selection - 1);
    }
   
    public List<Order> displayOrderInvoice(int number, LocalDate date, String name, String state, BigDecimal stateTax, String product, BigDecimal area, BigDecimal matCost, 
        BigDecimal labCost, BigDecimal totalMatCost, BigDecimal totalLaborCost, BigDecimal totalTax, BigDecimal totalCost) {
        io.print("Order number: " + number);
        io.print("Order date: " + date);   
        io.print("Name: " + name);    
        io.print("State: " + state);
        io.print("State Tax: " + stateTax);
        io.print("Product: " + product);   
        io.print("Area: " + area);    
        io.print("Material Cost: " + matCost);
        io.print("Labor Cost: " + labCost);
        io.print("Total Material Cost: " + totalMatCost);
        io.print("Total Labor Cost: " + totalLaborCost);   
        io.print("Total Tax: " + totalTax);    
        io.print("Total Cost: " + totalCost);
        return null;
    }
    
    public int askForOrderNumber() {
        return io.readInt("What order number would you like? (must be an integer)", 1, 1000);
    }
    
    public boolean confirmAdd(Order order) {
        boolean keepRunning = true;
        io.print("*********************************");
        io.print("||Order Number:\t\t" + order.getOrderNumber());
        io.print("||Client's Name:\t" + order.getClientName());
        io.print("||State:\t\t" + order.getState());
        io.print("||Tax Rate:\t\t" + order.getStateTax());
        io.print("||Product:\t\t" + order.getProduct());
        io.print("||Area in squarefeet:\t" + order.getArea());
        io.print("||Material Cost:\t$" + order.getMaterialCost());
        io.print("||Labor Cost:\t\t$" + order.getLaborCost());
        io.print("||Total Material Cost: \t$" + order.getTotalMaterialCost());
        io.print("||Total Labor Cost: \t$" + order.getTotalLaborCost());
        io.print("||Total Tax:\t\t$" + order.getTotalTax().setScale(2, RoundingMode.HALF_UP));
        io.print("||Total: \t\t$" + order.getTotalCost().setScale(2, RoundingMode.HALF_UP));
        io.print("*********************************");

        while (keepRunning) {
            String ans = io.readString("Are you sure you want to add this order? (y/n)");
            if (ans.toLowerCase().contains("y")) {
                return true;
            } else if (ans.toLowerCase().contains("n")) {
                return false;
            } else {
                io.print("Invalid input, please try again.");
            }
        }
        return false;
    }
    
    //************************************************************************//
    //***************************EDIT ORDER************************************//
    //************************************************************************//
    public String displayEditOrderDate() {
        return io.readString("What is your order date? ");
    }
    public LocalDate promptEditOrderDate() { 
        return io.readDate("Please enter a date for editing? ");
    }
    public String displayEditOrderNumber() {
        return io.readString("What is your order number your would like to edit? ");
    }
    public Order displayAllOrdersForEditing(List<Order> orderList) {
        int position = 1;
        int choice;
        for (Order order : orderList) {
            io.print("=============" + position + "==================");
            io.print("||Customer Name: " + order.getClientName());
            io.print("||State: " + order.getState());
            io.print("||Product: " + order.getProduct());
            io.print("||Area: " + order.getArea());
            io.print("*********************************");
            position++;
        }
        choice = io.readInt("Please select the order you want to edit.", 1, orderList.size());
        return orderList.get(choice - 1);
    } 
    
    public boolean confirmEdit(Order order){
        boolean keepGoing = true;
        io.print("=====================================");
        io.print("||Order Number: " + order.getOrderNumber());
        io.print("||Customer's Name: " + order.getClientName());
        io.print("||State: " + order.getState());
        io.print("||Tax Rate: " + order.getStateTax());
        io.print("||Product: " + order.getProduct());
        io.print("||Area per SQFT: " + order.getArea());
        io.print("||Material Cost per SQFT: " + order.getMaterialCost());
        io.print("||Labor Cost per SQFT: " + order.getLaborCost());
        io.print("||Total Material Cost: " + order.getTotalMaterialCost());
        io.print("||Total Labor Cost: " + order.getTotalLaborCost());
        io.print("||Total Tax: " + order.getTotalTax().setScale(2, RoundingMode.HALF_UP));
        io.print("||Total Cost: " + order.getTotalCost().setScale(2, RoundingMode.HALF_UP));
        io.print("*********************************");
        while (keepGoing) {
            String yesOrNo = io.readString("Are you sure you want to edit this order? (y/n)");
            if (yesOrNo.toLowerCase().contains("y")) {
                return true;
            } else if (yesOrNo.toLowerCase().contains("n")) {
                return false;
            } else {
                io.print("Invalid input, please try again.");
            }
        }
        return false;
        
    }
     
    public int displayEditElement(Order order) {
        io.print("1) Order Date");
        io.print("2) Customer Name");
        io.print("3) State");
        io.print("4) Product");
        io.print("5) Area");
        io.print("6) Exit");
        return io.readInt("Please select a field to edit.", 1, 6);
    }
    
    public void displayEditing(Order order, int selection, List<StateTax> stateList, List<Product> prodList) {
        switch (selection) {
            case 1:
                LocalDate date = io.readDate("Please enter a new DATE for this order.");
                order.setOrderDate(date);
                break;
            case 2:
                String name = io.readString("Please enter a new FULL NAME.");
                order.setClientName(name);
                break;
            case 3:
                StateTax state = promptState(stateList);
                order.setState(state.getState());

                break;
            case 4:
                Product prod = promptProduct(prodList);
                order.setProduct(prod.getProduct());
                break;
            case 5:
                BigDecimal area = io.readBigDecimal("Please enter flooring area in squarefoot.");
                order.setArea(area);
                break;
            case 6:
                break;
        }
    }
    
    
    
    //************************************************************************//
    //***************************REMOVE ORDER************************************//
    //************************************************************************//
    public LocalDate promptRemoveOrderDate() { 
        return io.readDate("Please enter a remove date? ");
    }
    public Order displayAllOrdersForRemove(List<Order> orderList) {
        int position = 1;
        int selection;
        for (Order order : orderList) {
            io.print("************" + position + "*************");
            io.print("||Client's Name:\t" + order.getClientName());
            io.print("||State:\t\t" + order.getState());
            io.print("||Product:\t\t" + order.getProduct());
            io.print("||Area:\t\t\t" + order.getArea());
            io.print("*********************************");
            position++;
        }
        selection = io.readInt("Please select the order for remove.", 1, orderList.size());
        return orderList.get(selection - 1);
    }
    public boolean confirmRemove(Order order) {
        boolean keepRunning = true;
        io.print("*********************************");
        io.print("||Order Number:\t\t" + order.getOrderNumber());
        io.print("||Client's Name:\t" + order.getClientName());
        io.print("||State:\t\t" + order.getState());
        io.print("||Tax Rate:\t\t" + order.getStateTax());
        io.print("||Product:\t\t" + order.getProduct());
        io.print("||Area in squarefeet:\t" + order.getArea());
        io.print("||Material Cost:\t$" + order.getMaterialCost());
        io.print("||Labor Cost:\t\t$" + order.getLaborCost());
        io.print("||Total Material Cost: \t$" + order.getTotalMaterialCost());
        io.print("||Total Labor Cost: \t$" + order.getTotalLaborCost());
        io.print("||Total Tax:\t\t$" + order.getTotalTax().setScale(2, RoundingMode.HALF_UP));
        io.print("||Total: \t\t$" + order.getTotalCost().setScale(2, RoundingMode.HALF_UP));
        io.print("*********************************");
        while (keepRunning) {
            String ans = io.readString("Are you sure you want to remove this order? (y/n)");
            if (ans.toLowerCase().contains("y")) {
                return true;
            } else if (ans.toLowerCase().contains("n")) {
                return false;
            } else {
                io.print("Invalid input, please try again.");
            }
        }
        return false;
    }
    
    
    //************************************************************************//
    //***************************SAVE ORDER************************************//
    //************************************************************************//
    public int displaySaveCurrentWork() {
        io.print("Do you want to save your work? Yes = 1, No = 2");
        
        return io.readInt("Please select from the above choices.", 1, 2);
    }
    
    
    
    //************************************************************************//
    //***************************EXIT PROGRAM BANNER************************************//
    //************************************************************************//
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    
    
    //************************************************************************//
    //***************************UNKNOWN COMMAND!! BANNER************************************//
    //************************************************************************//
    public void displayUnknownCommand() {
        io.print("Unknown Command!!!");
    }
    
    //************************************************************************//
    //***************************SWITCH MODE************************************//
    //************************************************************************//
     public boolean showModeSelection() {
        boolean keepRunning = true;
        while (keepRunning) {
            int mode = io.readInt("Would you like production(1) or training(2) mode?", 1, 2);
            if (mode == 1) {
                io.print("Welcome to production mode.");
                return true;
            } else if(mode ==2){
                io.print("Welcome to training mode.");
                return true;
            }
        }
        return false;
    }
}
