package testCases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commomMethods.Files;
import commomMethods.WallPost;
import pages.ContactPage;
import pages.CoursePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.SocialGroupPage;
import setUp.Base;
import pages.HomePage;

@Listeners(utilities.Listeners.class)
public class LoginF extends Base{
	WallPost act = new WallPost();
	LoginPage LP = new LoginPage();
	SocialGroupPage SP = new SocialGroupPage();
	ProfilePage PP = new ProfilePage();
	HomePage HP = new HomePage();
	ContactPage CP = new ContactPage();
	CoursePage CrP = new CoursePage();
	Files file = new Files();

	@BeforeClass
	public void studentLogin() throws InterruptedException, IOException {
		LP.signIn("Student");
		HP.navigateToTopNews();
	}

	@Test(priority = 1)
	public void testStudentDeleteFileFromMyFilesPage() throws InterruptedException, IOException {
		PP.navigateToPortfolio();
		file.deletePortfolioFile();
	}

	@Test(priority = 2)
	public void testStudentDeleteSocialGroup() throws InterruptedException, IOException {
		SP.navigateToGroupPage();
		SP.navigateToGroupWall("studentGroup");
		SP.deleteGroup();
	}

	@AfterClass
	public void studentLogout() {
		LP.signOut();
		driver.quit();
	}
}
