package com.mycompany.mavenproject1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LotteryResultsScraper {
    public static void main(String[] args) {
        // Path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");


        // Initialize Chrome driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        // Open the website
        driver.get("https://www.nlb.lk/results/govisetha");

        // Wait for JavaScript execution and fetch the page source
        try {
            Thread.sleep(5000);  // Wait 5 seconds to allow JS execution
        } catch (InterruptedException e) {
            
        }

        
        List<WebElement> rows = driver.findElements(By.cssSelector("table.w0 tbody tr"));
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            String drawDate = row.findElement(By.cssSelector("td:first-child")).getText();
            List<WebElement> numbers = row.findElements(By.cssSelector("ol li"));
            StringBuilder numbersText = new StringBuilder();
            for (int j = 0; j < numbers.size(); j++) {
                WebElement number = numbers.get(j);
                numbersText.append(number.getText()).append(" ");
            }

            System.out.println();
            System.out.println("Draw Date: " + drawDate);
            System.out.println("Numbers: " + numbersText.toString().trim());
            System.out.println("-------------------------------");

            // Print the first element
            if (i == 0) {
                break;
            }
        }

        // Close driver
        driver.quit();
    }
}


