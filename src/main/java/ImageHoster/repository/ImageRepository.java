package ImageHoster.repository;

import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class ImageRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Image uploadImage(Image newImage) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newImage);
            transaction.commit();
        } catch(Exception e) {
            transaction.rollback();
        }

        return newImage;
    }

    public List<Image> getAllImages() {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Image> query = em.createQuery("SELECT im from Image im", Image.class);
        List<Image> resultList;

        resultList = query.getResultList();

        return resultList;

    }

    public Image getImageByTitle(String title) {

        EntityManager em = emf.createEntityManager();
        Image image = null;
        TypedQuery<Image> query = em.createQuery("SELECT im from Image im WHERE im.title=:title", Image.class);
        query.setParameter("title", title);
        try {
            image = query.getSingleResult();
        } catch (Exception e) {
            image = null;
        }

        return image;
    }

    public Image getImage(Integer imageId) {

        EntityManager em = emf.createEntityManager();
        return em.find(Image.class, imageId);
    }

    public void updateImage(Image updatedImage) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(updatedImage);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }

    public void deleteImage(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Image image = em.find(Image.class, imageId);
            em.remove(image);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }
}
