/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ser;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ashish
 */
@ManagedBean(name = "book",eager=true)
@RequestScoped
public class bookcall implements Serializable
{
    Connection con;
    private int serid;
    private int sercid;
    private String sertype;
    private String serdesc;
    private Date serdate;
    private int serstaffid;
    private String serstatus;
    HttpSession sess=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
    public int getSerid() {
        return serid;
    }

    public void setSerid(int serid) {
        this.serid = serid;
    }

    public int getSercid() {
        return sercid;
    }

    public void setSercid(int sercid) {
        this.sercid = sercid;
    }

    public String getSertype() {
        return sertype;
    }

    public void setSertype(String sertype) {
        this.sertype = sertype;
    }

    public String getSerdesc() {
        return serdesc;
    }

    public void setSerdesc(String serdesc) {
        this.serdesc = serdesc;
    }

    public Date getSerdate() {
        return serdate;
    }

    public void setSerdate(Date serdate) {
        this.serdate = serdate;
    }

    public int getSerstaffid() {
        return serstaffid;
    }

    public void setSerstaffid(int serstaffid) {
        this.serstaffid = serstaffid;
    }

    public String getSerstatus() {
        return serstatus;
    }

    public void setSerstatus(String serstatus) {
        this.serstatus = serstatus;
    }
    @PostConstruct
    public void init() throws IOException
    {
       
    }
     public void insertservice() throws SQLException, ClassNotFoundException
    
    { 
        try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
                 if(sess==null)
                {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml"); 
            
                }
                 else
                 {
                int flag=0;
                Date date=new Date(); 
               // String s=type;
                java.sql.Date dos = new java.sql.Date(date.getTime());
                       
                String un=sess.getAttribute("username").toString();
                String scid=(sess.getAttribute("userid")).toString();
                int cid=Integer.parseInt(scid);

                PreparedStatement pst=con.prepareStatement("insert into services values(default,?,?,?,?,?,?)");
                pst.setInt(1, cid);
                pst.setString(2, "Electrician");
                pst.setString(3,"light not working");
                pst.setDate(4, dos);
                pst.setInt(5,1);
                pst.setString(6, "pending");
                pst.execute();

                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sucessfully Added!!!! " ));   


                 }




              //fpprice=Float.parseFloat(pprice);



             //String exidate = dateFormat.format(exi);


         
        }      
        catch(Exception e)
        {
            e.printStackTrace();
        }
     
        
        }
        
    
    
    
}
