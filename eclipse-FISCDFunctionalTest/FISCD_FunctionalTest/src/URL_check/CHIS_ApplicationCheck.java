package URL_check;



import java.sql.Driver;

import org.openqa.selenium.WebDriver;

public class CHIS_ApplicationCheck  {
	public static WebDriver chis_driver;
	public CHIS_ApplicationCheck(WebDriver driver) {
		// TODO Auto-generated constructor stub
		chis_driver=driver;
		
	}
	public static WebDriver getChis_driver() {

		chis_driver.get("");
		return chis_driver;
		
	
	}
	
	
	
	
	
	

}
