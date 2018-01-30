package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class ContactPage extends Base {

	/*
	 * Navigate to Contact (Student) wall
	 */
	public void navigateToStudent() throws IOException, InterruptedException {
		ocDetails();
		driver.findElement(By.xpath(Config.getProperty("button"))).click();
		driver.findElement(By.xpath(Config.getProperty("viewProfile"))).click();
		driver.findElement(By.xpath("//div[@class='tab-group']//a[text()='contacts']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'student')]")));
		driver.findElement(By.xpath("//a[contains(text(), 'student')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Basic Information']")));
		driver.findElement(By.xpath("//a[text()='wall']")).click();
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")), ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
	}
}
