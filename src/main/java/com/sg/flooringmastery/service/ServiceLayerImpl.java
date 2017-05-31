/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.StateTaxDao;
import com.sg.flooringmastery.dao.UniqueIdDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THUAN HUYNH
 */
public class ServiceLayerImpl implements ServiceLayer {

    private OrderDao orderDaoProd;
    private OrderDao orderDaoTrain;
    private UniqueIdDao uniqueIdProd;
    private UniqueIdDao uniqueIdTrain;
    private ProductDao productDao;
    private StateTaxDao stateTaxDao;
    private AuditDao auditDao;

    public ServiceLayerImpl(OrderDao orderDaoProd, OrderDao orderDaoTrain, UniqueIdDao uniqueIdProd, UniqueIdDao uniqueIdTrain, ProductDao productDao, StateTaxDao stateTaxDao, AuditDao auditDao) {
        this.orderDaoProd = orderDaoProd;
        this.orderDaoTrain = orderDaoTrain;
        this.uniqueIdProd = uniqueIdProd;
        this.uniqueIdTrain = uniqueIdTrain;
        this.productDao = productDao;
        this.stateTaxDao = stateTaxDao;
        this.auditDao = auditDao;
    }

    @Override
    public void addOrder(Order order) throws PersistenceException {
        orderDaoProd.addOrder(order);
    }

    @Override
    public Order getOrder(Order order) throws PersistenceException{
        return orderDaoProd.getOrder(order);
    }

    @Override
    public Order editOrder(Order order) throws PersistenceException {
        return orderDaoProd.editOrder(order);
    }
    
    @Override
    public void removeOrder(Order order) throws PersistenceException{
        orderDaoProd.removeOrder(order);
    }
    
//    @Override
//    public Order saveWork(int orderNumber, Order order) throws PersistenceException{
//        int yesOrNo = 1;
//        if(yesOrNo == 1){
//            return orderDaoProd.saveWork(orderNumber, order);
//        }else{
//            return null;
//        }
//    }


    ///Ask someone if i need addOrder, getOrder, editOrder for orderDaoTraining?
    
    @Override
    public List<StateTax> getAllStateTax() throws PersistenceException{
        return stateTaxDao.getAllStateTax();
    }
    
    @Override
    public List<Product> getAllProducts() throws PersistenceException{
        return productDao.getAllProducts();
    }
    
    
    @Override
    public ArrayList<Order> getAllAvailableOrders() throws PersistenceException{
        return orderDaoProd.getAllAvailableOrders();
    }
    
    @Override
    public Integer getOrderNumber(ArrayList<Order> orderList) throws PersistenceException{
        return orderDaoProd.getOrderNumber(orderList);
    }
    
    
     @Override
    public void loadAllDates(LocalDate date) throws PersistenceException{
        orderDaoProd.loadAllDates(date);
    }
    
    @Override
    public List<Order> getAllOrders(LocalDate date) throws PersistenceException{
        return orderDaoProd.getAllOrders(date);
    }
    
    //************************************************************************//
    //***************************METHODS FOR ALL MONEY CALCULATIONS************************************//
    //************************************************************************//  
    @Override
    public BigDecimal totalMaterialCost(BigDecimal area, BigDecimal matCost) throws PersistenceException{
        BigDecimal totalMatCost = area.multiply(matCost);
        return totalMatCost;
    }
    @Override
    public BigDecimal totalLaborCost(BigDecimal area, BigDecimal labCost) throws PersistenceException{
        BigDecimal totalLabCost = area.multiply(labCost);
        return totalLabCost;
    }
    @Override
    public BigDecimal totalTax(BigDecimal totalMaterialCost, BigDecimal totalLaborCost, BigDecimal taxRate) throws PersistenceException{
        BigDecimal totalTax = (totalMaterialCost.add(totalLaborCost)).multiply((taxRate).multiply(new BigDecimal(".01")));
        return totalTax;
    }
    @Override
    public BigDecimal totalCost(BigDecimal totalMaterialCost, BigDecimal totalLaborCost, BigDecimal totalTax) throws PersistenceException{
        BigDecimal totalCost = totalMaterialCost.add(totalLaborCost).add(totalTax);
        return totalCost;
    }

   
    @Override
    public Order generateOrder(int orderNumber, LocalDate date, String name, String state, BigDecimal taxRate, String product, 
        BigDecimal area, BigDecimal matCost, BigDecimal labCost, BigDecimal TMC, BigDecimal TLC, BigDecimal TT, BigDecimal TC) {
        Order order = new Order(orderNumber);
        order.setOrderDate(date);
        order.setClientName(name);
        order.setState(state);
        order.setStateTax(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setMaterialCost(matCost);
        order.setLaborCost(labCost);
        order.setTotalMaterialCost(TMC);
        order.setTotalLaborCost(TLC);
        order.setTotalTax(TT);
        order.setTotalCost(TC);
        
        return order;
    
    }

    @Override
    public Order updatedGenerateOrder(LocalDate date, BigDecimal area, String name, Product prod, StateTax state, int id) throws PersistenceException {
        Order order = new Order(id);
//        if(id  < 0){
//            order.setOrderNum(uniqueIdProd.getID());
//        } else{
//            order.setOrderNum(id);
//        }
        order.setOrderDate(date);
        order.setClientName(name);
        order.setState(state.getState());
        order.setStateTax(state.getTaxRate());
        order.setProduct(prod.getProduct());
        order.setArea(area);
        order.setMaterialCost(prod.getMaterialCostPerSQFT());
        order.setLaborCost(prod.getLaborCostPerSQFT());
        order.setTotalMaterialCost(area.multiply(prod.getMaterialCostPerSQFT()));
        order.setTotalLaborCost(area.multiply(prod.getLaborCostPerSQFT()));
        BigDecimal BD100 = new BigDecimal("100");
        BigDecimal tax = (order.getStateTax().divide(BD100, 2, RoundingMode.HALF_UP));
        order.setTotalTax((order.getTotalMaterialCost().add(order.getTotalLaborCost())).multiply(tax));
        order.setTotalCost(order.getTotalLaborCost().add(order.getTotalMaterialCost().add(order.getTotalTax())));
        return order;
    }
    
    //************************************************************************//
    //***************************EDIT ORDER************************************//
    //************************************************************************//
    @Override
    public Product getProductByName(String name){
        return productDao.getProductByName(name);
    }
    @Override
    public StateTax getStateByName(String name){
        return stateTaxDao.getStateByName(name);
    }
    @Override
    public void updateOrder(Order order) throws PersistenceException{
        orderDaoProd.updateOrder(order);
    }
    @Override
    public void checkEmpty(LocalDate date) throws PersistenceException{
        orderDaoProd.checkEmpty(date);
    }
    
    
    @Override
    public void setDaos(OrderDao dao) {
        orderDaoProd = dao;
//        uniqueDao = idDao;
    }
}
