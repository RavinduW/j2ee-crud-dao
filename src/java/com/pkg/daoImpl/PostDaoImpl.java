package com.pkg.daoImpl;


import com.pkg.dao.PostDao;
import com.pkg.models.Post;
import com.pkg.utils.ConnectionManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ravindu Weerasnghe
 */
public class PostDaoImpl implements PostDao {
    
    //keep a single copy
    static Connection currentConnection = null;
     
    public void addPost(Post post){
        PreparedStatement ps = null;
        String query = "INSERT INTO posts(title,postbody) VALUES (?,?)";
        
        try{
            
            //connect to the database
            currentConnection = ConnectionManager.getConnection();
           
            ps = currentConnection.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getPostbody());
            
            ps.executeUpdate();
            
            
        }catch(Exception e){
            System.out.println(e);  
        }finally{
            //all the connections,prepared statements,result sets are closed in hetre
            if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            if(ps != null){
                try{
                    ps.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }   
        }
    }//addPost method
    
    public List<Post> getPosts(){
       ResultSet rs = null;
       List <Post> posts = new LinkedList<>();
       String query = "SELECT * FROM posts";
       
       try{
           currentConnection = ConnectionManager.getConnection();
           rs = currentConnection.prepareStatement(query).executeQuery();
           
           while(rs.next()){
               Post p = new Post(rs.getInt(1),rs.getString(2),rs.getString(3));
               posts.add(p); 
           } 
       }catch(Exception e){
           System.out.println(e);
       }finally{
           if(currentConnection != null){
               try{
                   currentConnection.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }
           if(rs != null){
               try{
                   rs.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }
       }
       return posts;
    }//getPosts method
    
    public List<Post> findById(int id){
       
       ResultSet rs = null;
       PreparedStatement ps = null;
       List <Post> posts = new LinkedList<>();
       String query = "SELECT * FROM posts WHERE id=?";
       
       try{
           currentConnection = ConnectionManager.getConnection();
           ps = currentConnection.prepareStatement(query);
           ps.setInt(1, id);
           rs = ps.executeQuery();
           
           while(rs.next()){
              Post p = new Post(rs.getInt(1),rs.getString(2),rs.getString(3));
              posts.add(p);
           }
           
       }catch(Exception e){
           System.out.println(e);
       }finally{
           if(currentConnection != null){
               try{
                   currentConnection.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }
           
           if(ps != null){
               try{
                   ps.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }
           
           if(rs != null){
               try{
                   rs.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }           
       }
       
       return posts;
    }//findById method
    
    public void editPost(int id,Post post){
        
        PreparedStatement ps= null;
        String query = "UPDATE posts SET title=?,postbody=? WHERE id=?";
        
        try{
            currentConnection = ConnectionManager.getConnection();
            ps = currentConnection.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getPostbody());
            ps.setInt(3, id);
            
            ps.executeUpdate();
                    
        }catch(Exception e){
            System.out.println(e);        
        }finally{
            if(ps != null){
                try{
                   ps.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }//editPost method
    
    public void deletePost(int id){
        PreparedStatement ps= null;
        String query = "DELETE FROM posts WHERE id=?";

        try{
            
            currentConnection = ConnectionManager.getConnection();
            ps = currentConnection.prepareStatement(query);
            ps.setInt(1,id);
            
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }//deletePost method
    
}//PostDaoImpl class
