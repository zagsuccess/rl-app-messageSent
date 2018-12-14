package com.uhope.messageSent.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 描述:
 * 报送统计表
 *
 * @author a4994
 * @create 2018-12-10 9:46
 */
public class MsSentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String region;

    @Column(name = "sent_number")
    private Integer sentNumber;

    @Column(name = "accept_number")
    private Integer acceptNumber;

    @Column(name = "return_number")
    private Integer returnNumber;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSentNumber() {
        return sentNumber;
    }

    public void setSentNumber(Integer sentNumber) {
        this.sentNumber = sentNumber;
    }

    public Integer getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(Integer acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public Integer getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(Integer returnNumber) {
        this.returnNumber = returnNumber;
    }
}
