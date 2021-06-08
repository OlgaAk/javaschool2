package javaee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class HomeEJB {

    @PersistenceContext(unitName = "Unit1")
    private EntityManager entityManager;

    public List<UserEntity> getUsers() {
        List<UserEntity> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT user FROM users user");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
