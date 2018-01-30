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
import pages.HomePage;

@Listeners(utilities.Listeners.class)
public class LoginE {
	WallPost act = new WallPost();
	LoginPage LP = new LoginPage();
	SocialGroupPage SP = new SocialGroupPage();
	ProfilePage PP = new ProfilePage();
	HomePage HP = new HomePage();
	ContactPage CP = new ContactPage();
	CoursePage CrP = new CoursePage();
	Files file = new Files();

	@BeforeClass
	public void teacherLogin() throws InterruptedException, IOException {
		LP.signIn("Teacher");
		HP.navigateToTopNews();
	}

	@Test(priority = 1)
	public void testTeacherJoinsStudentSocialGroup() throws InterruptedException, IOException {
		SP.navigateToGroupPage();
		SP.joiningGroup("studentGroup");
	}

	@Test(priority = 2)
	public void testTeacherDeleteFiles() throws InterruptedException, IOException {
		CrP.navigateToCourse();
		file.deleteCourseFile();
	}

	@Test(priority = 3)
	public void testTeacherDeleteSocialGroup() throws InterruptedException, IOException {
		SP.navigateToGroupPage();
		SP.navigateToGroupWall("teacherGroup");
		SP.deleteGroup();
	}

	@Test(priority = 4)
	public void testTeacherLeavesStudentSocialGroup() throws InterruptedException, IOException {
		SP.navigateToGroupPage();
		SP.navigateToGroupWall("studentGroup");
		SP.leaveGroup();
	}

	@AfterClass
	public void teacherLogout() {
		LP.signOut();
	}
}
