package az.elixir.icurriculum.models;



import javax.persistence.*;
import java.util.List;

@Entity
public class UsersModel {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "users_model_id_seq", initialValue = 2, allocationSize = 1)
    @GeneratedValue(generator = "mySeqGen")
    private int id;

    private String username;

    private String password;

    private String firstname;

    private String surname;

    @Column(nullable = true)
    private String photos;

    @OneToMany(mappedBy = "student")
    private List<StudentProgram> studentProgram;



    @OneToMany(mappedBy = "instructor")
    private List<InstructorProgram> instructorProgram;

    @ManyToOne
    @JoinColumn(name = "user_category_model_id", nullable = false)
    private UserCategoryModel userCategoryModel;

    public UsersModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserCategoryModel getUserCategoryModel() {

        return userCategoryModel;
    }

    public void setUserCategoryModel(UserCategoryModel userCategoryModel) {
        this.userCategoryModel = userCategoryModel;
    }

    public List<StudentProgram> getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(List<StudentProgram> studentProgram) {
        this.studentProgram = studentProgram;
    }

    public List<InstructorProgram> getInstructorProgram() {
        return instructorProgram;
    }

    public void setInstructorProgram(List<InstructorProgram> instructorProgram) {
        this.instructorProgram = instructorProgram;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotosImagePath() {

        return "/images/user-photo/" + photos;
    }
}
