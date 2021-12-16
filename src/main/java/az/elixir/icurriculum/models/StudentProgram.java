package az.elixir.icurriculum.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class StudentProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    private ProgramsModel program;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private UsersModel student;

    @OneToMany(mappedBy = "studentProgram")
    private List<StudentCollections> studentCollections;

    private int status;

    public StudentProgram() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProgramsModel getProgram() {
        return program;
    }

    public void setProgram(ProgramsModel program) {
        this.program = program;
    }

    public UsersModel getStudent() {
        return student;
    }

    public void setStudent(UsersModel student) {
        this.student = student;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StudentCollections> getStudentCollections() {
        return studentCollections;
    }

    public void setStudentCollections(List<StudentCollections> studentCollections) {
        this.studentCollections = studentCollections;
    }
}
