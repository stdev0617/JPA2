package me.stdev.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post); // persist

        savedPost.setTitle("whiteShip");

        List<Post> all = postRepository.findAll();
        assertThat(all.size(), is(1));
    }

    @Test
    public void findByTitleStartsWith() {
        savePost();
        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size(), is(1));
    }

    private void savePost(){
        Post post = new Post();
        post.setTitle("Spring");
        postRepository.save(post); //persist
        postRepository.findAll();
    }

    @Test
    public void findByTitle() {
        savePost();
        List<Post> all = postRepository.findByTitle("Spring", Sort.by("pTitle"));
        assertThat(all.size(),is(1));
    }
}