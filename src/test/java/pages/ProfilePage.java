package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class ProfilePage extends Base {

	/*
	 * Navigate to 'view profile page'
	 */
	public void navigateToViewProfilePage() throws IOException, InterruptedException {
		ocDetails();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Config.getProperty("button"))));
		driver.findElement(By.xpath(Config.getProperty("button"))).click();
		driver.findElement(By.xpath(Config.getProperty("viewProfile"))).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='page__header']/h2"),
				"My Profile"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tab-group']//a[text()='wall']")));

	}

	/*
	 * Navigate to Profile wall
	 */
	public void navigateToProfileWall() throws IOException, InterruptedException {
		navigateToViewProfilePage();
		driver.findElement(By.xpath("//div[@class='tab-group']//a[text()='wall']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")), ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
	}

	/*
	 * Navigate to Portfolio page
	 */
	public void navigateToPortfolio() throws InterruptedException, IOException {
		navigateToViewProfilePage();
		driver.findElement(By.xpath("//a[text()='portfolio']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Media']")));
	}
}
