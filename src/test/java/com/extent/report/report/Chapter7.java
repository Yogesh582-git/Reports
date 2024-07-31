package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Chapter7 {
	public static void main(String[] args) throws Exception {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
		
		ExtentReports extentReport= new ExtentReports();
		File file= new File("report.html");
		ExtentSparkReporter sparkReport= new ExtentSparkReporter(file);
		extentReport.attachReporter(sparkReport);
		
		extentReport.setSystemInfo("os",System.getProperty("os.name"));
		extentReport.setSystemInfo("java",System.getProperty("java.version"));
		extentReport.setSystemInfo("Chrome",capabilities.getBrowserVersion() + capabilities.getBrowserVersion());
		
		extentReport.flush();
		driver.quit();
		Desktop.getDesktop().browse((file).toURI());
	}
}
