package commomMethods;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import setUp.Base;

public class WallPost extends Base {

	String fileName = "Book1posted.xlsx";
	public static Map<String, String> posted = new HashMap<String, String>();
	WebDriverWait wait;

	/*
	 * Getting the WYSWYG editor active
	 */
	public void readyToPost() throws IOException {
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")));
		driver.findElement(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")).click();
		getPost();
		getTime();
	}

	/*
	 * Posting text content on the wall
	 */
	public void post(String postContent) throws IOException, InterruptedException {
		readyToPost();
		String finalContent = Config.getProperty(postContent) + df.format(dateobj);
		posted.put(postContent, finalContent);
		driver.switchTo().frame("ext-gen143").switchTo().activeElement().sendKeys(finalContent);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/b[text()='Share']")));
		driver.findElement(By.xpath("//button/b[text()='Share']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//ul[@id='wall-wraper']/li[1]//div[@class='post']"), finalContent));
	}

	/*
	 * Posting url on the wall
	 */
	public void postUrl(String postContent) throws IOException, InterruptedException {
		readyToPost();
		String finalContent = (Config.getProperty(postContent) + df.format(dateobj)).replaceAll("[/ :]", "") + ".com";
		posted.put(postContent, finalContent);
		driver.findElement(By.xpath("//button[@aria-label='Attach a Web Link']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='wall-attachment-url']")));
		driver.findElement(By.xpath("//input[@id='wall-attachment-url']")).sendKeys(finalContent);
		driver.findElement(By.xpath("//button/b[text()='Share']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//ul[@id='wall-wraper']/li[1]//div[@class='attachment']/a"), finalContent));
	}

	/*
	 * Posting image on the wall
	 */
	public void postImage() throws InterruptedException {
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")));
		driver.findElement(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")).click();
		driver.findElement(By.xpath("//button[@aria-label='Attach a Photo']")).click();
		driver.switchTo().frame("_iframe-ksubox").findElement(By.xpath("//div[@id='selectFromMedia']/input")).click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("_iframe-klibrarybox"));
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button/b[text()='Share']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']/li[1]//a[@rel='photo']")));
	}

	/*
	 * Posting document file on the wall
	 */
	public void postFile() throws InterruptedException {
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")));
		driver.findElement(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")).click();
		driver.findElement(By.xpath("//button[@aria-label='Attach a File']")).click();
		String filePath = System.getProperty("user.dir") + "/Files/" + fileName + "";
		File fff = new File(filePath);
		driver.findElement(By.xpath("//input[@id='file-upload-file'][@type='file']")).sendKeys(fff.getAbsolutePath());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload']")));
		driver.findElement(By.xpath("//button[text()='Upload']")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class='x-panel-header-text']"),
				"File attached"));
		driver.findElement(By.xpath("//button/b[text()='Share']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']/li[1]//a[contains(text(), 'Book')]")));
	}

	/*
	 * Posting video on the wall
	 */
	public void postVideo() throws InterruptedException {
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")));
		driver.findElement(By.xpath("//textarea[@id='wall_publisher_textarea_noedit']")).click();
		driver.findElement(By.xpath("//button[@aria-label='Attach a Video']")).click();
		driver.switchTo().frame("_iframe-ksubox").findElement(By.xpath("//div[@id='selectFromMedia']/input")).click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("_iframe-klibrarybox"));
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button/b[text()='Share']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@id='wall-wraper']/li[1]//div[@class='mvm uiStreamAttachments clearfix']")));
	}

	/*
	 * Verify the present of posted content on the wall
	 */
	public void verify(String postContent) throws IOException {
		getPost();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")));
		Assert.assertTrue(driver.getPageSource().contains(posted.get(postContent)));
	}
}
