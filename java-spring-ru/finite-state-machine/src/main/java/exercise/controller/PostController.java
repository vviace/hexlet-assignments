package exercise.controller;

import exercise.dto.PostDto;
import exercise.model.Post;
import exercise.model.PostState;
import exercise.repository.PostRepository;
import exercise.PostNotFoundException;
import exercise.UnprocessableEntityException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "")
    public Iterable<Post> getPosts() {
        return postRepository.findAllByState(PostState.PUBLISHED);
    }

    @PostMapping(path = "")
    public Post createPost(@RequestBody PostDto dto) {
        Post post = new Post();
        post.setBody(dto.getBody());
        post.setTitle(dto.getTitle());

        return postRepository.save(post);
    }

    @GetMapping(path = "/{id}")
    public Post getPost(@PathVariable long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));
    }

    @PatchMapping(path = "/{id}/publish")
    public Post publish(@PathVariable long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));

        // Вызывается событие publish
        // Если переход возможен, устанавливаем новое состояние и сохраняем пост
        if (!post.publish()) {
            throw new UnprocessableEntityException("Publishing is not possible");
        }
        return postRepository.save(post);
    }

    @PatchMapping(path = "/{id}/archive")
    public Post archive(@PathVariable long id) {
        // BEGIN
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        if (!post.archive()) {
            throw new UnprocessableEntityException("Archive is not possible");
        }
        return  postRepository.save(post);
        // END
    }
}
