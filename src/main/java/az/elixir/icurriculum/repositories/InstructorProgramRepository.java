package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.InstructorProgram;
import az.elixir.icurriculum.models.ProgramsModel;
import az.elixir.icurriculum.models.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorProgramRepository extends CrudRepository<InstructorProgram,Integer> {
    InstructorProgram findByInstructorAndProgram(UsersModel instructor, ProgramsModel programsModel);
}
