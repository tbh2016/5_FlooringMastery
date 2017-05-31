/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.flooringmastery.controller.controller;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderDaoImplProduction;
import com.sg.flooringmastery.dao.OrderDaoImplTraining;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.PersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yingy
 */
public class OrderDaoUnitTest {
    OrderDao orderDao;
    
    public OrderDaoUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        orderDao = (OrderDao)ctx.getBean("orderDaoProd"); 
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
//    @Test
//    public void OrderTest_AddandGetforProduction()throws PersistenceException {
//        Order order = new Order(3);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Sam");
//        order.setState("OH");
//        order.setStateTax(BigDecimal.valueOf(6.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("wood");
//        order.setArea(BigDecimal.valueOf(500));                 //////ADDS/SETS UP THE ORDER
//        order.setMaterialCost(BigDecimal.valueOf(60));
//        order.setLaborCost(BigDecimal.valueOf(800));
//        order.setTotalMaterialCost(BigDecimal.valueOf(600));
//        order.setTotalLaborCost(BigDecimal.valueOf(450));
//        order.setTotalTax(BigDecimal.valueOf(250));
//        order.setTotalCost(BigDecimal.valueOf(300));
//        
//        
//        orderDao.addOrder(order.getOrderNumber(), order); 
//        
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);
//    }
//    
//    @Test
//    public void OrderTest_AddandGetforTraining()throws PersistenceException {
//        Order order = new Order(4);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Bob");
//        order.setState("PA");
//        order.setStateTax(BigDecimal.valueOf(8.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("steel");
//        order.setArea(BigDecimal.valueOf(400));                 //////ADDS/SETS UP THE ORDER
//        order.setMaterialCost(BigDecimal.valueOf(50));
//        order.setLaborCost(BigDecimal.valueOf(650));
//        order.setTotalMaterialCost(BigDecimal.valueOf(555));
//        order.setTotalLaborCost(BigDecimal.valueOf(444));
//        order.setTotalTax(BigDecimal.valueOf(222));
//        order.setTotalCost(BigDecimal.valueOf(311));
//        
//        
//        orderDao.addOrder(order.getOrderNumber(), order);
//        
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);
//    }
//
////****************************************************************************//    
//    @Test
//    public void OrderTest_EditforProduction()throws PersistenceException {
//        Order order = new Order(3);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Sam");
//        order.setState("OH");
//        order.setStateTax(BigDecimal.valueOf(6.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("wood");
//        order.setArea(BigDecimal.valueOf(500));                    //////ADDS/SETS UP THE ORDER                 
//        order.setMaterialCost(BigDecimal.valueOf(60));
//        order.setLaborCost(BigDecimal.valueOf(800));
//        order.setTotalMaterialCost(BigDecimal.valueOf(600));
//        order.setTotalLaborCost(BigDecimal.valueOf(450));
//        order.setTotalTax(BigDecimal.valueOf(250));
//        order.setTotalCost(BigDecimal.valueOf(300));
//        
//        
//        
//        orderDao.editOrder(order.getOrderNumber(),order);           ////Edit the order, first it removes the order based on the hashmap ID, than replaces it based on the 
//                                                                     //on the Hashmap ID and Object type
//        
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);
//    }
//    
//    @Test
//    public void OrderTest_EditforTraining()throws PersistenceException {
//        Order order = new Order(4);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Bob");
//        order.setState("PA");
//        order.setStateTax(BigDecimal.valueOf(8.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("steel");
//        order.setArea(BigDecimal.valueOf(400));                 //////ADDS/SETS UP THE ORDER
//        order.setMaterialCost(BigDecimal.valueOf(50));
//        order.setLaborCost(BigDecimal.valueOf(650));
//        order.setTotalMaterialCost(BigDecimal.valueOf(555));
//        order.setTotalLaborCost(BigDecimal.valueOf(444));
//        order.setTotalTax(BigDecimal.valueOf(222));
//        order.setTotalCost(BigDecimal.valueOf(311));
//        
//        OrderDao orderDao = new OrderDaoImplTraining();           //////////////INSTANTIATION OF DAO, OrderDaoImplTraining
//        orderDao.editOrder(order.getOrderNumber(), order);         ////Edit the order, first it removes the order based on the hashmap ID, than replaces it based on the 
//                                                                     //on the Hashmap ID and Object type
//        
//                
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);
//    }
//    //*************************************************************************//
//    
//    @Test
//    public void OrderTest_RemoveforProduction()throws PersistenceException {
//        Order order = new Order(3);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Sam");
//        order.setState("OH");
//        order.setStateTax(BigDecimal.valueOf(6.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("wood");
//        order.setArea(BigDecimal.valueOf(500));                    //////ADDS/SETS UP THE ORDER                 
//        order.setMaterialCost(BigDecimal.valueOf(60));
//        order.setLaborCost(BigDecimal.valueOf(800));
//        order.setTotalMaterialCost(BigDecimal.valueOf(600));
//        order.setTotalLaborCost(BigDecimal.valueOf(450));
//        order.setTotalTax(BigDecimal.valueOf(250));
//        order.setTotalCost(BigDecimal.valueOf(300));
//        
//        
//        OrderDao orderDao = new OrderDaoImplProduction();           //////////////INSTANTIATION OF DAO, OrderDaoImplProduction
//        orderDao.addOrder(order.getOrderNumber(), order);           ////ADD ORDER
//        orderDao.removeOrder(order.getOrderNumber());               ////REMOVE ORDER
//        
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(getHashIdtoGetObjectInfo, null);              ////COMPARE DTO TO DAO TO SEE IF EQUALS
//    }
//    
//    @Test
//    public void OrderTest_RemoveforTraining()throws PersistenceException {
//        Order order = new Order(4);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Bob");
//        order.setState("PA");
//        order.setStateTax(BigDecimal.valueOf(8.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("steel");
//        order.setArea(BigDecimal.valueOf(400));                 //////ADDS/SETS UP THE ORDER
//        order.setMaterialCost(BigDecimal.valueOf(50));
//        order.setLaborCost(BigDecimal.valueOf(650));
//        order.setTotalMaterialCost(BigDecimal.valueOf(555));
//        order.setTotalLaborCost(BigDecimal.valueOf(444));
//        order.setTotalTax(BigDecimal.valueOf(222));
//        order.setTotalCost(BigDecimal.valueOf(311));
//        
//        OrderDao orderDao = new OrderDaoImplTraining();           //////////////INSTANTIATION OF DAO, OrderDaoImplTraining
//        orderDao.addOrder(order.getOrderNumber(), order);         ////ADD ORDER
//        orderDao.removeOrder(order.getOrderNumber());             ////REMOVE ORDER
//                
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(null, getHashIdtoGetObjectInfo);            ////COMPARE DTO TO DAO TO SEE IF EQUALS
//    }
//    //*************************************************************************//
//    
//    @Test
//    public void OrderTest_SaveCurrentWorkforProduction()throws PersistenceException {
//        Order order = new Order(3);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Sam");
//        order.setState("OH");
//        order.setStateTax(BigDecimal.valueOf(6.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("wood");
//        order.setArea(BigDecimal.valueOf(500));                    //////ADDS/SETS UP HALF THE ORDER                 
////        order.setMaterialCost(BigDecimal.valueOf(60));
////        order.setLaborCost(BigDecimal.valueOf(800));
////        order.setTotalMaterialCost(BigDecimal.valueOf(600));
////        order.setTotalLaborCost(BigDecimal.valueOf(450));
////        order.setTotalTax(BigDecimal.valueOf(250));
////        order.setTotalCost(BigDecimal.valueOf(300));
//        
//        
//        
//        orderDao.saveWork(order.getOrderNumber(), order);           ////ADD ORDER
//        
//        
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);              ////COMPARE DTO TO DAO TO SEE IF EQUALS
//    }
//    
//    @Test
//    public void OrderTest_SaveCurrentWorkforTraining()throws PersistenceException {
//        Order order = new Order(4);                     //////////////INSTANTIATION OF DTO
//        order.setOrderDate(LocalDate.now());
//        order.setClientName("Bob");
//        order.setState("PA");
//        order.setStateTax(BigDecimal.valueOf(8.25));
//        //Your choice maybe, but in the SWG material we had
////        order.setStateTax(new BigDecimal("6.25"));
//        order.setProduct("steel");
//        order.setArea(BigDecimal.valueOf(400));                 //////ADDS/SETS UP HALF THE ORDER
////        order.setMaterialCost(BigDecimal.valueOf(50));
////        order.setLaborCost(BigDecimal.valueOf(650));
////        order.setTotalMaterialCost(BigDecimal.valueOf(555));
////        order.setTotalLaborCost(BigDecimal.valueOf(444));
////        order.setTotalTax(BigDecimal.valueOf(222));
////        order.setTotalCost(BigDecimal.valueOf(311));
//        
//        OrderDao orderDao = new OrderDaoImplTraining();           //////////////INSTANTIATION OF DAO, OrderDaoImplTraining
//        orderDao.addOrder(order.getOrderNumber(), order);         ////ADD ORDER
//        
//                
//        Order getHashIdtoGetObjectInfo = orderDao.getOrder(order.getOrderNumber());
//        
//        assertEquals(order, getHashIdtoGetObjectInfo);            ////COMPARE DTO TO DAO TO SEE IF EQUALS
//    }
//    //*************************************************************************//
    
}
