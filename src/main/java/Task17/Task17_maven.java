package Task17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task17_maven {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
            System.out.println("Step 1: Website opened: " + driver.getTitle());
            //Thread.sleep(5000);

            System.out.println("Step 2: Searching for the 'Guest log in' button");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Guest log in']")));

            System.out.println("Step 3: The 'Guest log in' button found, performing click.");
            loginButton.click();
           // Thread.sleep(5000);

            System.out.println("Step 4: Searching for the element from  border menu with text 'Garage'");
            WebElement menuItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[routerlink=\"garage\"]")));
            System.out.println("Element found: " + menuItem1.getText());

            System.out.println("Step 5: Searching for the element from  border menu with text 'Fuel expenses'");
            WebElement menuItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[routerlink=\"expenses\"]")));
            System.out.println("Element found: " + menuItem2.getText());

            System.out.println("Step 6: Searching for the element from  border menu with text 'Instructions'");
            WebElement menuItem3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[routerlink=\"instructions\"]")));
            System.out.println("Element found: " + menuItem3.getText());

            System.out.println("Step 7: Searching for the element from  border menu with text 'Log out'");
            WebElement menuItem4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn-link.text-danger.btn-sidebar.sidebar_btn")));
            System.out.println("Element found: " + menuItem4.getText());

        } catch (Exception e) {
            System.out.println("Test failed. Error:  " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
