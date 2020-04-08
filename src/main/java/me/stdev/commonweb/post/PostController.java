package me.stdev.commonweb.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository posts;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) { // 도메인이 가진 변수와 다르므로 @PathVariable에 명시를 해줘야함.
        return post.getTitle();
    }

    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable) {
        return posts.findAll(pageable);
    }
}
