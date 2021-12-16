package az.elixir.icurriculum.services;

import az.elixir.icurriculum.dtos.AddProgramDTO;
import az.elixir.icurriculum.dtos.AssignProgramDTO;
import az.elixir.icurriculum.models.InstructorProgram;
import az.elixir.icurriculum.models.ProgramsModel;
import az.elixir.icurriculum.models.StudentProgram;

import java.util.List;

public interface ProgramService {
    void saveProgram(AddProgramDTO addProgramDTO);
    List<ProgramsModel> getAllProgram();

    void assignProgram(AssignProgramDTO assignProgramDTO);




    void unassignStudent(String studentId,  String  programId);
    void unassignInstructor(String studentId,  String  programId);





}
