package az.elixir.icurriculum.models;

import javax.persistence.*;

@Entity
public class StudentCollections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="badge_id", nullable=false)
    private BadgesModel badgesModel;

    @ManyToOne
    @JoinColumn(name="student_program_id", nullable=false)
    private StudentProgram studentProgram;

    public StudentCollections() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BadgesModel getBadgesModel() {
        return badgesModel;
    }

    public void setBadgesModel(BadgesModel badgesModel) {
        this.badgesModel = badgesModel;
    }

    public StudentProgram getStudentProgram() {
        return studentProgram;
    }

    public void setStudentProgram(StudentProgram studentProgram) {
        this.studentProgram = studentProgram;
    }
}
