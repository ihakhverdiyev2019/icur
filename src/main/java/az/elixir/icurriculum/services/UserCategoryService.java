package az.elixir.icurriculum.services;

import az.elixir.icurriculum.models.UserCategoryModel;

import java.util.List;

public interface UserCategoryService {

    List<UserCategoryModel> getAllUserCategory();

    UserCategoryModel getCategoryById(int id);

    UserCategoryModel getCategoryByCategoryName(String category);



}
