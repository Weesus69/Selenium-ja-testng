package testngpackage.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import testngpackage.pages.Guru99HomePage;

import testngpackage.pages.Guru99Login;

/* Esimerkki siit‰ miten k‰ytet‰‰n POMmia eli metodit ja lokaattorit m‰‰ritell‰‰n omissa luokissaan
 * kts. testngpackage.pages.Guru99HomePage ja testngpackage.pages.Guru99Login. T‰ss‰ sitten vaan
 * p‰tkytet‰‰n testi‰ menem‰‰n eli ei muuta.
 */

public class Test99GuruLogin {

    WebDriver driver;

    Guru99Login objLogin;

    Guru99HomePage objHomePage;

    

    @BeforeTest

    public void setup(){

		System.setProperty("webdriver.chrome.driver","C:\\koodit\\chromedriver\\chromedriver.exe");					
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/V4/");

    }

    /**

     * This test case will login in http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @Test(priority=0)

    public void test_Home_Page_Appear_Correct(){

        //Create Login Page object

    objLogin = new Guru99Login(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    objLogin.loginToGuru99("mgr123", "mgr!23");

    // go the next page

    objHomePage = new Guru99HomePage(driver);

    //Verify home page

    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
}
}