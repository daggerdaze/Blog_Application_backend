package com.casetudy.blogging.controller;



import com.casetudy.blogging.exception.ResourceNotFoundException;
import com.casetudy.blogging.model.Blogs;
import com.casetudy.blogging.repository.BlogsRepository;
import com.casetudy.blogging.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class BlogsController {
    @Autowired
    BlogsRepository blogsRepository;

    @Autowired
    CurrentUserService currentUserService;

    @GetMapping("/blogs")
    public List<Blogs> getAllBlogs() {
        return blogsRepository.findAll();
    }

    @PostMapping("/addBlog")
    public String addBlog(@Valid @RequestBody Blogs blogs, Principal principal){
        return currentUserService.addPost(blogs,principal);
    }
    @PostMapping("/updateBlog")
    public String updateBlog(@Valid @RequestBody Blogs blog, Principal principal) {
        return currentUserService.addPost(blog, principal);
    }
    @GetMapping("/blogs/{id}")
    public Blogs getBlogById(@PathVariable(value = "id") Long blogid) {
        return blogsRepository.findById(blogid)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", blogid));
    }

    @GetMapping("/category/{type}")
    public List<Blogs> getProductByCategory(@PathVariable(value = "type") String productCategory) {
        return blogsRepository.findAllByCategory(productCategory);
    }

    @GetMapping("searchby/{str}")
    public List<Blogs> getSearch(@PathVariable(value = "str") String str) {
        return blogsRepository.findAllByContentContaining(str);
    }

    @GetMapping(path = "/userBlogs")
    public ArrayList<Blogs> showBlog(Principal principal) {
        return currentUserService.show(principal);
    }

    @GetMapping("/deleteBlog/{blogId}")
    public String deleteBlog(@PathVariable("blogId") Long id) {
        return currentUserService.delete(id);
    }

}