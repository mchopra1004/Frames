package commomMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class Timetable extends Base {

	String meetingName = "automation meeting";

	/*
	 * Creating meeting from timetable
	 */
	public void createMeeting() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Create Meeting']")).click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='meetingname']/input")));
		driver.findElement(By.xpath("//div[@id='meetingname']/input")).sendKeys(meetingName);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='meetingtime']")));
		driver.findElement(By.xpath("//input[@id='meetingtime']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='card']/tfoot/tr/th[1]/button")));
		driver.findElement(By.xpath("//table[@class='card']/tfoot/tr/th[1]/button")).click();
		driver.findElement(By.xpath("//input[@name='duration']")).clear();
		driver.findElement(By.xpath("//input[@name='duration']")).sendKeys("10");
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='" + meetingName + "']")));
	}

	/*
	 * Deleting meeting from timetable
	 */
	public void deleteMeeting() throws InterruptedException {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='timetable__header']//h3[text()='" + meetingName + "']")));
		driver.findElement(By.xpath("//div[@class='timetable__header']//h3[text()='" + meetingName + "']")).click();
		WebElement ele = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Edit automation meeting']")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']")));
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'No Events')]")));
	}
}
