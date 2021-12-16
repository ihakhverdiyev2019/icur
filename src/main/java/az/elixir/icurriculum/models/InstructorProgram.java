package az.elixir.icurriculum.models;

import javax.persistence.*;
@Entity
public class InstructorProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    private ProgramsModel program;

    @ManyToOne
    @JoinColumn(name="instructor_id", nullable=false)
    private UsersModel instructor;

    public InstructorProgram() {
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

    public UsersModel getInstructor() {
        return instructor;
    }

    public void setInstructor(UsersModel instructor) {
        this.instructor = instructor;
    }
}
