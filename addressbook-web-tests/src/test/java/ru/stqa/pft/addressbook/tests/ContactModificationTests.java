package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.goTo().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFName", "TestLastName",
              "Test address", "+7(000) 000-00-00", "+7 812 495-00-00",
              "+7 812 311-00-00", "testEmail@gmail.com", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoContactModificationForm(before.size() + 1);
    ContactData contact = new ContactData("TestFName", "TestLastName",
            "Test address", "+7(000) 000-00-00", "+7 812 495-00-00",
            "+7 812 311-00-00", "testEmail@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
