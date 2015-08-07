package com.jt.lumibright;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author superman
 */
public class Config {

    public int debounceIntervalMilisecond = 50;
    public int lineDownTextSize = 48;
    public int partTextSize = 48;
    public int modeTextSize = 48;
    public int timerTextSize = 48;
    public int editingTextSize = 48;
    public int legendTextSize = 15;
    public String lineDownPin;
    public String resetPin;

    public Config() {
        load();
    }

    private void load() {
        System.out.println("loading configuration file...");
        Properties prop = new Properties();

        try {
            InputStream inputStream
                    = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (inputStream == null) {
                System.out.println("Failed to load configuration file. [config.properties]");
                return;
            }

            prop.load(inputStream);
            String lineDown = prop.getProperty("line.down.text.size");
            String part = prop.getProperty("part.text.size");
            String mode = prop.getProperty("mode.text.size");
            String timer = prop.getProperty("timer.text.size");
            String editing = prop.getProperty("editing.text.size");
            String legend = prop.getProperty("legend.text.size");
            String debounce = prop.getProperty("debounce.interval.milisecond");

            lineDownTextSize = Integer.valueOf(lineDown);
            partTextSize = Integer.valueOf(part);
            modeTextSize = Integer.valueOf(mode);
            timerTextSize = Integer.valueOf(timer);
            editingTextSize = Integer.valueOf(editing);
            legendTextSize = Integer.valueOf(legend);
            debounceIntervalMilisecond = Integer.valueOf(debounce);

            lineDownPin = prop.getProperty("line.down.pin");
            resetPin = prop.getProperty("reset.pin");
        } catch (IOException e) {
            System.out.println("Failed to load configuration file.");
        } catch (NumberFormatException ex) {
            System.out.println(String.format("Failed to convert number. %s", ex.getMessage()));
        }
    }
}
