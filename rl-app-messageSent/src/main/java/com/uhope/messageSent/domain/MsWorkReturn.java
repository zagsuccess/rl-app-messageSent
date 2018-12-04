package com.uhope.messageSent.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ms_work_return")
public class MsWorkReturn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    @Column(name = "sent_reports_id")
    private String sentReportsId;

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
     * @return sent_reports_id
     */
    public String getSentReportsId() {
        return sentReportsId;
    }

    /**
     * @param sentReportsId
     */
    public void setSentReportsId(String sentReportsId) {
        this.sentReportsId = sentReportsId;
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
        MsWorkReturn other = (MsWorkReturn) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSentReportsId() == null ? other.getSentReportsId() == null : this.getSentReportsId().equals(other.getSentReportsId()))
            && (this.getReturnDate() == null ? other.getReturnDate() == null : this.getReturnDate().equals(other.getReturnDate()))
            && (this.getReturnReason() == null ? other.getReturnReason() == null : this.getReturnReason().equals(other.getReturnReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSentReportsId() == null) ? 0 : getSentReportsId().hashCode());
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
        sb.append(", sentReportsId=").append(sentReportsId);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", returnReason=").append(returnReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}