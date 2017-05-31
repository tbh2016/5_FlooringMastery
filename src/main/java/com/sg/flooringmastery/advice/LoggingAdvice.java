/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.service.PersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author THUAN HUYNH
 */
public class LoggingAdvice {
    AuditDao auditDao;
 
    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (PersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
