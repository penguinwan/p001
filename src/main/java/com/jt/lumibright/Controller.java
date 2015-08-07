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
public class Controller {

    private Frame frame;

    public Controller(Frame frame) {
        this.frame = frame;
    }
    
    public void lineDown() {
        System.out.println("controller set line down...");
        frame.lineDown();
    }
    
    public void stopTimer() {
        System.out.println("controller stop timer...");
        frame.stopTimer();
    }
    
    public void reset() {
        System.out.println("controller reset...");
        frame.resetTimer();
    }
}
