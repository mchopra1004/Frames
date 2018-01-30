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
public class LoginC {
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
	public void teacherLogin() throws InterruptedException, IOException {
		LP.signIn("Teacher");
		HP.navigateToTopNews();
	}

	@Test(priority = 1)
	public void testTeacherShareImageOnTopNewsWall() throws InterruptedException {
		act.postImage();
	}

	@Test(priority = 2)
	public void testTeacherCreateLiveSessionthroughMeetingWidget() throws InterruptedException {
		meeting.createMeeting();
	}

	@Test(priority = 3)
	public void testTeacherUploadFilesInCourse() throws InterruptedException, IOException {
		CrP.navigateToCourse();
		file.uploadCourseFile();
	}

	@Test(priority = 4)
	public void testTeacherDeleteLiveSessionthroughMeetingWidget() throws InterruptedException {
		HP.navigateToTopNews();
		meeting.deleteMeeting();
	}

	@Test(priority = 5)
	public void testTeacherVerifyFilesInPortfolio() throws InterruptedException, IOException {
		PP.navigateToPortfolio();
		file.verifyPortfolioFile();
	}

	@AfterClass
	public void teacherLogout() {
		LP.signOut();
	}
}
