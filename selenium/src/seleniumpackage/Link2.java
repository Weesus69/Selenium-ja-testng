package seleniumpackage;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;		

public class Link2 {				
    		
    public static void main(String[] args) {									
        String baseUrl = "http://demo.guru99.com/selenium/link.html";					
		System.setProperty("webdriver.chrome.driver","C:\\koodit\\chromedriver\\chromedriver.exe");					
        WebDriver driver = new ChromeDriver();					
        		
        driver.get(baseUrl);					
        driver.findElement(By.partialLinkText("here")).click();					
        System.out.println("Title of page is: " + driver.getTitle());							
        driver.quit();			
    }		
}