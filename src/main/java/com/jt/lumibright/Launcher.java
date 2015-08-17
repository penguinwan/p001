package com.jt.lumibright;

import com.jt.lumibright.Config;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author superman
 */
public class Launcher {

    public static void main(String[] args) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("launching...");

                Config config = new Config();
                State state = new State();
                Frame frame = new Frame(state);
                frame.setupFontSize(config.lineDownTextSize,
                        config.partTextSize,
                        config.modeTextSize,
                        config.timerTextSize,
                        config.legendTextSize,
                        config.editingTextSize);
                frame.setPart(state.part);
                frame.setMode(state.mode);
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

                Controller controller = new Controller(frame);

                try {
                    boolean disableGpio = false;
                    String skipGpio = System.getProperty("skipGpio");
                    if (skipGpio != null && skipGpio.equalsIgnoreCase("true")) {
                        disableGpio = true;
                    }

                    if (!disableGpio) {
                        System.out.println("setting up gpio factory...");
                        GpioController gpio = GpioFactory.getInstance();

                        // line down pin 04
                        System.out.println(String.format("line down pin to %s...", config.lineDownPin));
                        final GpioPinDigitalInput lineDownPin = gpio.provisionDigitalInputPin(
                                RaspiPin.getPinByName(
                                        config.lineDownPin),
                                PinPullResistance.PULL_DOWN);
                        lineDownPin.setDebounce(config.debounceIntervalMilisecond);
                        lineDownPin.addListener(new LineDownGpioListener(controller));

                        // reset timer pin 05
                        System.out.println(String.format("reset timer pin to %s...", config.resetPin));
                        final GpioPinDigitalInput resetPin = gpio.provisionDigitalInputPin(
                                RaspiPin.getPinByName(
                                        config.resetPin),
                                PinPullResistance.PULL_DOWN);
                        resetPin.setDebounce(config.debounceIntervalMilisecond);
                        resetPin.addListener(new ResetTimerGpioListener(controller));
                    }

                    System.out.println("frame is ready...");
                } catch (Exception ex) {
                    System.out.println("Error setting up GPIO...");
                    ex.printStackTrace();
                }
            }          
        });

    }

}
