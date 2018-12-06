package com.uhope.messageSent.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ms_week_return")
public class MsWeekReturn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @Column(name = "week_submission_id")
    private String weekSubmissionId;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "return_reason")
    private String returnReason;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return week_submission_id
     */
    public String getWeekSubmissionId() {
        return weekSubmissionId;
    }

    /**
     * @param weekSubmissionId
     */
    public void setWeekSubmissionId(String weekSubmissionId) {
        this.weekSubmissionId = weekSubmissionId;
    }

    /**
     * @return return_date
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return return_reason
     */
    public String getReturnReason() {
        return returnReason;
    }

    /**
     * @param returnReason
     */
    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MsWeekReturn other = (MsWeekReturn) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeekSubmissionId() == null ? other.getWeekSubmissionId() == null : this.getWeekSubmissionId().equals(other.getWeekSubmissionId()))
            && (this.getReturnDate() == null ? other.getReturnDate() == null : this.getReturnDate().equals(other.getReturnDate()))
            && (this.getReturnReason() == null ? other.getReturnReason() == null : this.getReturnReason().equals(other.getReturnReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeekSubmissionId() == null) ? 0 : getWeekSubmissionId().hashCode());
        result = prime * result + ((getReturnDate() == null) ? 0 : getReturnDate().hashCode());
        result = prime * result + ((getReturnReason() == null) ? 0 : getReturnReason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", weekSubmissionId=").append(weekSubmissionId);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", returnReason=").append(returnReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}