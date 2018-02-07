package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }

  public void toUserList() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void chooseUserById(int id) {
    click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=2']"));
  }

  public void resetUserPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
