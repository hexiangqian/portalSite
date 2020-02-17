package zy.news.web.bean;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private String contractnum;

    private String projectnum;

    private String projecttoken;

    private Byte status;

    private Date planstart;

    private Date planstop;

    private String reallycomplete;

    private static final long serialVersionUID = 1L;

    public String getContractnum() {
        return contractnum;
    }

    public void setContractnum(String contractnum) {
        this.contractnum = contractnum == null ? null : contractnum.trim();
    }

    public String getProjectnum() {
        return projectnum;
    }

    public void setProjectnum(String projectnum) {
        this.projectnum = projectnum == null ? null : projectnum.trim();
    }

    public String getProjecttoken() {
        return projecttoken;
    }

    public void setProjecttoken(String projecttoken) {
        this.projecttoken = projecttoken == null ? null : projecttoken.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getPlanstart() {
        return planstart;
    }

    public void setPlanstart(Date planstart) {
        this.planstart = planstart;
    }

    public Date getPlanstop() {
        return planstop;
    }

    public void setPlanstop(Date planstop) {
        this.planstop = planstop;
    }

    public String getReallycomplete() {
        return reallycomplete;
    }

    public void setReallycomplete(String reallycomplete) {
        this.reallycomplete = reallycomplete == null ? null : reallycomplete.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", contractnum=").append(contractnum);
        sb.append(", projectnum=").append(projectnum);
        sb.append(", projecttoken=").append(projecttoken);
        sb.append(", status=").append(status);
        sb.append(", planstart=").append(planstart);
        sb.append(", planstop=").append(planstop);
        sb.append(", reallycomplete=").append(reallycomplete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}