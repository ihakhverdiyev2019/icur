package az.elixir.icurriculum.services;

import az.elixir.icurriculum.dtos.AddProgramDTO;
import az.elixir.icurriculum.dtos.AssignProgramDTO;
import az.elixir.icurriculum.models.*;
import az.elixir.icurriculum.repositories.InstructorProgramRepository;
import az.elixir.icurriculum.repositories.ProgramBadgeRepository;
import az.elixir.icurriculum.repositories.ProgramRepository;
import az.elixir.icurriculum.repositories.StudentProgramRepository;
import az.elixir.icurriculum.utils.SHAEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private InstructorProgramRepository instructorProgramRepository;

    @Autowired
    private StudentProgramRepository studentProgramRepository;


    @Autowired
    private UsersService usersService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private ProgramBadgeRepository programBadgeRepository;

    @Override
    public void saveProgram(AddProgramDTO addProgramDTO) {

        ProgramsModel programsModel = new ProgramsModel();
        programsModel.setProgramName(addProgramDTO.getProgramName());


        programsModel.setEndDate(addProgramDTO.getEndDate());
        programsModel.setStartDate(addProgramDTO.getStartDate());

        programRepository.save(programsModel);

        List<ProgramBadge> programBadges = new ArrayList<>();
        ProgramBadge programBadge =new ProgramBadge();
        System.out.println(addProgramDTO.getBadgeId());
        BadgesModel badgesModel = badgeService.getById(addProgramDTO.getBadgeId());
        programBadge.setBadgesModel(badgesModel);
        programBadge.setProgramsModel(programsModel);

        programBadgeRepository.save(programBadge);
        programBadges.add(programBadge);

        programsModel.setProgramBadge(programBadges);

        if (addProgramDTO.getInstructorsId() !=null ) {
            List<String> instructorId = addProgramDTO.getInstructorsId();
            List<InstructorProgram> instructors = new ArrayList<>();
            for (int i = 0; i < instructorId.size(); i++) {
                InstructorProgram instructorProgram = new InstructorProgram();
                UsersModel instructor = usersService.getById(instructorId.get(i));
                instructorProgram.setProgram(programsModel);
                instructorProgram.setInstructor(instructor);
                instructorProgramRepository.save(instructorProgram);
                instructors.add(instructorProgram);
            }
            programsModel.setInstructorProgram(instructors);

        }
        if (addProgramDTO.getStudentsId() != null) {

            List<String> studentId = addProgramDTO.getStudentsId();
            List<StudentProgram> studentsProgram = new ArrayList<>();
            for (int s = 0; s < studentId.size(); s++) {
                StudentProgram studentProgram = new StudentProgram();
                UsersModel student = usersService.getById(studentId.get(s));
                studentProgram.setProgram(programsModel);
                studentProgram.setStudent(student);
                studentProgram.setStatus(0);
                studentProgramRepository.save(studentProgram);
                studentsProgram.add(studentProgram);
            }
            programsModel.setStudentProgram(studentsProgram);
        }


    }

    @Override
    public List<ProgramsModel> getAllProgram() {

        return programRepository.findAll();
    }

    @Override
    public void assignProgram(AssignProgramDTO assignProgramDTO) {
        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(assignProgramDTO.getProgramId())).get();
        UsersModel user = usersService.getById(assignProgramDTO.getUserId());
        if(assignProgramDTO.getCategory().equals("Student")){
            StudentProgram studentProgram = new StudentProgram();
            studentProgram.setProgram(programsModel);
            studentProgram.setStudent(user);
            studentProgramRepository.save(studentProgram);
        }

        if (assignProgramDTO.getCategory().equals("Instructor")){
            InstructorProgram instructorProgram = new InstructorProgram();
            instructorProgram.setProgram(programsModel);
            instructorProgram.setInstructor(user);
            instructorProgramRepository.save(instructorProgram);
        }



    }



    @Override
    public void unassignStudent(String studentId, String programId) {
        UsersModel user = usersService.getById(studentId);
        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        StudentProgram studentProgram = studentProgramRepository.findByStudentAndProgram(user,programsModel);
        studentProgramRepository.delete(studentProgram);

    }

    @Override
    public void unassignInstructor(String studentId, String programId) {
        UsersModel user = usersService.getById(studentId);
        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        InstructorProgram instructorProgram = instructorProgramRepository.findByInstructorAndProgram(user,programsModel);
        instructorProgramRepository.delete(instructorProgram);


    }


}
