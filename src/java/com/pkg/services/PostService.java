/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.services;

import com.pkg.models.Post;
import java.util.List;

/**
 *
 * @author ravindu_c
 */
//business service interface
public interface PostService {
    public List<Post>getPosts(int currentPageNumber,int numberOfRows);
    public List<Post>findById(int id);
    public void createPost(Post post);
    public void editPost(int id, Post post);
    public void deletePost(int id);
    public int getNumberOfRows();
    public List<Post>getAllPosts();
}
