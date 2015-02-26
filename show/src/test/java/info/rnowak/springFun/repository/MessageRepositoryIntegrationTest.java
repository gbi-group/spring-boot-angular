package info.rnowak.springFun.repository;

import io.github.gbi.SpringFun;
import io.github.gbi.domain.Message;
import io.github.gbi.domain.MessageDetail;
import io.github.gbi.repository.MessageDetailRepository;
import io.github.gbi.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringFun.class)
public class MessageRepositoryIntegrationTest {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Test
    public void shouldFindAllMessages() {
        //when:
        Page<Message> messages = messageRepository.findAll(new PageRequest(0, 3));
        //then:
        assertThat(messages.getTotalElements()).isEqualTo(10L);
    }

    @Test
    public void shouldFindMessageWithId() {
        //when:
        Message message = messageRepository.findOne(0L);
        //then:
        assertThat(message).isNotNull();
        assertThat(message.getHeader()).isNotNull().isEqualTo("Header1");
        // message detail
        List<MessageDetail> detailList = message.getDetailList();
        assertThat(detailList.size()).isEqualTo(2);
        MessageDetail detail2 = detailList.get(1);
        assertThat(detail2.getContent()).isEqualTo("Header1-Detail2");
    }

    @Test
    public void shouldReturnNullWhenMessageWithIdDoesNotExist() {
        //when:
        Message message = messageRepository.findOne(999L);
        //then:
        assertThat(message).isNull();
    }

    @Test
    public void shouldInsertMessageDetail() {
        Message message = messageRepository.findOne(5L);
        MessageDetail detail = new MessageDetail(message.getId(), "Header6-Detail2", message);
        //message.getDetailList().add(detail);
        messageDetailRepository.save(detail);
        message = messageRepository.findOne(5L);
        assertThat(message.getDetailList().size()).isEqualTo(2);
        MessageDetail detail2 = message.getDetailList().get(1);
        assertThat(detail2.getContent()).isEqualTo("Header6-Detail2");
    }

    @Test
    public void shouldInsertMessage() {
        Message message = new Message("Header11", "laomie");
        messageRepository.save(message);
        assertThat(message.getId()).isEqualTo(10L);
        messageRepository.delete(message.getId());
    }

    // TODO: 新增message和messageDetail时的一致性测试（原子事务）
}
