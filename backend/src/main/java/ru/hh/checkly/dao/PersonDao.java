package ru.hh.checkly.dao;

import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class PersonDao {

  private final SessionFactory sessionFactory;

  @Inject
  public PersonDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public boolean checkIfPersonExists(String email, String password) {
    return (boolean) sessionFactory
      .getCurrentSession()
      .createNativeQuery("SELECT EXISTS (SELECT 1 FROM person WHERE email = :email AND password = :password)")
      .setParameter("email", email)
      .setParameter("password", password)
      .getSingleResult();
  }

}
