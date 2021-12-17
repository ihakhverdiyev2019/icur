package az.elixir.icurriculum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CollectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name="badge_model_id", nullable=false)
    private BadgesModel badgesModel;



    @Column(nullable = true)
    private String photos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BadgesModel getBadgesModel() {
        return badgesModel;
    }

    public void setBadgesModel(BadgesModel badgesModel) {
        this.badgesModel = badgesModel;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotosImagePath() {

        return "/images/collection/" + photos;
    }
}
