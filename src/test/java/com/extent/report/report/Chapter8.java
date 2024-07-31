package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class Chapter8 {
	public static void main(String[] args) throws Exception {

		ExtentReports extentReports = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		sparkReport.viewConfigurer().viewOrder().as(new ViewName[] {
				ViewName.AUTHOR,
				ViewName.CATEGORY,
				ViewName.TEST
		}).apply();
		extentReports.attachReporter(sparkReport);
		

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
