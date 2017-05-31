/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.UniqueIdDao;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author yingy
 */
public class BeanServiceImpl implements BeanService{
     @Override
    public OrderDao switchOrderBean(boolean isProduction, ApplicationContext cxt) {
        if(isProduction){
            return cxt.getBean("orderDaoProd", OrderDao.class);
        } else {
            return cxt.getBean("orderDaoTrain", OrderDao.class);
        }
    }
    
//    @Override
//    public UniqueIdDao switchIdBean(boolean isProduction, ApplicationContext cxt){
//        if(isProduction){
//            return cxt.getBean("idDao", UniqueIdDao.class);
//        } else{
//            return cxt.getBean("idDaoTraining", UniqueIdDao.class);
//        }
//    }
}
