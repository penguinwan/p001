/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jt.lumibright;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author superman
 */
public class State {

    public String part;
    public String mode;

    public State() {
        load();
    }

    private void load() {
        System.out.println("loading state...");
        Properties prop = new Properties();

        try {
            InputStream inputStream
                    = getClass().getClassLoader().getResourceAsStream("state.properties");

            if (inputStream == null) {
                System.out.println("Failed to load state. [state.properties]");
                return;
            }

            prop.load(inputStream);
            part = prop.getProperty("part");
            mode = prop.getProperty("mode");
        } catch (IOException e) {
            System.out.println("Failed to load state.");
        }
    }

    public void save() {
        System.out.println("saving state...");
        try {
            URL stateUrl = getClass().getClassLoader().getResource("state.properties");
            FileOutputStream fos = new FileOutputStream(new File(stateUrl.toURI()));

            Properties prop = new Properties();
            prop.put("part", part);
            prop.put("mode", mode);

            prop.store(fos, null);
        } catch (FileNotFoundException ex) {
            System.out.println("state.properties not found.");
        } catch (URISyntaxException ex) {
            System.out.println("state.properties not found.");
        } catch (IOException ex) {
            System.out.println("error storing state.properties.");
        }
    }

}
