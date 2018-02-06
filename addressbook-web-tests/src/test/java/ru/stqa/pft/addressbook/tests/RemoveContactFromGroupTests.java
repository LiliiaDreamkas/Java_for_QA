package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RemoveContactFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if(app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("TestFirstName").withLastName("TestLastName").withAddress("Test address")
              .withMobilePhone("+7(000) 000-00-00").withHomePhone("+7 812 495-00-00").withWorkPhone("+7 812 311-00-00")
              .withEmail("testEmail@gmail.com"));
    }
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactFromGroupRemoving(){
    Contacts contacts = app.db().contacts();
    ContactData contactToRemove = contacts.stream().filter((c) -> c.getGroups().size() != 0).findAny().orElse(null);
    if (contactToRemove == null) {
      contactToRemove = contacts.iterator().next();
      Groups groups = app.db().groups();
      app.contact().addToGroup(contactToRemove, groups.iterator().next());
      contactToRemove = app.db().contactByID(contactToRemove.getId());
    }
    GroupData currentGroup = contactToRemove.getGroups().iterator().next();
    Contacts groupContacts = currentGroup.getContacts();
    app.contact().removeFromGroup(currentGroup, contactToRemove);
    GroupData groupWithoutContact = app.db().groupByID(currentGroup.getId());
    assertThat(groupWithoutContact.getContacts(), equalTo(groupContacts.without(contactToRemove)));
  }
}
