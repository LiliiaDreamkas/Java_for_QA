package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.MantisUser;

public class DbHelper {
  
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public MantisUser nonAdminUser() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    MantisUser user = (MantisUser) session.createQuery("from MantisUser  where id > 1").iterate().next();
    System.out.println(user);
    session.getTransaction().commit();
    session.close();
    return user;
  }

}
