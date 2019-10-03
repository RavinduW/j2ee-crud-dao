/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.controllers;

import com.pkg.dao.PostDao;
import com.pkg.daoImpl.PostDaoImpl;
import com.pkg.models.Post;
import com.pkg.serviceImpl.PostServiceImpl;
import com.pkg.services.PostService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ravindu_c
 */
public class PostController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int currentPageNumber,numberOfRows;
        
        if(request.getParameter("currentPageNumber") != null && request.getParameter("numberOfRows") != null){
            currentPageNumber = Integer.parseInt(request.getParameter("currentPageNumber"));
            numberOfRows = Integer.parseInt(request.getParameter("numberOfRows"));; 
        }else{
            currentPageNumber = 1;
            numberOfRows = 5;
        }
        
        PostService ps = new PostServiceImpl();
        int rows = ps.getNumberOfRows();
        System.out.println("numberOfRows"+rows);
        int numberOfPages = (int) Math.ceil((double)rows / (double)numberOfRows);
        System.out.println("numberOfPages"+numberOfPages);
        //System.out.println((int)Math.ceil((double)11/(double)5));
//        if (numberOfPages % numberOfRows < 0) {
//            numberOfPages++;
//            System.out.println("++"+numberOfPages);
//        }
        System.out.println("currentPageNumber"+currentPageNumber);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPageNumber", currentPageNumber);
        request.setAttribute("numberOfRows", numberOfRows);
        request.setAttribute("AllPosts",ps.getPosts(currentPageNumber,numberOfRows)); //set value for AllPosts variable
        RequestDispatcher rd = request.getRequestDispatcher("AllPosts.jsp"); //return the jsp
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title,postbody;
        PostService ps = new PostServiceImpl();
        
        title = request.getParameter("title");
        postbody = request.getParameter("postbody");
        
        Post pd = new Post(0,title,postbody);
        
        ps.createPost(pd);
        
        response.sendRedirect("/crud/PostController");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
