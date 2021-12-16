package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.ProgramsModel;
import az.elixir.icurriculum.models.StudentProgram;
import az.elixir.icurriculum.models.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentProgramRepository extends CrudRepository<StudentProgram,Integer> {
    StudentProgram findByStudentAndProgram(UsersModel student, ProgramsModel programsModel);

    List<StudentProgram> findAllByStudentAndStatus(UsersModel student,int status);

    StudentProgram findByStudentAndProgramAndStatus(UsersModel usersModel,ProgramsModel programsModel, int status);

    List<StudentProgram> findAllByProgramAndStatus(ProgramsModel programsModel, int status);

}
