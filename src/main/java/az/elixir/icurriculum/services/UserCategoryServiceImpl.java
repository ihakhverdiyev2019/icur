package az.elixir.icurriculum.services;

import az.elixir.icurriculum.models.UserCategoryModel;
import az.elixir.icurriculum.repositories.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService{

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategoryModel> getAllUserCategory() {
        return userCategoryRepository.findAll();
    }

    @Override
    public UserCategoryModel getCategoryById(int id) {
        return userCategoryRepository.findById(id);
    }

    @Override
    public UserCategoryModel getCategoryByCategoryName(String category) {
        return userCategoryRepository.findByCategory(category);
    }
}
