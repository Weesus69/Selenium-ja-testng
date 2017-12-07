package testngpackage;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/* T�ss� luokassa testataan dataproviderin k�ytt�� TestNG:ssa. Dataprovider on t�ss� m��ritelty samassa
 * luokassa ja sen idea on ett� samaa testi� ajetaan eri dataseteill�. T�ss� tapauksessa kolmella eri
 * datalla
 */

public class dataProviderInSameFile {


    /** Test case to verify google search box
     * @param author
     * @param searchKey
     * @throws InterruptedException
     */

    @Test(dataProvider="SearchProvider")
    public void testMethod(String author,String searchKey) throws InterruptedException{
    {
		System.setProperty("webdriver.chrome.driver","C:\\koodit\\chromedriver\\chromedriver.exe");					
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://google.com");
    	WebElement searchText = driver.findElement(By.name("q"));
        //search value in google searchbox
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue +"::::"+searchKey);
        searchText.clear();
        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
        driver.close();
    }
    }
    /**
     * @return Object[][] where first column contains 'author'
     * and second column contains 'searchKey'
     */

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "Guru99", "India" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };

    }

}