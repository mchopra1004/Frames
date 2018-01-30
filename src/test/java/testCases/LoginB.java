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
public class LoginB {

	WallPost act = new WallPost();
	LoginPage LP = new LoginPage();
	SocialGroupPage SP = new SocialGroupPage();
	ProfilePage PP = new ProfilePage();
	HomePage HP = new HomePage();
	ContactPage CP = new ContactPage();
	CoursePage CrP = new CoursePage();

	@BeforeClass
	public void studentLogin() throws InterruptedException, IOException {
		LP.signIn("Student");
		HP.navigateToTopNews();
	}

	@Test(priority = 1)
	public void testStudentPostTextOnTopNewsWall() throws IOException, InterruptedException {
		act.post("studentTextOnTop");
		act.verify("studentTextOnTop");
	}

	@Test(priority = 3)
	public void testStudentVerifyTopNewsWallPostOnRecentNewsWall() throws IOException, InterruptedException {
		HP.navigateToRecentNews();
		act.verify("studentTextOnTop");
	}

	@Test(priority = 4)
	public void testStudentJoinsTeacherSocialGroup() throws IOException, InterruptedException {
		SP.navigateToGroupPage();
		SP.joiningGroup("teacherGroup");
	}

	@Test(priority = 5)
	public void testStudentCommentOnTeacherCoursePost() throws IOException, InterruptedException {
		CrP.navigateToCourse();
		act.verify("teacherURLOnCourse");
		CrP.makeComment();
	}

	@Test(priority = 6)
	public void testStudentVerifyTopNewsWallPostOnProfileWall() throws IOException, InterruptedException {
		PP.navigateToProfileWall();
		act.verify("studentTextOnTop");
	}

	@Test(priority = 7)
	public void testStudentPostURLOnTeacherSocialGroup() throws InterruptedException, IOException {
		SP.navigateToGroupPage();
		SP.navigateToGroupWall("teacherGroup");
		act.postUrl("studentURLonGroup");
		act.verify("studentURLonGroup");
	}

	@AfterClass
	public void studentLogout() {
		LP.signOut();
	}

}
