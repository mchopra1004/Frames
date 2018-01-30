package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import setUp.Base;

public class Listeners extends Base implements ITestListener, ISuiteListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String FilePath = System.getProperty("user.dir") + "\\Screenshots\\";
		new File(FilePath);
		String method = result.getMethod().getMethodName();
		try {
			FileUtils.copyFile(scrFile, new File(FilePath + method + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(method + " has failed");
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult xyz) {
		String method = xyz.getMethod().getMethodName();
		System.out.println(method + " has started");

	}

	public void onTestSuccess(ITestResult xyz) {
		String method = xyz.getMethod().getMethodName();
		System.out.println(method + " has passed");
	}

	public void onFinish(ISuite xyz) {
	
	}

	public void onStart(ISuite xyz) {
		try {
			File f = new File(System.getProperty("user.dir") + "\\Screenshots\\");
			File[] files = f.listFiles();
			for (File file : files) {
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("No file");
		}
	}

}
