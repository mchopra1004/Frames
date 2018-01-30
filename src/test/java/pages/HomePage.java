package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class HomePage extends Base {

	/*
	 * Navigate to Top-News wall
	 */
	public void navigateToTopNews() throws InterruptedException {
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='Home']")));
		driver.findElement(By.xpath("//a[@aria-label='Home']")).click();
		
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				WebElement postMaker = driver.findElement(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']"));
				wait.until(ExpectedConditions.stalenessOf(postMaker));
			} catch (Exception e) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")),
						ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
			}
		} else {
			wait.until(ExpectedConditions.or(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")),
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
		}
	}

	/*
	 * Navigate to Recent-News Wall
	 */
	public void navigateToRecentNews() {
		driver.findElement(By.xpath("//a[text()='recent news']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")), ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
	}

	/*
	 * Handle System Compatibility Checker (SCC)
	 */
	public void handleSCC() throws InterruptedException, IOException {
		wait = new WebDriverWait(driver, 60);
		
	try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='snooze-button']")));
		driver.findElement(By.xpath("//button[@id='snooze-button']")).click();
		driver.findElement(By.xpath("//button[text()='30 Days']")).click();
	} catch (Exception e) {
		driver.findElement(By.id("acceptUnknownAcpPluginBtn")).click();
	}
//		wait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(By.id("acceptUnknownAcpPluginBtn")),
//				ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='snooze-button']"))));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='snooze-button']")));
//		driver.findElement(By.xpath("//button[@id='snooze-button']")).click();
//		driver.findElement(By.xpath("//button[text()='30 Days']")).click();
		Thread.sleep(3000);
	}
}