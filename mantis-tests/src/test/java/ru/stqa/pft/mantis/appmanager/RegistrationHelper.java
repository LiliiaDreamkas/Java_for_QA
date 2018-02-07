package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String username, String email){
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
