package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.FavoriteEmployerEntity;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployerDao {

    private SessionFactory sessionFactory;

    public EmployerDao(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public String SaveFavoriteEmployer(FavoriteEmployerEntity favoriteEmployerEntity) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(favoriteEmployerEntity);
        return favoriteEmployerEntity.getId();
    }

    public FavoriteEmployerEntity getFavoriteEmployer(String id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteEmployerEntity employer = null;
        try {
            employer = session
                    .createQuery("SELECT e FROM FavoriteEmployerEntity e WHERE e.id = :id", FavoriteEmployerEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return employer;
    }

    public void deleteFavoriteEmployer(String id) {
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
