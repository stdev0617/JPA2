package me.stdev.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static me.stdev.commonweb.post.CommentSpecs.isBest;
import static me.stdev.commonweb.post.CommentSpecs.isGood;
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
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
//        comment.setComment("comment");
        comment.setComment("spring data jpa projection");
        comment.setPost(savedPost);
//        commentRepository.save(comment);
        comment.setUp(10);
        comment.setDown(1);

//        comments.findByPost_Id(savedPost.getId()).forEach(c -> {
//            System.out.println("=================");
//            System.out.println(c.getVotes());
//        });

        comments.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach(c -> {
            System.out.println("=================");
            System.out.println(c.getComment());
        });

//        Optional<Comment> byId = commentRepository.findById(1l);

//        comments.getById(1l);
//        System.out.println("=======================");
//        comments.findById(1l);

//        comments.findByPost_Id(1l);
    }

    @Test
    public void specs() {
        //comments.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()));
        Page<Comment> page = comments.findAll(isBest().or(isGood()), PageRequest.of(0,10));
    }

    @Test
    public void qbe() {
        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();

        Example<Comment> example = Example.of(prove, exampleMatcher);

        comments.findAll(example);
    }
}