package com.casetudy.blogging.service;

import com.casetudy.blogging.model.Blogs;
import com.casetudy.blogging.model.Comments;
import com.casetudy.blogging.model.User;
import com.casetudy.blogging.repository.BlogsRepository;
import com.casetudy.blogging.repository.CommentRepository;
import com.casetudy.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogsRepository blogsRepository;

    @Autowired
    CommentRepository commentRepository;


    public String postComment(Principal principal,Long blogId,String comment) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Blogs> blogs = blogsRepository.findById(blogId);
        Comments comments = new Comments();
        comments.setUser(user.get());
        comments.setBlogs(blogs.get());
        comments.setComment(comment);
        commentRepository.save(comments);
        return "\"Comment Added\"";
    }
}
