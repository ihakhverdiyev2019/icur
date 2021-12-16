package az.elixir.icurriculum.services;

import az.elixir.icurriculum.models.*;
import az.elixir.icurriculum.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private ProgramBadgeRepository programBadgeRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private StudentProgramRepository studentProgramRepository;

    @Autowired
    private StudentCollectionRepository studentCollectionRepository;


    @Autowired
    private UsersService usersService;

    @Override
    public BadgesModel saveBadge(BadgesModel badgesModel) {
        return badgeRepository.save(badgesModel);
    }

    @Override
    public List<BadgesModel> getAllBadge() {
        return badgeRepository.findAll();
    }

    @Override
    public List<BadgesModel> getAllBadgeOfStudents(String id) {
        UsersModel usersModel = usersService.getById(id);

        List<BadgesModel> badgesModels = new ArrayList<>();
        List<StudentProgram> studentPrograms = studentProgramRepository.findAllByStudentAndStatus(usersModel,1);
        for(int i = 0 ; i<studentPrograms.size();i++){
           badgesModels.add(studentPrograms.get(i).getProgram().getProgramBadge().get(0).getBadgesModel());
        }



        return badgesModels ;
    }

    @Override
    public BadgesModel getAllBadgeOfStudentsAndBadge(String sid, String bid) {
        UsersModel usersModel = usersService.getById(sid);

        BadgesModel badgesModel = null;
        List<StudentProgram> studentPrograms = studentProgramRepository.findAllByStudentAndStatus(usersModel,1);
        for(int i = 0 ; i<studentPrograms.size();i++){
            if(studentPrograms.get(i).getProgram().getProgramBadge().get(0).getBadgesModel().getId()==Integer.parseInt(bid)){
                badgesModel = studentPrograms.get(i).getProgram().getProgramBadge().get(0).getBadgesModel();
            }

        }
        return badgesModel;
    }

    @Override
    public BadgesModel getById(String id) {
        return badgeRepository.findById(Integer.parseInt(id)).get();
    }

    @Override
    public void removeBadge(int id) {
        BadgesModel badgesModel = badgeRepository.findById(id).get();
        List<ProgramBadge> programBadges = programBadgeRepository.findAllByBadgesModel(badgesModel);
        if(programBadges!=null){
            for(int i = 0 ; i<programBadges.size();i++){
                programBadgeRepository.delete(programBadges.get(i));
            }
        }
        badgeRepository.delete(badgesModel);
    }



    @Override
    public void enableTheCollection(String studentId, String programId) {
        StudentCollections studentCollections = new StudentCollections();

        UsersModel usersModel = usersService.getById(studentId);
        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        StudentProgram studentProgram = studentProgramRepository.findByStudentAndProgram(usersModel,programsModel);
        studentProgram.setStatus(1);



        studentCollections.setBadgesModel(programsModel.getProgramBadge().get(0).getBadgesModel());
        studentCollections.setStudentProgram(studentProgram);
        studentProgramRepository.save(studentProgram);
        studentCollectionRepository.save(studentCollections);



    }

    @Override
    public void enableAllCollection(String programId) {

        List<StudentCollections> studentCollections = new ArrayList<>();



        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        List<StudentProgram> studentProgram = studentProgramRepository.findAllByProgramAndStatus(programsModel,0);
        for (int i = 0 ; i<studentProgram.size();i++){
            StudentCollections studentCollection = new StudentCollections();
            studentCollection.setBadgesModel(programsModel.getProgramBadge().get(0).getBadgesModel());
            studentCollection.setStudentProgram(studentProgram.get(i));
            studentProgram.get(i).setStatus(1);
            studentProgramRepository.save(studentProgram.get(i));
            studentCollections.add(studentCollection);

        }

        studentCollectionRepository.saveAll(studentCollections);





    }






    @Override
    public void disableTheCollection(String studentId, String programId) {
        UsersModel usersModel = usersService.getById(studentId);
        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        StudentProgram studentProgram = studentProgramRepository.findByStudentAndProgram(usersModel,programsModel);
        StudentCollections studentCollections = studentCollectionRepository.findByBadgesModelAndStudentProgram(programsModel.getProgramBadge().get(0).getBadgesModel(),studentProgram);
        studentProgram.setStatus(0);
        studentProgramRepository.save(studentProgram);
        studentCollectionRepository.delete(studentCollections);
    }






    @Override
    public void disableAllCollection(String programId) {

        List<StudentCollections> studentCollections = new ArrayList<>();



        ProgramsModel programsModel = programRepository.findById(Integer.parseInt(programId)).get();
        List<StudentProgram> studentProgram = studentProgramRepository.findAllByProgramAndStatus(programsModel,1);
        for (int i = 0 ; i<studentProgram.size();i++){
            StudentCollections studentCollection = new StudentCollections();
            studentCollection.setBadgesModel(programsModel.getProgramBadge().get(0).getBadgesModel());
            studentCollection.setStudentProgram(studentProgram.get(i));
            studentProgram.get(i).setStatus(0);
            studentProgramRepository.save(studentProgram.get(i));
            studentCollections.add(studentCollection);

        }

        studentCollectionRepository.deleteAll(studentCollections);

    }

}
