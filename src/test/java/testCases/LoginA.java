package testCases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commomMethods.WallPost;
import pages.ContactPage;
import pages.CoursePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.SocialGroupPage;
import pages.HomePage;

@Listeners(utilities.Listeners.class)
public class LoginA {

	WallPost act = new commomMethods.WallPost();
	LoginPage LP = new LoginPage();
	SocialGroupPage SP = new SocialGroupPage();
	ProfilePage PP = new ProfilePage();
	HomePage HP = new HomePage();
	ContactPage CP = new ContactPage();
	CoursePage CrP = new CoursePage();

	@BeforeClass
	public void teacherLogin() throws InterruptedException, IOException {
		LP.invoke();
		LP.signIn("Teacher");
		HP.handleSCC();
	}

	@Test(priority = 1)
	public void createGroup() throws IOException, InterruptedException {
		SP.navigateToGroupPage();
		SP.createGroup("teacherGroup");
	}

	@Test(priority = 2)
	public void testTeacherPostTextOnProfileWallAndVerifyOnTopNewsWall() throws IOException, InterruptedException {
		PP.navigateToProfileWall();
		act.post("teacherTextOnProfile");
		act.verify("teacherTextOnProfile");
		HP.navigateToTopNews();
		act.verify("teacherTextOnProfile");
	}

	@Test(priority = 3)
	public void testTeacherPostTextOnRecentNewsWallAndVerifyOnTopNewsWall() throws InterruptedException, IOException {
		HP.navigateToRecentNews();
		act.post("teacherTextOnRecent");
		act.verify("teacherTextOnRecent");
		HP.navigateToTopNews();
		act.verify("teacherTextOnRecent");
	}

	@Test(priority = 4)
	public void testTeacherPostTextOnTopNewsWall() throws IOException, InterruptedException {
		act.post("teacherTextOnTop");
		act.verify("teacherTextOnTop");
	}

	@Test(priority = 5)
	public void testTeacherPostURLOnStudentsWall() throws IOException, InterruptedException {
		CP.navigateToStudent();
		act.postUrl("teacherURLOnStudent");
		act.verify("teacherURLOnStudent");
	}

	@Test(priority = 6)
	public void testTeacherPostOnProfileWall() throws IOException, InterruptedException {
		PP.navigateToProfileWall();
		act.post("teacherTextOnProfile2");
		act.verify("teacherTextOnProfile2");
		act.postUrl("teacherURLOnProfile");
		act.verify("teacherURLOnProfile");
	}

	@Test(priority = 7)
	public void testTeacherPostOnCourseWall() throws InterruptedException, IOException {
		CrP.navigateToCourse();
		act.postUrl("teacherURLOnCourse");
		act.verify("teacherURLOnCourse");
	}

	@AfterClass
	public void teacherLogout() {
		LP.signOut();
	}
}
