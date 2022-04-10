package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.FavoriteVacancyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {

    private SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Integer addFavoriteVacancy(FavoriteVacancyEntity favoriteVacancyEntity) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(favoriteVacancyEntity);
        return favoriteVacancyEntity.getId();
    }

    public Optional<FavoriteVacancyEntity> getFavoriteVacancy(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        FavoriteVacancyEntity vacancy = session
                .createQuery("SELECT v FROM FavoriteVacancyEntity v WHERE v.id = :id", FavoriteVacancyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(vacancy);
    }

    public void deleteFavoriteVacancy(Integer id) {
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
