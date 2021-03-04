package com.robo.gabi.robogabi;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootApplication
public class RoboGabiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoboGabiApplication.class, args);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luizao\\Pictures\\chromedriver.exe");
    }

}
