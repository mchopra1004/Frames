package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class CoursePage extends Base {

	/*
	 * Navigate to course wall
	 */
	public void navigateToCourse() throws InterruptedException, IOException {
		ocDetails();
		driver.findElement(By.xpath(Config.getProperty("course"))).click();
		driver.findElement(By.xpath(Config.getProperty("viewAllCourse"))).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Config.getProperty("section1"))));
		driver.findElement(By.xpath(Config.getProperty("section1"))).click();
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")), ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
	}

	/*
	 * Make Comment on Teacher's post
	 */
	public void makeComment() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//ul[@id='wall-wraper']/li[1]//a[@class='comment']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='wall-wraper']/li[1]//a[text()='Submit']")));
		getPost();
		driver.findElement(By.xpath("//div[@class='textarea-wrapper']/textarea"))
				.sendKeys(Config.getProperty("studentComment"));
		driver.findElement(By.xpath("//a[text()='Submit']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//li[@class='wall-item commentBox']/following-sibling::li//div[@class='post']"),
				Config.getProperty("studentComment")));
	}

	/*
	 * Verify file uploads
	 */
	public void verifyUploadedFile() {
		driver.findElement(By.xpath("//a[text()='files']")).click();
	}

	/*
	 * Attempt quiz
	 */
	public void attemptQuiz() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='activity report']")).click();
		driver.findElement(By.xpath("//table[@class='assignments']//a/strong[text()='AutoQuiz']")).click();
		driver.findElement(By.xpath("//form[@method='post']//input[@type='submit']")).click();
		driver.findElement(By.xpath("//label[text()='False']")).click();
		driver.findElement(By.xpath("//a[text()='Finish Exam']")).click();
		driver.findElement(By.xpath("//input[@value='Submit all and finish']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit all and finish']")));
		driver.findElement(By.xpath("//button[text()='Submit all and finish']")).click();
		driver.findElement(By.xpath("//div[@class='oc_quiz_reports']//a[text()='Finish review']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feedback']/h2")));
		driver.findElement(By.xpath("//a[@class='coursework-link']")).click();
	}
}
