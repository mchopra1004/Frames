package setUp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	// public static WebDriver driver;
	public static RemoteWebDriver driver;
	public static Properties Config = new Properties();
	public static DateFormat df;
	public static Date dateobj;
	public static WebDriverWait wait;
	public static String browser;
	public static String ProgramName;
	public static String password;
	public static String env;
	public static String USERNAME;
	public static String ACCESS_KEY;
	public static String URL;
	public static String OS;
	public static DesiredCapabilities caps;

	/*
	 * Invoking the browser and navigating to program's url
	 */
	public void invoke() throws InterruptedException, IOException {

		browser = System.getProperty("browser");
		password = System.getProperty("password");
		env = System.getProperty("env");
		USERNAME = System.getProperty("username");
		ACCESS_KEY = System.getProperty("access");
		URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
		OS = System.getProperty("OS");
		
		ProgramName = System.getProperty("pName");
		if (browser.equalsIgnoreCase("chrome") && OS.equalsIgnoreCase("mac")) {
			caps = DesiredCapabilities.chrome();
			caps.setCapability("platform", "OS X 10.9");
			caps.setCapability("version", "50.0");

		}

		if (browser.equalsIgnoreCase("firefox") && OS.equalsIgnoreCase("mac")) {

			caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "OS X 10.9");
			caps.setCapability("version", "16.0");

		}
		if (browser.equalsIgnoreCase("chrome") && OS.equalsIgnoreCase("windows")) {
			caps = DesiredCapabilities.chrome();
			caps.setCapability("platform", "Windows 7");
			caps.setCapability("version", "49.0");

		}

		if (browser.equalsIgnoreCase("firefox") && OS.equalsIgnoreCase("windows")) {
			caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "Windows 7");
			caps.setCapability("version", "47.0.2");

		}

		caps.setCapability("maxDuration", 10800);
		caps.setCapability("idleTimeout", 1000);
		driver = new RemoteWebDriver(new URL(URL), caps);
		driver.setFileDetector(new LocalFileDetector());

		if (env.equalsIgnoreCase("staging")) {
			driver.get("https://www-" + ProgramName + "-lms-stg.2u.com");
		}

		else if (env.equalsIgnoreCase("production")) {

			File file = new File(
					System.getProperty("user.dir") + "/src/test/resources/Properties/ProductionURLs.properties");
			FileInputStream userFile = new FileInputStream(file);
			Config.load(userFile);
			driver.get(Config.getProperty(ProgramName.toLowerCase()));
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	/*
	 * Getting user-name from property file
	 */
	public void getUser() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Properties/User.properties");
		FileInputStream userFile = new FileInputStream(file);
		Config.load(userFile);
	}

	/*
	 * Getting xPaths(from property file) of elements available in 2Bar
	 */
	public void ocDetails() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Properties/OCbar.properties");
		FileInputStream userFile = new FileInputStream(file);
		Config.load(userFile);
	}

	/*
	 * Getting content(from property file) to be posted on walls
	 */
	public void getPost() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Properties/Post.properties");
		FileInputStream userFile = new FileInputStream(file);
		Config.load(userFile);
	}

	/*
	 * Getting current time stamp for the content to be posted on walls
	 */
	public void getTime() {
		df = new SimpleDateFormat("dd/MM/yy HH:mm:ss a");
		dateobj = new Date();
	}
}
