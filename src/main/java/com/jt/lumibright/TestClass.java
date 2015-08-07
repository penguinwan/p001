/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jt.lumibright;

/**
 *
 * @author superman
 */
public class TestClass {
    public static void main(String[] args) {
        int seconds = 7263;
        
        long hour = seconds / (60 * 60);
        seconds = Math.round(seconds % (60 * 60));
        long minutes = seconds / (60);
        seconds = Math.round(seconds % (60));
        long sss = seconds;
        
        System.out.println("hour "+hour+" minute "+minutes+" second "+sss);

    }
}
