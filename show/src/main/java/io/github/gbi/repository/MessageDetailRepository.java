package io.github.gbi.repository;

import io.github.gbi.domain.MessageDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by laomie on 15-2-27.
 */
public interface MessageDetailRepository extends PagingAndSortingRepository<MessageDetail, Long> {
}
