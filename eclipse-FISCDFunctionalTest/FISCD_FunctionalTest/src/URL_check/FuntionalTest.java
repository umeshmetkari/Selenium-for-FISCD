package URL_check;

import java.time.Duration;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FuntionalTest {
 
	
	public static WebDriver driver;
	public static List<WebElement> proceed;
	static List<WebElement> signin;
	static WebElement username;
	
			static void proceedClick() throws InterruptedException {
				 username = driver.findElement(By.xpath("//span[@class='control-label']/following-sibling::div[1]/input[@type='text']"));
				boolean username_vali=username.isDisplayed();
				System.out.println("Got the username field"+username_vali);
				username.sendKeys("ted");	
				Thread.sleep(5000);
				WebElement proceedClick = driver.findElement(By.xpath("//button[.='Proceed']"));
				 proceedClick.click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//span[@class='control-label']/following-sibling::div[1]/input[@type='password']")).sendKeys("Asdf123#");
				
				
			}
			
			static void signinFillUp() throws InterruptedException {
				username = driver.findElement(By.xpath("//span[@class='control-label']/following-sibling::div[1]/input[@type='text']"));
				boolean username_vali=username.isDisplayed();
				System.out.println("Got the username field"+username_vali);
				username.sendKeys("ted");	
				Thread.sleep(5000);
				driver.findElement(By.xpath("//span[@class='control-label']/following-sibling::div[1]/input[@type='password']")).sendKeys("Asdf123#");
			}
			
			static boolean signinValidation() {
				
				 //signin = driver.findElement(By.xpath("//button[.='Sign in']"));
				   signin=driver.findElements(By.xpath("//button[.='Sign in']"));	
				//System.out.println(signin.isEnabled());
					//boolean signin_vali=signin.isDisplayed();
				boolean signin_vali = signin.size() > 0;
				
				 
					return signin_vali;
			}
			
			static void signinClick() {
				 WebElement signinClick = driver.findElement(By.xpath("//button[.='Sign in']"));
				JavascriptExecutor js = (JavascriptExecutor)driver; 
				js.executeScript("arguments[0].click()", signinClick);
			}
			static boolean proceedValidation() {
				// proceed=driver.findElement(By.xpath("//button[.='Proceed']"));
				   proceed=driver.findElements(By.xpath("//button[.='Proceed']"));
					
					//boolean getpage_vali=proceed.isDisplayed();
				 boolean getpage_vali=proceed.size() > 0;
				 
					return getpage_vali;
			}
			static void chisLoginCheck() {
				((JavascriptExecutor)driver).executeScript("window.open()");
				
				ArrayList<String> tabs =new ArrayList<>(driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(1));
				
				driver.get("https://barclays-sit3-uat-fiscd.fisglobal.com:8183/ch/");
			}
			static void fiscdPageValidation() throws InterruptedException {
				System.out.println("logged in to FISCD home page");
				Thread.sleep(40000);
				List<WebElement> taskButton = driver.findElements(By.cssSelector("li[class$='nxTask']>a:nth-child(1)"));
				List<WebElement> taskGroupButton = driver.findElements(By.cssSelector("li[class$='nxTaskGroup']>a:nth-child(1)"));
				List<WebElement> userNotificationButton = driver.findElements(By.cssSelector("li[class$='nxUserNotification']>a:nth-child(1)"));
				Thread.sleep(3000);
				
				System.out.println("found task button");
				int taskValidation=taskButton.size();
				System.out.println(taskValidation);
				
				System.out.println("found task group button");
				int taskGroupValidation= taskGroupButton.size();
				System.out.println(taskGroupValidation);
				
				System.out.println("found User Notification button");
				int userNotificationValidation= taskGroupButton.size();
				System.out.println(userNotificationValidation);
				
				if(taskValidation > 0 && taskGroupValidation > 0 && userNotificationValidation > 0 ) {
					
					System.out.println("FISCD Application Is UP");
				}
				else {
					
					System.out.println("FISCD Home Page Is Blank");
				}
				
			}
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\e5634234\\eclipse-FISCDFunctionalTest\\BROWSER DRIVER\\chromedriver_win32\\chromedriver.exe");
		
		ChromeOptions chromeoption=new ChromeOptions();
		
		chromeoption.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
		
		 driver=new ChromeDriver(chromeoption);
		 //CHIS_ApplicationCheck chisCheck=new CHIS_ApplicationCheck();
		
		driver.get("https://barclays-sit3-uat-fiscd.fisglobal.com:8090/gatewaycontroller/app/");
		Thread.sleep(5000);
		driver.manage().window().maximize();
		
		Thread.sleep(40000);
			System.out.println("test1");
		 boolean signInValidationValue = signinValidation();
	       System.out.println("sign in validation value= "+signInValidationValue);
	     boolean proceedValidationValue=proceedValidation();
	     	System.out.println("Proceed validation value= "+proceedValidationValue);
	     	
	     	//String ServiceUnavailable=driver.findElement(By.xpath("//body[1]/h1")).getText();
	     	//String ServiceUnavailable = driver.findElements(By.xpath("//body[1]/h1")).toString();
	     	//ServiceUnavailable.toString();
	     	//System.out.println(ServiceUnavailable);
	     	//List<WebElement> ServiceUnavailable = driver.findElements(By.linkText("503 Service Unavailable"));
	     	
	     	//System.out.println(ServiceUnavailable.isEmpty());
		try {
			
			
	      
		if(proceedValidationValue==true) {
			//System.out.println("Entered in if condition");
			
			proceedClick();
			Thread.sleep(2000);
			signinClick();
			
			Thread.sleep(2000);
			
			fiscdPageValidation();
			
			Thread.sleep(20000);
			chisLoginCheck();
			
					}
		else if(signInValidationValue==true) {
				
			//System.out.println("Entered in else if loop ");
			
			 	signinFillUp();
				Thread.sleep(5000);
				signinClick();
				
				Thread.sleep(20000);
				chisLoginCheck();
			
			
		}
		else if(proceedValidationValue == false && signInValidationValue == false) {
			
			System.out.println("FISCD Application is down");
		}
		
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		//driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/input")).sendKeys("ted");
		
		//driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/div/div/footer/span/div/div[1]/div[1]/button")).click();
		//driver.findElement(By.xpath("//button[.='Proceed']")).click();
		
		
		//driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/input")).sendKeys("Asdf123#");
		//driver.findElement(By.xpath("//span[@class='control-label']/following-sibling::div[1]/input[@type='password']")).sendKeys("Asdf123#");
		
		Thread.sleep(30000);
		
		
		System.out.println("check0");
		//driver.navigate().refresh()     # to refresh the page
		
		
		
		
		//Actions actions = new Actions(driver);
		//actions.moveToElement(signin).click().build().perform();
		
	//	System.out.println("check1");
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	System.out.println("check2");
	//	wait.until(ExpectedConditions.presenceOfElementLocated(signin));
	//	System.out.println("check3");
		
		
		//driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		//driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/div/div/footer/span/div/div[1]/div[1]/button")).click();
		//driver.findElement(By.className("fis-input ng-touched ng-dirty ng-valid-parse ng-empty ng-invalid ng-invalid-required")).sendKeys("Asdf123#");
	  //driver.findElement(By.xpath("//div[@class='fis-block']/child::div[@class='fis-primary-block']/child::button[1]")).click();
		//driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/div/div/footer/span/div/div[1]/div[1]/button")).click();
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//Scroll down till the bottom of the page
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}

}
