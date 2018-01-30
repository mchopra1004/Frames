package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class LoginPage extends Base {

	/*
	 * Sign in to the program
	 */
	public void signIn(String user) throws InterruptedException, IOException {
		  getUser();
		  String Login;
		  if (ProgramName.equalsIgnoreCase("wu-llm") || ProgramName.equalsIgnoreCase("gwu-mph")
		    || ProgramName.equalsIgnoreCase("gwu-mha") || ProgramName.equalsIgnoreCase("syr-mba")
		    || ProgramName.equalsIgnoreCase("unc-mba") || ProgramName.equalsIgnoreCase("sc-msn")
		    || ProgramName.equalsIgnoreCase("sc-mba") || ProgramName.equalsIgnoreCase("au-mba")
		    || ProgramName.equalsIgnoreCase("yu-med"))
		  {
		   do {
		    driver.findElement(By.xpath("//input[@id='username']")).clear();
		    driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Config.getProperty(user));
		    driver.findElement(By.xpath("//input[@id='password']")).clear();
		    driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		    driver.findElement(By.id("login")).click();
		    try {
		     wait = new WebDriverWait(driver, 30);
		     wait.until(ExpectedConditions.or(
		       ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Home']")),
		       ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='snooze-button']"))));
		     Login = "Pass";
		    } catch (Exception e) {
		     Login = "Fail";
		    }
		   } while (Login == "Fail");
		  } else {
		   driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Config.getProperty(user));
		   driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		   driver.findElement(By.xpath(".//input[@id='loginpage_submit_btn']")).click();
		  }
		 }

	/*
	 * Signing out
	 */
	public void signOut() {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Config.getProperty("button"))));
		driver.findElement(By.xpath(Config.getProperty("button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Config.getProperty("SignOut"))));
		driver.findElement(By.xpath(Config.getProperty("SignOut"))).click();
	}
}
