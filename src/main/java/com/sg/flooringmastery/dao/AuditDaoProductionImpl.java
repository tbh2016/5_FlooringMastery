/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.service.PersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author THUAN HUYNH
 */
public class AuditDaoProductionImpl implements AuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";
   
    public void writeAuditEntry(String entry) throws PersistenceException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));            ////////UNIVERSAL WRITE AUDIT FOR AOP, CAN USE ON OTHER CLASSES, VERY FLEXIBLE
        } catch (IOException e) {
            throw new PersistenceException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
