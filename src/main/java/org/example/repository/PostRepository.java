package org.example.repository;

import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository {
    private final Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong();

    public List<Post> all() {
        if (postMap.isEmpty()) {
            return Collections.emptyList();
        } else {
            return postMap.values().stream().toList();
        }
    }

    public Optional<Post> getById(long id) {
        Post post = postMap.getOrDefault(id, null);
        return Optional.ofNullable(post);
    }

    public Post save(Post post) {
        if(post.getId() == 0) {
            long i = id.incrementAndGet();
            postMap.put(i, new Post(i, post.getContent()));
        } else {
            long currentId = post.getId();
            postMap.put(currentId, new Post(currentId, post.getContent()));
            id.incrementAndGet();
        }
            return post;
        }

        public void removeById ( long id){
            postMap.remove(id);
        }
    }
