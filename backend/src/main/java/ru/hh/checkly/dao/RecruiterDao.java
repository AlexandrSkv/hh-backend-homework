package ru.hh.checkly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.checkly.entity.Recruiter;

import javax.inject.Inject;
import java.util.Optional;

public class RecruiterDao {
  private final SessionFactory sessionFactory;

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Inject
  public RecruiterDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Optional<Recruiter> getRecruiterById(Long id) {
    Recruiter recruiter = getSession().get(Recruiter.class, id);
    return Optional.ofNullable(recruiter);
  }

  public Long saveRecruiter(Recruiter recruiter) {
    getSession().save(recruiter);
    return recruiter.getId();
  }

}
