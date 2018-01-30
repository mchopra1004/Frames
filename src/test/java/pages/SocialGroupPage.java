package pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setUp.Base;

public class SocialGroupPage extends Base {

	String studentGroup = "CriticalSanityTestStudentSocialGroup";
	String teacherGroup = "CriticalSanityTestTeacherSocialGroup";
	String shortName = "ShortName";
	String abtGroup = "ahcd2sa1em";
	String topics = "topswd21new";

	public static Map<String, String> grp = new HashMap<String, String>();

	/*
	 * Navigate to Browse All group page
	 */
	public void navigateToGroupPage() throws IOException {
		ocDetails();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Config.getProperty("group"))));
		driver.findElement(By.xpath(Config.getProperty("group"))).click();
		driver.findElement(By.xpath(Config.getProperty("viewAll"))).click();
	}

	/*
	 * Creating social group
	 */
	public void createGroup(String group) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Start a Social Group']")).click();
		getTime();
		String GroupName;
		if (group == "studentGroup") {
			GroupName = studentGroup + df.format(dateobj);
		} else {
			GroupName = teacherGroup + df.format(dateobj);
		}
		grp.put(group, GroupName);
		driver.findElement(By.xpath("//input[@name='groupname']")).sendKeys(GroupName);
		driver.findElement(By.xpath("//input[@name='shortname']")).sendKeys(shortName + df.format(dateobj));
		driver.findElement(By.xpath("//textarea[@name='aboutgroup']")).sendKeys(abtGroup);
		driver.findElement(By.xpath("//input[@name='topic1']")).sendKeys(topics);
		wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submitbutton']")));
		driver.findElement(By.xpath("//input[@name='submitbutton']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='members']")));
	}

	/*
	 * Joining social group
	 */
	public void joiningGroup(String group) throws InterruptedException {
		// searching group
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(grp.get(group));
		driver.findElement(By.xpath(".//a[@id='search_button']")).click();
		// joining the group
		driver.findElement(By.xpath("//a[@class='join']")).click();
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();
	}

	/*
	 * Navigate to Social group wall
	 */
	public void navigateToGroupWall(String group) throws InterruptedException {
		// searching group
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(grp.get(group));
		driver.findElement(By.xpath(".//a[@id='search_button']")).click();
		// accessing group
		driver.findElement(By.xpath("//a[text()='" + grp.get(group) + "']")).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='wall-wraper']")), ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='err_msg'][contains(text(), 'no posts')]"))));
	}

	/*
	 * Deleting social group
	 */
	public void deleteGroup() {
		wait = new WebDriverWait(driver, 60);
		WebElement ele =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		driver.switchTo().alert().accept();
	}

	public void leaveGroup() throws InterruptedException {
		wait = new WebDriverWait(driver, 60);
		WebElement ele =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Leave']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Browse Social Groups']")));
	}
}
