package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Chapter6 {
	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Swamy Saranam Ayyappa");
		sparkReporter.config().setDocumentTitle("Doc Title");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		sparkReporter.config().setCss(".badge-primary{background-color:#6569df}");
		
		
		extentReports.attachReporter(sparkReporter);

		extentReports.createTest("Test1").assignAuthor("Yogi").assignCategory("smoke").assignDevice("Chrome99")
				.pass("This test is passed");

		extentReports.createTest("Test2").assignAuthor("Eswar").assignCategory("sanity").assignDevice("Edge99")
				.fail("This Test is failed");

		extentReports.createTest("Test2").assignAuthor("swamy").assignCategory("sanity").assignDevice("Edge102")
				.fail("This Test is failed");

		extentReports.createTest("Test2").assignAuthor("saranam").assignCategory("smoke").assignDevice("Chrome89")
				.pass("This Test is failed");

		extentReports.createTest("Test7", "The double Test")
				.assignAuthor(new String[] { "swamy", "saranam", "Ayyappa" })
				.assignCategory(new String[] { "smoke", "sanity", "regression" })
				.assignDevice(new String[] { "edge99", "chrome99", "Edge102" });

		extentReports.flush();
		Desktop.getDesktop().browse((file).toURI());
	}
}
