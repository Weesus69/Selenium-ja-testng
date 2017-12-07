package testngpackage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Iterator;		
import java.util.Set;
import java.text.NumberFormat;

public class firsttestngfile {
    public String baseUrl = "http://demo.guru99.com/selenium/newtours/";
    String driverPath = "C:\\koodit\\chromedriver\\chromedriver.exe";
    public WebDriver driver ; 
    
    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching chrome browser"); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }  
     
  //@Test
  public void verifyHomepageTitle() {
	  String expectedTitle = "Welcome: Mercury Tours";
      String actualTitle = driver.getTitle();
      Assert.assertEquals(actualTitle, expectedTitle);
  }
  
  //@Test
  public void dateTimePicker(){
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.get("http://demo.guru99.com/selenium");
      //Find the date time picker control
      WebElement dateBox = driver.findElement(By.xpath("//form//input[@name='bdaytime']"));
      //Fill date as mm/dd/yyyy as 09/25/2013
      dateBox.sendKeys("09252013");
      //Press tab to shift focus to time field
      dateBox.sendKeys(Keys.TAB);
      //Fill time as 02:45 PM
      dateBox.sendKeys("0245PM");
  }
  
  //@Test
  public void testDAtePicker() throws Exception{
      //DAte and Time to be set in textbox
      String dateTime ="12/07/2014 2:00 PM";
      driver.manage().window().maximize();
      driver.get("http://demos.telerik.com/kendo-ui/datetimepicker/index");    
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      //button to open calendar
      WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));    
      selectDate.click();
  //button to move next in calendar
  WebElement nextLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));
  //button to click in center of calendar header
  WebElement midLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));
  //button to move previous month in calendar
  WebElement previousLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]")); 
      //Split the date time to get only the date part
      String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
      //get the year difference between current year and year to set in calander
      int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);
      midLink.click();

      if(yearDiff!=0){
          //if you have to move next year
          if(yearDiff>0){
              for(int i=0;i< yearDiff;i++){
                 System.out.println("Year Diff->"+i);
                  nextLink.click();
              }
          }

          //if you have to move previous year
          else if(yearDiff<0){
              for(int i=0;i< (yearDiff*(-1));i++){
                  System.out.println("Year Diff->"+i);
                  previousLink.click();
              }
          }
      }
      
      Thread.sleep(1000);

      //Get all months from calendar to select correct one
      List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));     
      list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();     
      Thread.sleep(1000);
      //get all dates from calendar to select correct one
      List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));     
      list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();   
      ///FOR TIME
      WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));
      //click time picker button
      selectTime.click();
      //get list of times
      List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));   
      dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];
   //select correct time
      for (WebElement webElement : allTime) {
          if(webElement.getText().equalsIgnoreCase(dateTime))
          {
              webElement.click();
          }
      }
  }

  //@Test
  public void testAlertHandling() throws Exception{
  // Alert Message handling
	
  driver.get("http://demo.guru99.com/selenium/delete_customer.php");	
  driver.findElement(By.name("cusid")).sendKeys("53920");					
  driver.findElement(By.name("submit")).submit();			
  		
  // Switching to Alert        
  Alert alert = driver.switchTo().alert();		
  		
  // Capturing alert message.    
  String alertMessage= driver.switchTo().alert().getText();		
  		
  // Displaying alert message		
  System.out.println(alertMessage);	
  Thread.sleep(5000);
  		
  // Accepting alert		
  alert.accept();		}
  
  //@Test
  public void testPopUpHandling() throws Exception{
	   driver.get("http://demo.guru99.com/popup.php");			
       driver.manage().window().maximize();		
               		
       driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();			
       		
       String MainWindow=driver.getWindowHandle();		
       		
       // To handle all new opened window.				
       Set<String> s1=driver.getWindowHandles();		
       Iterator<String> i1=s1.iterator();		
       		
       while(i1.hasNext())			
       {		
           String ChildWindow=i1.next();		          		
           if(!MainWindow.equalsIgnoreCase(ChildWindow))			
           {    		                
                   // Switching to Child window
                   driver.switchTo().window(ChildWindow);	                                                                                                           
                   driver.findElement(By.name("emailid"))
                   .sendKeys("gaurav.3n@gmail.com");                			                  
                   driver.findElement(By.name("btnLogin")).click();			
                                
			// Closing the Child Window.
                       driver.close();		
           }		
       }		
       // Switching to Parent window i.e Main Window.
           driver.switchTo().window(MainWindow);				
   }
  
  
  //@Test
  public void testlenghthOftablerowsandcolumns() throws Exception{
  driver.get("http://money.rediff.com/gainers/bsc/dailygroupa?");         
  //No.of Columns
  List <WebElement> col = driver.findElements(By.xpath(".//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
  System.out.println("No of cols are : " +col.size()); 
  //No.of rows 
  List <WebElement> rows = driver.findElements(By.xpath(".//*[@id=\"leftcontainer\"]/table/tbody/tr/td[1]")); 
  System.out.println("No of rows are : " + rows.size());
}
  
  //@Test
  public void testlgetcertainrowandcolumn() throws Exception{
  driver.get("http://money.rediff.com/gainers/bsc/daily/groupa?"); 
   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  WebElement baseTable = driver.findElement(By.tagName("table"));
  
  //To fimd third row of table
  WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
  
  String rowtext = tableRow.getText();
  System.out.println("Third row of table : "+rowtext);
  
  //to get 3rd row's 2nd column data
  WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
  String valueIneed = cellIneed.getText();
  System.out.println("Cell value is : " + valueIneed); 
}   
  
  
  @Test
  public void testgetmaximumvaluefromtable() throws Exception{
  String max;
  double m=0,r=0;
  driver.get("http://money.rediff.com/gainers/bsc/daily/groupa?");
  //No. of Columns
  List <WebElement> col = driver.findElements(By.xpath(".//*[@id=\'leftcontainer\']/table/thead/tr/th"));
  System.out.println("Total No of columns are : " +col.size());
  //No.of rows
  List <WebElement> rows = driver.findElements(By.xpath (".//*[@id=\'leftcontainer\']/table/tbody/tr/td[1]"));
  System.out.println("Total No of rows are : " + rows.size());
  for (int i =1;i<rows.size();i++)
  {    
      max= driver.findElement(By.xpath("html/body/div[1]/div[5]/table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
      NumberFormat f =NumberFormat.getNumberInstance(); 
      Number num = f.parse(max);
      max = num.toString();
      m = Double.parseDouble(max);
      if(m>r)
       {    
          r=m;
       }
  }
  System.out.println("Maximum current price is : "+ r);
}

  
  @AfterTest
  public void terminateBrowser(){
      driver.close();
  }
}