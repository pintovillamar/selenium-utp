package org.utp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Announcement {

    public String readProp(String propFile, String property) {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("property file '" + propFile + "' not found in the classpath");
            }
        } else {
            throw new RuntimeException("property file '" + propFile + "' not found in the classpath");
        }
        return prop.getProperty(property);
    }

    @SuppressWarnings("unused")
    static void login() {
        String pathToChromeDriver = "/home/jose/IdeaProjects/tests/seleniumUTP/src/main/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        WebDriver driver = new ChromeDriver();

        Announcement U = new Announcement();

        String email = U.readProp("config.properties", "email");
        String password = U.readProp("config.properties", "pass");


        driver.get("https://canvas.utp.edu.pe/login");

        String login_with_microsoft = "/html/body/div[3]/div[2]/div/div/div[1]/div/div/div/div/div/div[2]/form[1]/div[4]/ul/li/a";
        String input_email_microsoft = "/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div[1]/div[3]/div/div/div/div[2]/div[2]/div/input[1]";
        String button_email_microsoft = "/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div[1]/div[3]/div/div/div/div[4]/div/div/div/div[2]/input";
        String input_password_microsoft = "/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div/div[2]/input";
        String button_password_microsoft = "/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[4]/div[2]/div/div/div/div/input";
        String dont_stay_signed_in = "/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[1]/input";

        driver.findElement(By.xpath(login_with_microsoft)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath(input_email_microsoft)).sendKeys(email);
        driver.findElement(By.xpath(button_email_microsoft)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath(input_password_microsoft)).sendKeys(password);
        driver.findElement(By.xpath(button_password_microsoft)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath(dont_stay_signed_in)).click();

        // Go to Courses

        String courses_button = "/html/body/div[3]/header[2]/div[1]/ul/li[3]/a";
        String courses_all_button = "/html/body/div[4]/span/span/div/div/div/div/div/ul[2]/li[2]/a";
            String current_courses = "course-list-course-title-column course-list-no-left-border";


        driver.findElement(By.xpath(courses_button)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath(courses_all_button)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement table = driver.findElement(By.id("my_courses_table"));

        List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.className("course-list-table-row"));

        for (WebElement row : rows) {
            String courseName = row.findElement(By.className("course-list-course-title-column")).getText();

            System.out.println("Asignatura: " + courseName);
        }

    }

    public static void main(String[] args) {
        Announcement announcement = new Announcement();
        String email = announcement.readProp("config.properties", "email");
        login();
    }

}
