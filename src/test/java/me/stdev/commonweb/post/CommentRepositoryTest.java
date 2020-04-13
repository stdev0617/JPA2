package me.stdev.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository comments;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment() {
//        Post post = new Post();
//        post.setTitle("jpa");
//        Post savedPost = postRepository.save(post);
//
//        Comment comment = new Comment();
//        comment.setComment("comment");
//        comment.setPost(savedPost);
//        commentRepository.save(comment);

//        Optional<Comment> byId = commentRepository.findById(1l);

        comments.getById(1l);
        System.out.println("=======================");
        comments.findById(1l);
    }
}