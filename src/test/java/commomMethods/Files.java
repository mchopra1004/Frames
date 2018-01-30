package commomMethods;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import setUp.Base;

public class Files extends Base {

	String fileName = "Book1.xlsx";

	/*
	 * Uploading a document file in files section under course page
	 */
	public void uploadCourseFile() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='files']")).click();
		driver.findElement(By.xpath("//input[@id='upload-btn']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='file-upload-file'][@type='file']")));
		
		String filePath = System.getProperty("user.dir") + "/Files/" + fileName + "";
		File fff = new File(filePath);
		driver.findElement(By.xpath("//input[@id='file-upload-file'][@type='file']")).sendKeys(fff.getAbsolutePath());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload']")));
		driver.findElement(By.xpath("//button[text()='Upload']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + fileName + "']")));
	}

	/*
	 * Verify the presence of uploaded document file in files section under
	 * course page
	 */
	public void verifyCourseFile() {
		driver.findElement(By.xpath("//a[text()='files']")).click();
		String fetched = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-displayname']/a"))
				.getText();
		Assert.assertEquals(fetched, fileName, "No file found");
	}

	/*
	 * Deletion of uploaded document file in files section under course page
	 */
	public void deleteCourseFile() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='files']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='grid-remove']")));
		driver.findElement(By.xpath("//div[@class='grid-remove']")).click();
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='No files to display']")));
	}

	/*
	 * Verify presence of uploaded document file on portfolio page
	 */
	public void verifyPortfolioFile() {
		String fetched = driver.findElement(By.xpath("//table[@class='file-table']/tbody/tr[2]/td[1]/a")).getText();
		Assert.assertEquals(fetched, fileName, "No file present");
	}

	/*
	 * Deletion of document file on portfolio page
	 */
	public void deletePortfolioFile() throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Files']/following-sibling::div/a[text()='See All >']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='grid-remove']")));
		driver.findElement(By.cssSelector(".grid-remove")).click();
		driver.findElement(By.id("ext-gen78")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='No files to display']")));
	}

}
