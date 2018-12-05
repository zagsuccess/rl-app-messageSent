package com.uhope.messageSent.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ms_week_dynamic")
public class MsWeekDynamic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String title;

    private String direction;

    @Column(name = "sent_region")
    private String sentRegion;

    @Column(name = "sent_time_start")
    private String sentTimeStart;

    @Column(name = "sent_time_end")
    private String sentTimeEnd;

    @Column(name = "begin_time")
    private String beginTime;

    private String deadline;

    @Column(name = "weather_sent")
    private Integer weatherSent;

    @Column(name = "weather_accept")
    private Integer weatherAccept;

    private String initiator;

    @Column(name = "send_region")
    private String sendRegion;

    @Column(name = "accept_region")
    private String acceptRegion;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return sent_region
     */
    public String getSentRegion() {
        return sentRegion;
    }

    /**
     * @param sentRegion
     */
    public void setSentRegion(String sentRegion) {
        this.sentRegion = sentRegion;
    }

    /**
     * @return sent_time_start
     */
    public String getSentTimeStart() {
        return sentTimeStart;
    }

    /**
     * @param sentTimeStart
     */
    public void setSentTimeStart(String sentTimeStart) {
        this.sentTimeStart = sentTimeStart;
    }

    /**
     * @return sent_time_end
     */
    public String getSentTimeEnd() {
        return sentTimeEnd;
    }

    /**
     * @param sentTimeEnd
     */
    public void setSentTimeEnd(String sentTimeEnd) {
        this.sentTimeEnd = sentTimeEnd;
    }

    /**
     * @return deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * @return initiator
     */
    public String getInitiator() {
        return initiator;
    }

    /**
     * @param initiator
     */
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    /**
     * @return send_region
     */
    public String getSendRegion() {
        return sendRegion;
    }

    /**
     * @param sendRegion
     */
    public void setSendRegion(String sendRegion) {
        this.sendRegion = sendRegion;
    }

    /**
     * @return accept_region
     */
    public String getAcceptRegion() {
        return acceptRegion;
    }

    /**
     * @param acceptRegion
     */
    public void setAcceptRegion(String acceptRegion) {
        this.acceptRegion = acceptRegion;
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
        MsWeekDynamic other = (MsWeekDynamic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSentRegion() == null ? other.getSentRegion() == null : this.getSentRegion().equals(other.getSentRegion()))
            && (this.getSentTimeStart() == null ? other.getSentTimeStart() == null : this.getSentTimeStart().equals(other.getSentTimeStart()))
            && (this.getSentTimeEnd() == null ? other.getSentTimeEnd() == null : this.getSentTimeEnd().equals(other.getSentTimeEnd()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
            && (this.getSendRegion() == null ? other.getSendRegion() == null : this.getSendRegion().equals(other.getSendRegion()))
            && (this.getAcceptRegion() == null ? other.getAcceptRegion() == null : this.getAcceptRegion().equals(other.getAcceptRegion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSentRegion() == null) ? 0 : getSentRegion().hashCode());
        result = prime * result + ((getSentTimeStart() == null) ? 0 : getSentTimeStart().hashCode());
        result = prime * result + ((getSentTimeEnd() == null) ? 0 : getSentTimeEnd().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
        result = prime * result + ((getSendRegion() == null) ? 0 : getSendRegion().hashCode());
        result = prime * result + ((getAcceptRegion() == null) ? 0 : getAcceptRegion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", sentRegion=").append(sentRegion);
        sb.append(", sentTimeStart=").append(sentTimeStart);
        sb.append(", sentTimeEnd=").append(sentTimeEnd);
        sb.append(", deadline=").append(deadline);
        sb.append(", initiator=").append(initiator);
        sb.append(", sendRegion=").append(sendRegion);
        sb.append(", acceptRegion=").append(acceptRegion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getWeatherSent() {
        return weatherSent;
    }

    public void setWeatherSent(Integer weatherSent) {
        this.weatherSent = weatherSent;
    }

    public Integer getWeatherAccept() {
        return weatherAccept;
    }

    public void setWeatherAccept(Integer weatherAccept) {
        this.weatherAccept = weatherAccept;
    }
}