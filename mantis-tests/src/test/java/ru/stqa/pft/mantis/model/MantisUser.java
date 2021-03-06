package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class MantisUser {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public MantisUser withId(int id) {
    this.id = id;
    return this;
  }

  public MantisUser withUsername(String username) {
    this.username = username;
    return this;
  }

  public MantisUser withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "MantisUser{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
