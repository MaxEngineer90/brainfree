package eu.brainfree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Max on 11.12.2021
 * @project brainfree
 * @package com.brainfree
 **/

@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class BrainFreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrainFreeApplication.class, args);
    }
}
