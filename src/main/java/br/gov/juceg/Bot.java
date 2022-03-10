package br.gov.juceg;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;


public class Bot {

    public static void main(String[] args) throws InterruptedException {

        while (LocalDateTime.now().isBefore(LocalDateTime.of(2021, 12, 31, 23, 59, 59))) {
            System.out.println(LocalDateTime.now());
            Thread.sleep(1000);
        }

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\juceg\\Downloads\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 12);
        driver.get("https://www.juceg.go.gov.br/administrator/");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mod-login-username"))).sendKeys("nicolas-rcr");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mod-login-password"))).sendKeys("nicolas2001");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#form-login > fieldset > div:nth-child(4) > div > div > button"))).click();

        //ir ate o menu da tabela de preço
        driver.get("https://www.juceg.go.gov.br/administrator/index.php?option=com_menus&view=items&menutype=atuacao");
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content > div > div > div.row-fluid > div.span3 > div > div > div > ul:nth-child(4) > li:nth-child(1) > a > span.j-links-link"))).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menuList > tbody > tr:nth-child(1) > td:nth-child(2) > a"))).click();

        //Despublicando a atual e publicando a nova

        String publicado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(4) > td:nth-child(3) > a > span"))).getAttribute("class");

        while (publicado.equals("icon-publish")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(4) > td:nth-child(3) > a > span"))).click();
            Thread.sleep(2000);
            driver.navigate().refresh();
            publicado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(4) > td:nth-child(3) > a > span"))).getAttribute("class");
        }
        String despublicado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(5) > td:nth-child(3) > a > span"))).getAttribute("class");

        while (!despublicado.equals("icon-publish")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(5) > td:nth-child(3) > a > span"))).click();
            Thread.sleep(2000);
            driver.navigate().refresh();
            despublicado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#itemList > tbody > tr:nth-child(5) > td:nth-child(3) > a > span"))).getAttribute("class");
        }
        System.out.println();
    }
}
