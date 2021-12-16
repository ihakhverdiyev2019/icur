package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.UserCategoryModel;
import az.elixir.icurriculum.models.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<UsersModel, Integer> {

    UsersModel findByUsernameAndPassword(String username, String pass);

    List<UsersModel> findAll();

    List<UsersModel> findAllByUserCategoryModel(UserCategoryModel userCategoryModel);
}
