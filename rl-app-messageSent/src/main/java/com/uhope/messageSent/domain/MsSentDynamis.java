package com.uhope.messageSent.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ms_sent_dynamis")
public class MsSentDynamis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String weekid;

    private String title;

    private String region;

    private String deadline;

    private String initiator;

    @Column(name = "begin_time")
    private String beginTime;

    @Column(name = "accessory_url")
    private String accessoryUrl;

    @Column(name = "sent_state")
    private Integer sentState;

    @Column(name = "accept_state")
    private Integer acceptState;

    @Column(name = "patrol_condition")
    private String patrolCondition;

    @Column(name = "meeting_condition")
    private String meetingCondition;

    @Column(name = "problem_solving_condition")
    private String problemSolvingCondition;

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
     * @return weekid
     */
    public String getWeekid() {
        return weekid;
    }

    /**
     * @param weekid
     */
    public void setWeekid(String weekid) {
        this.weekid = weekid;
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
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
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
     * @return accessory_url
     */
    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    /**
     * @param accessoryUrl
     */
    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    /**
     * @return sent_state
     */
    public Integer getSentState() {
        return sentState;
    }

    /**
     * @param sentState
     */
    public void setSentState(Integer sentState) {
        this.sentState = sentState;
    }

    /**
     * @return accept_state
     */
    public Integer getAcceptState() {
        return acceptState;
    }

    /**
     * @param acceptState
     */
    public void setAcceptState(Integer acceptState) {
        this.acceptState = acceptState;
    }

    /**
     * @return patrol_condition
     */
    public String getPatrolCondition() {
        return patrolCondition;
    }

    /**
     * @param patrolCondition
     */
    public void setPatrolCondition(String patrolCondition) {
        this.patrolCondition = patrolCondition;
    }

    /**
     * @return meeting_condition
     */
    public String getMeetingCondition() {
        return meetingCondition;
    }

    /**
     * @param meetingCondition
     */
    public void setMeetingCondition(String meetingCondition) {
        this.meetingCondition = meetingCondition;
    }

    /**
     * @return problem_solving_condition
     */
    public String getProblemSolvingCondition() {
        return problemSolvingCondition;
    }

    /**
     * @param problemSolvingCondition
     */
    public void setProblemSolvingCondition(String problemSolvingCondition) {
        this.problemSolvingCondition = problemSolvingCondition;
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
        MsSentDynamis other = (MsSentDynamis) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeekid() == null ? other.getWeekid() == null : this.getWeekid().equals(other.getWeekid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
            && (this.getAccessoryUrl() == null ? other.getAccessoryUrl() == null : this.getAccessoryUrl().equals(other.getAccessoryUrl()))
            && (this.getSentState() == null ? other.getSentState() == null : this.getSentState().equals(other.getSentState()))
            && (this.getAcceptState() == null ? other.getAcceptState() == null : this.getAcceptState().equals(other.getAcceptState()))
            && (this.getPatrolCondition() == null ? other.getPatrolCondition() == null : this.getPatrolCondition().equals(other.getPatrolCondition()))
            && (this.getMeetingCondition() == null ? other.getMeetingCondition() == null : this.getMeetingCondition().equals(other.getMeetingCondition()))
            && (this.getProblemSolvingCondition() == null ? other.getProblemSolvingCondition() == null : this.getProblemSolvingCondition().equals(other.getProblemSolvingCondition()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeekid() == null) ? 0 : getWeekid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
        result = prime * result + ((getAccessoryUrl() == null) ? 0 : getAccessoryUrl().hashCode());
        result = prime * result + ((getSentState() == null) ? 0 : getSentState().hashCode());
        result = prime * result + ((getAcceptState() == null) ? 0 : getAcceptState().hashCode());
        result = prime * result + ((getPatrolCondition() == null) ? 0 : getPatrolCondition().hashCode());
        result = prime * result + ((getMeetingCondition() == null) ? 0 : getMeetingCondition().hashCode());
        result = prime * result + ((getProblemSolvingCondition() == null) ? 0 : getProblemSolvingCondition().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", weekid=").append(weekid);
        sb.append(", title=").append(title);
        sb.append(", region=").append(region);
        sb.append(", deadline=").append(deadline);
        sb.append(", initiator=").append(initiator);
        sb.append(", accessoryUrl=").append(accessoryUrl);
        sb.append(", sentState=").append(sentState);
        sb.append(", acceptState=").append(acceptState);
        sb.append(", patrolCondition=").append(patrolCondition);
        sb.append(", meetingCondition=").append(meetingCondition);
        sb.append(", problemSolvingCondition=").append(problemSolvingCondition);
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
}