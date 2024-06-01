package belajar.jdbc.database;

import belajar.jdbc.database.entity.Comment;
import belajar.jdbc.database.repository.CommentRepository;
import belajar.jdbc.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("arbi@gmail.com", "Halo");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(9306);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment notFound = commentRepository.findById(10000);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comment> commentList = commentRepository.findAll();
        System.out.println(commentList.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> commentList = commentRepository.findAllByEmail("arbi@gmail.com");
        System.out.println(commentList.size());
    }
}
