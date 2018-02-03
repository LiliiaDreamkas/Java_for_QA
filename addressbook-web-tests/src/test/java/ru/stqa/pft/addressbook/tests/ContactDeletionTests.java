package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if(app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("TestFirstName").withLastName("TestLastName").withAddress("Test address")
              .withMobilePhone("+7(000) 000-00-00").withHomePhone("+7 812 495-00-00").withWorkPhone("+7 812 311-00-00")
              .withEmail("testEmail@gmail.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
