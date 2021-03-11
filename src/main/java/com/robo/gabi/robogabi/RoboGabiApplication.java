package com.robo.gabi.robogabi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robo.gabi.robogabi.entity.Roupa;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@SpringBootApplication
public class RoboGabiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoboGabiApplication.class, args);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luizao\\Pictures\\chromedriver.exe");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        List<Stem> stems = new ArrayList<>();
        WebDriver chromeDriver = new ChromeDriver(options);
        try {
            for (int i = 1; i <=1278; i++ ) {
                System.out.println("Página -> " + i);
                chromeDriver.get("https://remixpacks.ru/page/" + i + "/");
                List<WebElement> musicas = chromeDriver.findElements(By.className("blogstems"));
                musicas.forEach(musica -> {
                    String info = musica.findElement(By.className("titlestems")).getText();
                    Stem stem = new Stem();
                    stem.setGenre(musica.findElement(By.className("genrescomp")).getText());
                    try {
                        stem.setArtist(info.split(" – ")[0]);
                        stem.setName(info.split(" – ")[1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                        stem.setName(info);
                        stem.setArtist(info);
                    }
                    stems.add(stem);
                });
            }
        } finally {
            try {
                Writer writer = new FileWriter("C:\\Users\\Luizao\\Pictures\\a.json");
                gson.toJson(stems, writer);
                writer.flush(); //flush data to file   <---
                writer.close(); //close write
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
