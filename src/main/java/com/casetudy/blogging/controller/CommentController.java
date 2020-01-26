package com.casetudy.blogging.controller;

import com.casetudy.blogging.model.Blogs;
import com.casetudy.blogging.model.Comments;
import com.casetudy.blogging.repository.CommentRepository;
import com.casetudy.blogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/post/{id}")
    public String pcomment(@Valid @RequestBody Comments comments, Principal principal,@PathVariable(value = "id")Long id) {
        String str = comments.getComment();
        return commentService.postComment(principal,id,str);
    }

    @GetMapping("/view")
    public List<Comments> viewComment() {
        return commentRepository.findAll();
    }
}