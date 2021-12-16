package az.elixir.icurriculum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProgramsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String programName;
    private String programDuration;
    private String startDate;
    private String endDate;

    @OneToMany(mappedBy="program")
    private List<StudentProgram> studentProgram;

    @OneToMany(mappedBy="program")
    private List<InstructorProgram> instructorProgram;


    @OneToMany(mappedBy="programsModel")
    private List<ProgramBadge> programBadge;



    public ProgramsModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDuration() {
        return programDuration;
    }

    public void setProgramDuration(String programDuration) {
        this.programDuration = programDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<StudentProgram> getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(List<StudentProgram> studentProgram) {
        this.studentProgram = studentProgram;
    }

    public List<InstructorProgram> getInstructorProgram() {
        return instructorProgram;
    }

    public void setInstructorProgram(List<InstructorProgram> instructorProgram) {
        this.instructorProgram = instructorProgram;
    }

    public List<ProgramBadge> getProgramBadge() {
        return programBadge;
    }

    public void setProgramBadge(List<ProgramBadge> programBadge) {
        this.programBadge = programBadge;
    }
}
