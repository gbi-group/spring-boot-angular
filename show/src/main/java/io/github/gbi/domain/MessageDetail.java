package io.github.gbi.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by laomie on 15-2-26.
 */
@Entity
@Table(name="message_detail")
public class MessageDetail implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="msg_id")
    private Long msgId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE, optional=false)
    @JoinColumn(name="msg_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Message message;

    public MessageDetail() {

    }

    public MessageDetail(Long msgId, String content, Message message) {
        this.msgId = msgId;
        this.content = content;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Long getMsgId() {
        return msgId;
    }

    public String getContent() {
        return content;
    }

    public Message getMessage() {
        return message;
    }
}
