/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.service.InvalidDateException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 *
 * @author THUAN HUYNH
 */
public class UserIOImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        int num = sc.nextInt();
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        boolean isValid = false;
        double number = 0;

        while (!isValid) {
            number = getValidDouble(prompt);
            isValid = isInRangeDouble(number, min, max);
        }
        return number;
    }

    private double getValidDouble(String prompt) { //************************************************************
        double number = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(prompt);
            try {
                String input = sc.nextLine();
                number = Double.parseDouble(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input\n");
            }
        }
        return number;
    }

    private boolean isInRangeDouble(double number, double min, double max) {
        if (number < min || number > max) {
            System.out.println("Input out of range\n");
            return false;
        }
        return true;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float num = sc.nextInt();
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = 0;
        do {
            System.out.println(prompt);
            num = sc.nextFloat();

        } while (num <= min || num >= max);
        return num;
    }

    @Override
    public int readInt(String prompt) {
        int num = 0; //number to be stored from string
        boolean invalid = true; //assume input is invalid, after verification, change to false
        while (invalid) { //while invalid is true, keep looping
            System.out.println(prompt); //ask user for whatver
            String input = sc.nextLine(); //take input as String because nextInt can cause problems
            try { //TRY to do this. if one line of code in this fails, go to catch (kind of similar to if-else)
                num = Integer.parseInt(input); //try to parse (analyze) String to see if integer is present. if this works, it will be set to num. if it doesnt, go to catch{}
                invalid = false; //will only run if above line worked. change invalid to false (valid), break out of loop and go down to the return statement
            } catch (NumberFormatException e) { //catch the "thrown" type of exception if input is not an int
                System.out.println("Enter valid number."); //error message to be shown. will take in new String because the while conditions are still not changed
            }
        }
        return num; //return the true number after broken out of while loop
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num = 0;
        boolean invalid = true;
        while (invalid) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num >= min && num <= max) {
                    invalid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid number.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long num = sc.nextInt();
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num = 0;
        do {
            System.out.println(prompt);
            num = sc.nextLong();

        } while (num <= min || num >= max);
        return num;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String input = "";
        boolean isString = false;

        while (!isString) {
            input = sc.nextLine();
            if (input.length() > 0) {
                isString = true;
            } else {
                System.out.println("Please enter something");
            }
        }
        return input;
    }

    @Override
    public void enterToContinue(String prompt) {
        System.out.println(prompt);
        sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bdnum = new BigDecimal("0");
        boolean invalid = true;
        while (invalid) {
            System.out.println(prompt);
            String input = sc.nextLine();           //read String input
            
            try {
                bdnum = new BigDecimal(input);  //sets bdnum equal to a new value, if the new value from the "input" doesn't equal a number it won't convert so bdnum != new BigDecimal(input);

                invalid = false;

            } catch (Exception e) {
                System.out.println("Enter valid number.");
            }
        }
        return bdnum; //return the false number after broken out of while loop
    }

    @Override
    public LocalDate readFutureDate(String prompt) {
        LocalDate ld = LocalDate.now();
//        ld.c
        boolean invalid = true;
        while (invalid) {
            try {
                System.out.println(prompt);

                String input = sc.nextLine();

                ld = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate fd = LocalDate.now().plusDays(7);
                if (ld.isAfter(fd)) {
                    invalid = false;
                } else {
                    invalid = true;
                }
            } catch (Exception e) {
                System.out.println("Enter valid date.");
            }
        }
        return ld; //return the false number after broken out of while loop
    }
    
    @Override
    public LocalDate readDate(String prompt) {
        boolean keepRunning = true;
        LocalDate date = LocalDate.now();
        while(keepRunning){
            try{
                System.out.println(prompt);
                String userInput = sc.nextLine();
                date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                keepRunning = false;
            } catch (Exception ex){
                System.out.println("Invalid Date or format.  Please enter a date in MM/dd/yyyy format.");
            }
        }
        return date;
    }
}
