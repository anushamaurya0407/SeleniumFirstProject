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
        // Your LT credentials
        String username = "anushamlambdatest";
        String accessKey = "LT_MjvkWeHF6IDWK1PNtWAY26zhvNsyMhFw68Igqxtluv9iz8U";

        // Minimal capability setup (like ChromeDriver but on LT cloud)
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("platformName", "Windows 11");

        // LT specific options
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "First Test Build");
        ltOptions.put("name", "First Google Test");
        ltOptions.put("selenium_version", "4.21.0");
        caps.setCapability("LT:Options", ltOptions);

        // Replace ChromeDriver with RemoteWebDriver (LT cloud hub)
        WebDriver driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
                caps
        );

        try {
            // Step 1: Open Google
            driver.get("https://www.google.com");

            // Step 2: Find the search box (Google uses 'q' instead of your DuckDuckGo locator)
            WebElement searchBox = driver.findElement(By.name("q"));

            // Step 3: Type "amazon.in" and press Enter
            searchBox.sendKeys("amazon.in");
            searchBox.sendKeys(Keys.RETURN);

            // Step 4: Wait for 5 seconds to see results
            Thread.sleep(5000);

            // Mark test as passed in LT
            ((RemoteWebDriver) driver).executeScript("lambda-status=passed");
        } catch (Exception e) {
            ((RemoteWebDriver) driver).executeScript("lambda-status=failed");
            throw e;
        } finally {
            // Step 5: Close browser
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
