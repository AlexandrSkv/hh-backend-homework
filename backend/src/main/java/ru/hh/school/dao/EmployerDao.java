package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.FavoriteEmployerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployerDao {

    private SessionFactory sessionFactory;

    public EmployerDao(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Integer addFavoriteEmployer(FavoriteEmployerEntity favoriteEmployerEntity) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(favoriteEmployerEntity);
        return favoriteEmployerEntity.getId();
    }

    public Optional<FavoriteEmployerEntity> getFavoriteEmployer(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteEmployerEntity employer = session
                .createQuery("SELECT e FROM FavoriteEmployerEntity e WHERE e.id = :id", FavoriteEmployerEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(employer);
    }

    public void deleteFavoriteEmployer(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteEmployerEntity employer = session.load(FavoriteEmployerEntity.class,id);
        session.delete(employer);
    }

    public List<FavoriteEmployerEntity> getFavoriteEmployers(Integer page, Integer per_page) {
        Session session = getSessionFactory().getCurrentSession();
        return session
                .createQuery("SELECT e FROM FavoriteEmployerEntity e", FavoriteEmployerEntity.class)
                .setFirstResult(page * per_page)
                .setMaxResults(per_page)
                .list();
    }



}
