package az.elixir.icurriculum.dtos;

import java.util.List;

public class AddProgramDTO {

    private String programName;
    private String programDuration;
    private String startDate;
    private String endDate;

    private String badgeId;

    private List<String> instructorsId;

    private List<String> studentsId;

    public AddProgramDTO() {
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

    public List<String> getInstructorsId() {
        return instructorsId;
    }

    public void setInstructorsId(List<String> instructorsId) {
        this.instructorsId = instructorsId;
    }

    public List<String> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(List<String> studentsId) {
        this.studentsId = studentsId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }
}
