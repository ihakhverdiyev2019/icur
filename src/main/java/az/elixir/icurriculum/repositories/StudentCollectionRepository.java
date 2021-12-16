package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.BadgesModel;
import az.elixir.icurriculum.models.StudentCollections;
import az.elixir.icurriculum.models.StudentProgram;
import az.elixir.icurriculum.models.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCollectionRepository extends CrudRepository<StudentCollections,Integer> {

    List<StudentCollections> findAllByStudentProgram(StudentProgram studentProgram);

    StudentCollections findByBadgesModelAndStudentProgram(BadgesModel badgesModel, StudentProgram studentProgram);


}
