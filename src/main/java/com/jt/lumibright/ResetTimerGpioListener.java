/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jt.lumibright;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author superman
 */
public class ResetTimerGpioListener implements GpioPinListenerDigital {

    private Controller controller;

    public ResetTimerGpioListener(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        // display pin state on console
        if (event.getState().equals(PinState.HIGH)) {
            System.out.println("reset timer pin high...");
            controller.reset();
        }
    }

}
