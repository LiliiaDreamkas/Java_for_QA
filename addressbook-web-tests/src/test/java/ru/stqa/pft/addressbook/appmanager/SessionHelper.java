package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(Properties properties) {
    getPage(properties.getProperty("web.baseUrl", "http://localhost/addressbook"));
    type(By.name("user"), properties.getProperty("web.adminLogin", "admin"));
    type(By.name("pass"), properties.getProperty("web.adminPassword", "secret"));
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
