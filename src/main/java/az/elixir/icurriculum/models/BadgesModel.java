package az.elixir.icurriculum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class BadgesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String badgeName;

    @OneToMany(mappedBy="badgesModel")
    private List<ProgramBadge> programBadges;

    @OneToMany(mappedBy="badgesModel")
    private List<StudentCollections> studentCollections;


    @Column(nullable = true)
    private String photos;

    public BadgesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public List<ProgramBadge> getProgramBadges() {
        return programBadges;
    }

    public void setProgramBadges(List<ProgramBadge> programBadges) {
        this.programBadges = programBadges;
    }


    public List<StudentCollections> getStudentCollections() {
        return studentCollections;
    }

    public void setStudentCollections(List<StudentCollections> studentCollections) {
        this.studentCollections = studentCollections;
    }

    @Transient
    public String getPhotosImagePath() {

        return "/images/badge-photo/" + photos;
    }
}
