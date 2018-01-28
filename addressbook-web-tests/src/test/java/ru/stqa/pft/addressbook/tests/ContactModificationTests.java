package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFName", "TestLastName",
              "Test address", "+7(000) 000-00-00", "+7 812 495-00-00",
              "+7 812 311-00-00", "testEmail@gmail.com", "test1"));
    }
    app.getContactHelper().gotoContactModificationForm();
    app.getContactHelper().fillContactForm(new ContactData("TestFName", "TestLastName",
            "Test address", "+7(000) 000-00-00", "+7 812 495-00-00",
            "+7 812 311-00-00", "testEmail@gmail.com", null), false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToHomePage();
  }
}