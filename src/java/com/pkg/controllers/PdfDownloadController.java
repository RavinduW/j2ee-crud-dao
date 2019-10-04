/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.controllers;

import com.pkg.models.Post;
import com.pkg.serviceImpl.PostServiceImpl;
import com.pkg.services.PostService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author ravindu_c
 */
public class PdfDownloadController extends HttpServlet {

    private String DOWNLOAD_FILE_NAME = "postReport.pdf"; //file name of the downloadable file
    private String FILE_TYPE = "application/pdf"; //file type of the file(pdf)
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
        try {
           generatePdfReport(request,response); //generate and download 
        }catch(Exception e){
            System.out.println("Exception in PdfDownloadController => "+e);
        }
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
        processRequest(request, response);
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

    public void generatePdfReport(HttpServletRequest request,HttpServletResponse response){
    
    List<Post> postData = new ArrayList<>();
    String reportPath;
    OutputStream outputStream = null;
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JRDataSource reportSource;
    Map reportParameters;
    PostService ps = new PostServiceImpl();
    
    try {

      reportPath = request.getServletContext().getRealPath("/reports") + "\\report1.jrxml";

      reportParameters = new HashMap();
      reportParameters.put("title", "Post Feeds");

      jasperDesign = JRXmlLoader.load(reportPath);
      jasperReport = JasperCompileManager.compileReport(jasperDesign);

      postData = ps.getAllPosts();
      reportSource = new JRBeanCollectionDataSource(postData); //set the database values to the reportSource

      //byteStream
      byte[] byteStream;
      byteStream = JasperRunManager.runReportToPdf(jasperReport,reportParameters, reportSource);
      
      //response
      response.setHeader("Content-Disposition", "attachement; filename=" + DOWNLOAD_FILE_NAME);
      response.setContentType(FILE_TYPE);
      response.setContentLength(byteStream.length);
      
      //outputstream
      outputStream = response.getOutputStream();
     
      //byteStream = data, 0 = starting offset, byteStream.length = length
      outputStream.write(byteStream, 0, byteStream.length);

    } catch (JRException ex) {
      Logger.getLogger(PdfDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
      e.printStackTrace();
    }finally{   
        try {
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(PdfDownloadController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
  }
    
    
    
    
}

