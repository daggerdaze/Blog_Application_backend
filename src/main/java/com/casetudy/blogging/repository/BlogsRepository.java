package com.casetudy.blogging.repository;

import com.casetudy.blogging.model.Blogs;
import com.casetudy.blogging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long> {

    public List<Blogs>findAllByCategory(String cat);
    public List<Blogs>findAllByContentContaining(String str);
    public ArrayList<Blogs> findAllByUser(Optional<User> users);

    public void deleteById(Long id);
}