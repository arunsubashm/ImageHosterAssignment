package ImageHoster.repository;

import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class TagRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Tag createTag(Tag tag) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return tag;
    }

    public Tag getTagByName(String name) {

        EntityManager em = emf.createEntityManager();
        Tag tag = null;
        TypedQuery<Tag> query = em.createQuery("SELECT ta from Tag ta WHERE ta.name=:name", Tag.class);
        query.setParameter("name", name);
        try {
            tag = query.getSingleResult();
        } catch (Exception e) {
            tag = null;
        }

        return tag;
    }
}
