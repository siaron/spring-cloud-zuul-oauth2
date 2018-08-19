package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "action")
public class ActionEntity {

    @Id
    @SequenceGenerator(name = "action_id_seq_gt", sequenceName = "action_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_id_seq_gt")
    private Long id;


    @Column(name = "action_code")
    private String actionCode;

    @Column(name = "u_id")
    private Long uId;

    @Column(name = "url")
    private String url;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
