/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.models;

/**
 *
 * @author Ravindu Weerasnghe
 */

//entity class of Post
public class Post {
    
    private int id;
    private String title;
    private String postbody;
    
    //constructor of Post
    public Post(int id, String title,String postbody){
        this.id = id;
        this.title = title;
        this.postbody = postbody;
    }

    //begins getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostbody() {
        return postbody;
    }

    public void setPostbody(String postbody) {
        this.postbody = postbody;
    }
    //end of getters and setters
     
}
