/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.UniqueIdDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.BeanService;
import com.sg.flooringmastery.service.PersistenceException;
import com.sg.flooringmastery.service.ServiceLayer;
import com.sg.flooringmastery.ui.View;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author THUAN HUYNH
 */
public class controller {
    //************************************************************************//
    //***************************INJECTION AND CONSTRUCTOR************************************//
    //************************************************************************//
    ServiceLayer service;
    View view;
    BeanService beanService;

    public controller(ServiceLayer service, View view, BeanService beanService) {
        this.service = service;
        this.view = view;
        this.beanService = beanService;
    }

    
    //************************************************************************//
    //***************************RUN METHOD******************************************//
    //************************************************************************//
    public void run(ApplicationContext cxt) {             //run method that uses another method on the bottom, which orchestrates whatever is in the view
        boolean keepGoing = true;
        boolean modeSwitch = false;
        boolean isProductionMode = true;
        int menuSelection = 0;
        try {        //The try statement lets you test a block of code for errors.
            //The catch statement lets you handle the error.
            isProductionMode = view.showModeSelection();
            switchMode(isProductionMode, cxt);
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exitMessage();
                        keepGoing = false;
                        break;
                    case 6:
                        modeSwitch = true;
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() throws PersistenceException { ///this method is 
        return view.printMenuAndGetSelection();                 ///connected to the menu
    }

    private void switchMode(boolean isProductionMode, ApplicationContext cxt){
        OrderDao orderDao = beanService.switchOrderBean(isProductionMode, cxt);
//        UniqueIdDao idDao = beanService.switchIdBean(isProductionMode, ac);
        service.setDaos(orderDao);
    }
    //************************************************************************//
    //****************SWITCH METHODS WITHIN THE above RUN METHOD************************//
    //************************************************************************//
    private void displayOrders() throws PersistenceException {
        LocalDate date = view.promptDispayOrderDate();
        service.loadAllDates(date);
        
        List<Order> orderList = service.getAllOrders(date);
        view.displayAllOrders(orderList);

    }
    
    private void addOrder() throws PersistenceException {
         
        
        LocalDate date = view.displayAskForOrderDate();//////////////////
//        service.addOrder(0, order)
        
        String name = view.displayAskForFullName();///////////
        
        List<StateTax> taxList = service.getAllStateTax();//////////////
        
        int outputStateTax = view.displayStateTaxList(taxList);
        
        BigDecimal area = view.displayAskForArea();/////////////
        List<Product> productList = service.getAllProducts();
        int outputProductChoice = view.displayProductList(productList);///////////////////
        
        String product = productList.get(outputProductChoice-1).getProduct();
        BigDecimal matCost = productList.get(outputProductChoice-1).getMaterialCostPerSQFT();
        BigDecimal labCost = productList.get(outputProductChoice-1).getLaborCostPerSQFT();
        
        String state = taxList.get(outputStateTax-1).getState();
        BigDecimal taxRate = taxList.get(outputStateTax-1).getTaxRate();
               
        BigDecimal TMC = service.totalMaterialCost(area, matCost);
        BigDecimal TLC = service.totalLaborCost(area, labCost);
        BigDecimal TT = service.totalTax(TMC, TLC, taxRate);
        BigDecimal TC = service.totalCost(TMC, TLC, TT);
        
//        int orderNumber = service.getOrderNumber(service.getAllAvailableOrders());
        int orderNumber = view.askForOrderNumber();

        Order order = service.generateOrder(orderNumber,date, name, state, taxRate, product, area, matCost, labCost, TMC, TLC, TT, TC);
        boolean doAdd = view.confirmAdd(order);
        if(doAdd){
            service.addOrder(order);
        }
        service.checkEmpty(date);        
//        List<Order> orderList = view.displayOrderInvoice(orderNumber,date, name, state, taxRate, product, area, matCost, labCost, TMC, TLC, TT, TC);
//        int yesOrNo = view.displaySaveCurrentWork();  
//        int orderNum = service.getOrderNumber();
    }
    
//public void displayOrderInvoice(LocalDate date, String name, String state, BigDecimal stateTax, String product, BigDecimal area, BigDecimal matCost, 
//        BigDecimal labCost, BigDecimal totalMatCost, BigDecimal totalLaborCost, BigDecimal totalTax, BigDecimal totalCost) {
//        io.print("Order number: " );
//        io.print("Order date: " + date);   
//        io.print("Name: " + name);    
//        io.print("State: " + state);
//        io.print("State Tax: " + stateTax);
//        io.print("Product: " + product);   
//        io.print("Area: " + area);    
//        io.print("Material Cost: " + matCost);
//        io.print("Labor Cost: " + labCost);
//        io.print("Total Material Cost: " + totalMatCost);
//        io.print("Total Labor Cost: " + totalLaborCost);   
//        io.print("Total Tax: " + totalTax);    
//        io.print("Total Cost: " + totalCost);
//    }

    private void editOrder() throws PersistenceException {
        List<Product> prodList = service.getAllProducts();
        List<StateTax> stateList = service.getAllStateTax();
        
        LocalDate date = view.promptEditOrderDate();
        service.loadAllDates(date);
        
        List<Order> orderList = service.getAllOrders(date);
        Order order = view.displayAllOrdersForEditing(orderList);
        boolean yesOrNoEdit = view.confirmEdit(order);
        if (yesOrNoEdit) {
            order = service.editOrder(order);
            int choice = view.displayEditElement(order);
            view.displayEditing(order, choice, stateList, prodList);
            
            order = service.updatedGenerateOrder(order.getOrderDate(),
                    order.getArea(),
                    order.getClientName(),
                    service.getProductByName(order.getProduct()),
                    service.getStateByName(order.getState()),
                    order.getOrderNumber());
            
            service.loadAllDates(order.getOrderDate());
            service.updateOrder(order);
        }
        service.checkEmpty(date);
    }

    private void removeOrder() throws PersistenceException {
        LocalDate date = view.promptRemoveOrderDate();
        service.loadAllDates(date);
        List<Order> orderList = service.getAllOrders(date);
        Order order = view.displayAllOrdersForRemove(orderList);
        boolean doRemove = view.confirmRemove(order);
        if(doRemove){
            service.removeOrder(order);
        }
        service.checkEmpty(date);
    }


    private void exitMessage() throws PersistenceException {
        view.displayExitBanner();
    }

    private void unknownCommand() throws PersistenceException {
        view.displayUnknownCommand();
    }
}

