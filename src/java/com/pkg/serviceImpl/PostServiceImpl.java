/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.serviceImpl;

import com.pkg.dao.PostDao;
import com.pkg.daoImpl.PostDaoImpl;
import com.pkg.models.Post;
import com.pkg.services.PostService;
import java.util.List;

/**
 *
 * @author ravindu_c
 */

//implementations of business services

public class PostServiceImpl implements PostService {
    PostDao pd = new PostDaoImpl();
    
    public void createPost(Post post){        
        pd.addPost(post);
    }
    
    public List<Post> getPosts(int currentPageNumber,int numberOfRows){
        return pd.getPosts(currentPageNumber,numberOfRows);
    }
    
    public List<Post> findById(int id){
        return pd.findById(id);
    }
    
    public void editPost(int id,Post post){
        pd.editPost(id, post);
    }
    
    public void deletePost(int id){
        pd.deletePost(id);
    }
    
    public int getNumberOfRows(){
        return pd.getNumberOfRows();
    }
    
    public List<Post> getAllPosts(){
        return pd.getAllPosts();
    }
    
}
