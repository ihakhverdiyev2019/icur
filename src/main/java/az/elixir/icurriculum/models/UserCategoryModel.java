package az.elixir.icurriculum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;


    @OneToMany(mappedBy="userCategoryModel")
    private List<UsersModel> usersModel;

    public UserCategoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<UsersModel> getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(List<UsersModel> usersModel) {
        this.usersModel = usersModel;
    }
}

