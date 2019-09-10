/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.dao;

import com.pkg.models.Post;
import java.util.List;

/**
 *
 * @author Ravindu Weerasnghe
 */

//this provides the interface for the Data Access Object of Post.

public interface PostDao {
    public void addPost(Post post);
    public List<Post> getPosts();
    public List<Post> findById(int id);
    public void editPost(int id,Post post);
    public void deletePost(int id);
}
