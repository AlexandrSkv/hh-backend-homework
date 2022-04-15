package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.FavoriteEmployerEntity;
import ru.hh.school.entity.FavoriteVacancyEntity;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {

    private SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public String SaveFavoriteVacancy(FavoriteVacancyEntity favoriteVacancyEntity) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(favoriteVacancyEntity);
        return favoriteVacancyEntity.getId();
    }

    public FavoriteVacancyEntity getFavoriteVacancy(String id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteVacancyEntity vacancy = null;
        try {
            vacancy = session
                    .createQuery("SELECT v FROM FavoriteVacancyEntity v WHERE v.id = :id", FavoriteVacancyEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {

        }

        return vacancy;
    }

    public void deleteFavoriteVacancy(String id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteVacancyEntity vacancy = session.load(FavoriteVacancyEntity.class,id);
        session.delete(vacancy);
    }

    public List<FavoriteVacancyEntity> getFavoriteVacancies(Integer page, Integer per_page) {
        Session session = getSessionFactory().getCurrentSession();
        return session
                .createQuery("SELECT v FROM FavoriteVacancyEntity v", FavoriteVacancyEntity.class)
                .setFirstResult(page * per_page)
                .setMaxResults(per_page)
                .list();
    }
}
