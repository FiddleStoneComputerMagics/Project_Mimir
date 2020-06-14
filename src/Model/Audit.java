package Model;

public class Audit {

    private Integer id;

    private Integer departamentID;

    private String nameAudit;

    private String dataAudit;

    private String markAudit;

    private String recommendations;

   public Audit(Integer departamentID,String nameAudit,String dataAudit,String markAudit,String recommendations){
        this.departamentID = departamentID;
        this.nameAudit = nameAudit;
        this.dataAudit = dataAudit;
        this.markAudit = markAudit;
        this.recommendations = recommendations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartamentID() {
        return departamentID;
    }

    public void setDepartamentID(Integer departamentID) {
        this.departamentID = departamentID;
    }

    public String getNameAudit() {
        return nameAudit;
    }

    public void setNameAudit(String nameAudit) {
        this.nameAudit = nameAudit;
    }

    public String getDataAudit() {
        return dataAudit;
    }

    public void setDataAudit(String dataAudit) {
        this.dataAudit = dataAudit;
    }

    public String getMarkAudit() {
        return markAudit;
    }

    public void setMarkAudit(String markAudit) {
        this.markAudit = markAudit;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}
