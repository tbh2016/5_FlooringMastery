/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author THUAN HUYNH
 */
public class App {
    
    public static void main(String[] args) {
        
//        UserIO myIo = new UserIOImpl();
//        View myView = new View(myIo); 
//        OrderDao orderDaoProd = new OrderDaoImplProduction();
//        OrderDao orderDaoTrain =new OrderDaoImplTraining();
//        UniqueIdDao uniqueIdProd = new UniqueIdDaoProductionImpl();
//        UniqueIdDao uniqueIdTrain = new UniqueIdDaoTrainingImpl();
//        ProductDao productDao = new ProductDaoImpl();
//        StateTaxDao stateTaxDao = new StateTaxDaoImpl();
//        AuditDao auditDao = new AuditDaoProductionImpl();
//        
//        ServiceLayer service = new ServiceLayerImpl(orderDaoProd, orderDaoTrain, uniqueIdProd, uniqueIdTrain, productDao, stateTaxDao, auditDao);
//        controller controller = new controller(service, myView);
//        controller.run();

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    controller controller = ctx.getBean("controller", controller.class);
    controller.run(ctx);
    }
    
}
