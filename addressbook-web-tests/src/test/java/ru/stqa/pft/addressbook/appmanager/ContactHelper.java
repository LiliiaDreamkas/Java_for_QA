package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    if (contactData.getPhotoPath() != null) {
      attach(By.name("photo"), contactData.getPhoto());
    }

    if (creation) {
      if (contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContactModificationForm(int index) {
    wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
  }

  public void gotoContactModificationFormById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void gotoContactCreationPage() { click(By.linkText("add new")); }

  public void selectGroupForContacts(int index){
    new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(index));
  }

  public void create(ContactData contact) {
    gotoContactCreationPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    gotoContactModificationFormById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    confirmContactDeletion();
    contactCache = null;
    homePage();
  }

  public void addToGroup (ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupForContacts(group.getId());
    addSelectedToGroup();
  }

  private void addSelectedToGroup() {
    click(By.name("add"));
  }

  public boolean isThereAContact() {
    return  isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> columns = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      String lastName = columns.get(1).getText();
      String firstName = columns.get(2).getText();
      String address = columns.get(3).getText();
//      String[] phones = columns.get(5).getText().split("/n");
      String allPhones = columns.get(5).getText();
      String allEmails = columns.get(4).getText();
//      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
//              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    gotoContactModificationFormById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email)
            .withEmail2(email2).withEmail3(email3).withAddress(address);
  }

//  private void initContactModificationByID(int id) {
//    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
//    WebElement columns = checkbox.findElement(By.xpath("./../.."));
//    List<WebElement> cells = columns.findElement(By.tagName("td"));
//    cells.get(7).findElement(By.name("a")).click();
//  }
}
