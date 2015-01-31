package io.github.gbi.repository;

import io.github.gbi.domain.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
}
