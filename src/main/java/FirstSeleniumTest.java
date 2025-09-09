import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class FirstSeleniumTest {
    public static void main(String[] args) throws Exception {
        // ✅ Fetch credentials from Jenkins pipeline environment
        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

        // Minimal capability setup
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "First Test Build");
        ltOptions.put("name", "First Google Test");
        ltOptions.put("selenium_version", "4.21.0");
        caps.setCapability("LT:Options", ltOptions);

        // Remote driver with LT hub
        WebDriver driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
                caps
        );

        try {
            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("amazon.in");
            searchBox.sendKeys(Keys.RETURN);

            Thread.sleep(5000);

            ((RemoteWebDriver) driver).executeScript("lambda-status=passed");
        } catch (Exception e) {
            ((RemoteWebDriver) driver).executeScript("lambda-status=failed");
            throw e;
        } finally {
            driver.quit();
        }
    }
}


//local
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class FirstSeleniumTest {
//    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//
//        // Step 1: Open Google
//        // driver.get("https://www.google.com");
//        // driver.get("https://www.bing.com");
//        driver.get("https://duckduckgo.com");
//
//        // Step 2: Find the search box
//        WebElement searchBox = driver.findElement(By.xpath("//*[@id='searchbox_input']"));
//
//        // Step 3: Type "amazon.in" and press Enter
//        searchBox.sendKeys("amazon.in");
//        searchBox.sendKeys(Keys.RETURN);
//
//        // Step 4: Wait for 5 seconds to see results
//        Thread.sleep(5000);
//
//        // Step 5: Close browser
//        driver.quit();
//    }
//}
