package ImageHoster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "images")

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    //id of the image
    private Integer id;

    @Column(name = "title")
    //title of the image
    private String title;

    @Column(columnDefinition = "TEXT")
    //The image in Base64 format
    private String imageFile;

    @Column(name = "description")
    //Description of the image
    private String description;

    @Column(name = "date")
    //Date on which the image is posted
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Image () { }

    public Image (Integer id, String title, String imageFile, String description, Date date) {

        this.id = id;
        this.title = title;
        this.imageFile = imageFile;
        this.description= description;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
