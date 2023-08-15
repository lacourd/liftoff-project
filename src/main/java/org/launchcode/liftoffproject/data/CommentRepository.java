package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
