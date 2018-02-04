package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/qa.png");
    ContactData contact = new ContactData().withFirstName("TestFirstName").withLastName("TestLastName").withAddress("Test address")
            .withMobilePhone("+7(000) 000-00-00").withHomePhone("+7 812 495-00-00").withWorkPhone("+7 812 311-00-00")
            .withEmail("testEmail@gmail.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/qa.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
