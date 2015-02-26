package io.github.gbi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String body;

    @OneToMany(mappedBy="message", fetch=FetchType.EAGER, cascade=CascadeType.MERGE, orphanRemoval=true)
    private List<MessageDetail> detailList = new ArrayList<>();

    protected Message() {

    }

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public List<MessageDetail> getDetailList() {
        return detailList;
    }
}
