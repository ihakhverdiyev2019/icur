package az.elixir.icurriculum.services;

import az.elixir.icurriculum.dtos.AddUserDTO;
import az.elixir.icurriculum.models.UserCategoryModel;
import az.elixir.icurriculum.models.UsersModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UsersService {

    UsersModel findUserbyUsernameAndPass(String username, String pass);


    void saveUser(AddUserDTO addUserDTO, MultipartFile multipartFile) throws IOException;


    List<UsersModel> getAllUser();


    List<UsersModel> getAllUserByCategory(UserCategoryModel userCategoryModel);


    UsersModel getById(String id);

    void saveUser(UsersModel usersModel);
}
