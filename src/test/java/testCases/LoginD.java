package testCases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commomMethods.Files;
import commomMethods.Timetable;
import commomMethods.WallPost;
import pages.ContactPage;
import pages.CoursePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.SocialGroupPage;
import pages.HomePage;

@Listeners(utilities.Listeners.class)
public class LoginD {
	WallPost act = new WallPost();
	LoginPage LP = new LoginPage();
	SocialGroupPage SP = new SocialGroupPage();
	ProfilePage PP = new ProfilePage();
	HomePage HP = new HomePage();
	ContactPage CP = new ContactPage();
	CoursePage CrP = new CoursePage();
	Files file = new Files();
	Timetable meeting = new Timetable();

	@BeforeClass
	public void studentLogin() throws InterruptedException, IOException {
		LP.signIn("Student");
		HP.navigateToTopNews();
	}

	@Test(priority = 1)
	public void testStudentShareFileOnTopNewsWall() throws InterruptedException {
		act.postFile();
	}

	@Test(priority = 2)
	public void testStudentShareVideoOnTopNewsWall() throws InterruptedException {
		act.postVideo();
	}

	@Test(priority = 3)
	public void testStudentCreateLiveSessionthroughMeetingWidget() throws InterruptedException {
		meeting.createMeeting();
	}

	@Test(priority = 4)
	public void testStudentDeleteLiveSessionthroughMeetingWidget() throws InterruptedException {
		meeting.deleteMeeting();
	}

	@Test(priority = 5)
	public void testStudentVerifyFilesInCourse() throws InterruptedException, IOException {
		CrP.navigateToCourse();
		file.verifyCourseFile();
	}

	@Test(priority = 6)
	public void testStudentCreateSocialGroup() throws IOException, InterruptedException {
		SP.navigateToGroupPage();
		SP.createGroup("studentGroup");
	}

	@Test(priority = 7)
	public void testStudentSubmitQuiz() throws InterruptedException, IOException {
		CrP.navigateToCourse();
		CrP.attemptQuiz();
	}

	@AfterClass
	public void studentLogout() {
		LP.signOut();
	}
}
