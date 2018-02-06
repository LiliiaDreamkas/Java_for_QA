package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class AddContactToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if(app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("TestFirstName").withLastName("TestLastName").withAddress("Test address")
              .withMobilePhone("+7(000) 000-00-00").withHomePhone("+7 812 495-00-00").withWorkPhone("+7 812 311-00-00")
              .withEmail("testEmail@gmail.com"));
    }
  }

  @Test
  public void testContactToGroupAddition(){
    Contacts contacts = app.db().contacts();
    ContactData contactForAddition = contacts.iterator().next();
    if ( app.db().groups().size() == 0  || contactForAddition.getGroups().size() == app.db().groups().size()) {
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    Groups contactGroups = contactForAddition.getGroups();
    groups.without(contactGroups);
    GroupData selectedGroup = groups.iterator().next();
    app.contact().addToGroup(contactForAddition, selectedGroup);
    ContactData addedContact = app.db().contactByID(contactForAddition.getId());
    assertThat(addedContact.getGroups(), equalTo(contactGroups.withAdded(selectedGroup)));

  }
}
