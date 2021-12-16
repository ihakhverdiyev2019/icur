package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.UserCategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategoryModel,Integer> {

    List<UserCategoryModel> findAll();

    UserCategoryModel findById(int id);

    UserCategoryModel findByCategory(String category);
}
