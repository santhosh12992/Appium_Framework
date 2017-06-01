package automationFramework;

import java.lang.reflect.Method;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utilities.Screenshots;
import io.appium.java_client.AppiumDriver;


public class ExtentTestNGITestListener {

	AppiumDriver driver;
	private static ExtentReports extent;
    private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance("extent.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HOSTDEVICE", "MAC-MINI");
		extent.setSystemInfo("PLATFORM", "ANDROID");
		extent.setSystemInfo("DEVICE NAME", "NEXUS 5 - Geny o0");
		extent.setSystemInfo("ANDROID VERSION", "5.1");
		extent.setSystemInfo("INTERNAL MEM", "10 GB");
		extent.setSystemInfo("EXTERNAM MEM", "1 GB");
	}
	
    @BeforeClass
    public synchronized void beforeClass() {
        ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {
        ExtentTest child = ((ExtentTest) parentTest.get()).createNode(method.getAnnotation(Test.class).description());
        test.set(child);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE)
        {        
        	String path = Screenshots.takeScreenshots(driver,result.getMethod().toString().substring(10, 15)+"_Fail");
        	((ExtentTest) test.get()).fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        	//((ExtentTest) test.get()).fail(result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SKIP)
            ((ExtentTest) test.get()).skip(result.getThrowable());
        else
            ((ExtentTest) test.get()).pass("Test passed");

        extent.flush();
    }
}

