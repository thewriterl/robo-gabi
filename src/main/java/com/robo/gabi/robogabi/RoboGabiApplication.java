package com.robo.gabi.robogabi;

import com.robo.gabi.robogabi.entity.Roupa;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
        System.setProperty("webdriver.chrome.driver", "/Users/paulo/Downloads/chromedriver");

        WebDriver chromeDriver = new ChromeDriver();
        try {
            //vai pra farfetch na marca da burberry
            chromeDriver.get("https://farfetch.com/br/shopping/men/burberry/items.aspx");
            //Pegar a lista de produtos pela UL
            List<WebElement> elements = chromeDriver.findElements(By.cssSelector("ul"));
            Optional<WebElement> listaDeProdutos = elements.stream()
                    .filter(elemento -> elemento.getAttribute("data-testid") != null)
                    .filter(elemento -> elemento.getAttribute("data-testid").equals("product-card-list"))
                    .findFirst();
            // pegar os produtos
            List<WebElement> produtos = listaDeProdutos.get().findElements(By.tagName("li"));
            //criamos uma lista de links
            List<String> listadelinks = new ArrayList<>();

            //pra cada link, faça isso ->
            produtos.forEach(produto -> {
                //pegar o link pela tag a, e buscar o HREF
                String link = produto.findElement(By.tagName("a")).getAttribute("href");
                // adiciona o link da linha acima na lista de links
                listadelinks.add(link);
            });

            //pra cada link na lista de links, faça isso ->
            listadelinks.forEach(link -> {
                // entrar no link
                chromeDriver.get(link);
                // pegar o nome da peça
                List<WebElement> nome = chromeDriver.findElements(By.cssSelector("h1 > span"));
                // pegar o preço
                List<WebElement> preco = chromeDriver.findElements(By.cssSelector("div > div"));
                //transforma o tipo do preço
                BigDecimal precoFinal =  new BigDecimal(preco.stream().filter(o -> o.getAttribute("data-tstid") != null)
                        .filter(o -> o.getAttribute("data-tstid").equals("priceInfo-priceInfo"))
                        .findFirst().get().getText().split("\n")[0].replace("R$", "").replace(".", "").replace(" ", "") + ".00");

                //colocar em roupa
                Roupa roupa = new Roupa();
                roupa.setNome(nome.get(1).getText());
                roupa.setPreco(precoFinal);
                System.out.println("roupa -> " + roupa.getNome() + "\n preco ->" + roupa.getPreco());
            });
            System.out.printf("jhgfhjg");
        } finally {

        }
    }

}
