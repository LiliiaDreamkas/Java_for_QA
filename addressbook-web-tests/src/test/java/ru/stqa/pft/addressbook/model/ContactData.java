package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String mobilePhone;
  private final String homePhone;
  private final String workPhone;
  private final String email;

  public ContactData(String firstName, String lastName, String address,
                     String mobilePhone, String homePhone, String workPhone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.homePhone = homePhone;
    this.workPhone = workPhone;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }
}
