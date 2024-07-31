package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chapter4 {

	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		extentReports.attachReporter(sparkReport);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		String base64 = base64();
		String path = captureScreenShot("Google.jpg");

		extentReports.createTest("ScreenshotTest1", "This is screenshot Test").info("this is info")
				.addScreenCaptureFromBase64String(base64);

		extentReports.createTest("ScreenshotTest2", "This is screenshot Test").info("this is info")
				.addScreenCaptureFromPath(path);

		extentReports.createTest("ScreenshotTest3", "This is screenshot Test").info("this is info")
				.addScreenCaptureFromBase64String(base64, "Google homepage");

		extentReports.createTest("ScreenshotTest4", "This is screenshot Test").info("this is info")
				.addScreenCaptureFromPath(path, "google home page").addScreenCaptureFromPath(path, "google home page")
				.addScreenCaptureFromPath(path, "google home page").addScreenCaptureFromPath(path, "google home page");

		extentReports.createTest("ScreenshotTest7", "This is screenshot Test").info("this is info")
				.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		extentReports.flush();
		driver.quit();
		Desktop.getDesktop().browse((file).toURI());
	}

	public static String captureScreenShot(String fileName) {
		TakesScreenshot tsc = (TakesScreenshot) driver;
		File screenShot = tsc.getScreenshotAs(OutputType.FILE);
		File destiPath = new File("./Resources/" + fileName);
		try {
			FileUtils.copyFile(screenShot, destiPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destiPath.getAbsolutePath();
	}

	public static String base64() {
		TakesScreenshot tsc = (TakesScreenshot) driver;
		String base64 = tsc.getScreenshotAs(OutputType.BASE64);
		System.out.println("Screenshot saved successfully");
		return base64;
	}

}
