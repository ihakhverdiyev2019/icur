package az.elixir.icurriculum.models;

import javax.persistence.*;

@Entity
public class ProgramBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    private ProgramsModel programsModel;

    @ManyToOne
    @JoinColumn(name="badge_id", nullable=false)
    private BadgesModel badgesModel;

    public ProgramBadge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProgramsModel getProgramsModel() {
        return programsModel;
    }

    public void setProgramsModel(ProgramsModel programsModel) {
        this.programsModel = programsModel;
    }

    public BadgesModel getBadgesModel() {
        return badgesModel;
    }

    public void setBadgesModel(BadgesModel badgesModel) {
        this.badgesModel = badgesModel;
    }
}
