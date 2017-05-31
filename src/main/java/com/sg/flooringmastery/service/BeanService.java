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
public interface BeanService {
    OrderDao switchOrderBean(boolean isProduction, ApplicationContext ctx);

//    UniqueIdDao switchIdBean(boolean isProduction, ApplicationContext ctx);
}
