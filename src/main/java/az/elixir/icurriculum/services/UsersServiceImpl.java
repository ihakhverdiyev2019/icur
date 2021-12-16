package az.elixir.icurriculum.services;

import az.elixir.icurriculum.dtos.AddUserDTO;
import az.elixir.icurriculum.models.UserCategoryModel;
import az.elixir.icurriculum.models.UsersModel;
import az.elixir.icurriculum.repositories.UserCategoryRepository;
import az.elixir.icurriculum.repositories.UsersRepository;
import az.elixir.icurriculum.utils.SHAEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserCategoryService userCategoryService;


    @Override
    public UsersModel findUserbyUsernameAndPass(String username, String pass) {
        SHAEncryption shaEncryption = new SHAEncryption();
        UsersModel usersModel= usersRepository.findByUsernameAndPassword(username, shaEncryption.getEncryptPass(pass) );


        return usersModel;
    }

    @Override
    public void saveUser(AddUserDTO addUserDTO) {
        SHAEncryption shaEncryption = new SHAEncryption();
        UsersModel usersModel = new UsersModel();
        UserCategoryModel userCategoryModel = userCategoryService.getCategoryById(addUserDTO.getCategoryId());
        System.out.println(addUserDTO.getCategoryId());
        usersModel.setFirstname(addUserDTO.getFirstname());
        usersModel.setSurname(addUserDTO.getLastname());
        usersModel.setUsername(addUserDTO.getUsername());
        usersModel.setPassword(shaEncryption.getEncryptPass(addUserDTO.getPassword()));
        usersModel.setUserCategoryModel(userCategoryModel);

        usersRepository.save(usersModel);
    }

    @Override
    public List<UsersModel> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public List<UsersModel> getAllUserByCategory(UserCategoryModel userCategoryModel) {
        return usersRepository.findAllByUserCategoryModel(userCategoryModel);
    }

    @Override
    public UsersModel getById(String id) {
        return usersRepository.findById(Integer.parseInt(id)).get();
    }

    @Override
    public void saveUser(UsersModel usersModel) {
        usersRepository.save(usersModel);
    }
}
