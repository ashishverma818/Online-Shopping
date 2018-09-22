/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "login",eager = true)
@SessionScoped
public class login {


    private String username;
    private String password;
    Connection con;
    //public String usr="";
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    boolean loginState=false;

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }
    
    
     public String action() throws SQLException 
    {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
           
          String usr=username;
          String pass=password;
          
          PreparedStatement pst=con.prepareStatement("select user_id,user_email,user_pwd from user where user_email=? and user_pwd=?");
          
          pst.setString(1,usr);
          pst.setString(2,pass);
          
          ResultSet rs=pst.executeQuery();
          
          if(rs.next())
          {
              loginState=true;
              HttpSession sess=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);         
              sess.setAttribute("username", username);
              sess.setAttribute("userid",rs.getInt("user_id"));
              sess.setAttribute("islogin", loginState);
              
              {  FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Welcome You are Logged In As:"+username ));
           FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);}
           //action1();1
              
             return "/Prj/index.xhtml?faces-redirect=true";
          }
          else
          {
            con.close();
              FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Wrong Username or Password - Please Try Again !!!! "));
                        
              //return "index.xhtml?faces-redirect=true";
              
          }
        
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
 
    }
    public String logout() throws SQLException,IOException
    {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Successfully Logged Out !!!! " ));
               FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
               //action1();

                  loginState=false;
                  HttpSession sess=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);         
                  sess.removeAttribute("username");
                  sess.setAttribute("islogin", loginState);
            return "/index.xhtml?faces-redirect=true";

    }   
    public login() {
    }
     public String action1() throws SQLException 
    {
        {
          try{
              String n;
              int m=0;
        Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","23456");
            PreparedStatement pst=con.prepareStatement("select medicine_name from newmedicine where medicine_quantity=?");
           pst.setInt(1,m);
            ResultSet rs=pst.executeQuery();
       
          while(rs.next())
            {
              n=rs.getString("medicine_name");
              // FacesMessage msg1;
              // msg1 = new FacesMessage(""+n+" is out of Stock .... ");
            //   FacesContext context = FacesContext.getCurrentInstance();
                  // context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,""+n+" is out of Stock .... ",null ));
       FacesContext.getCurrentInstance().addMessage("not", new FacesMessage(FacesMessage.SEVERITY_WARN, n+" is out of Stock","" ));
               // FacesContext.getCurrentInstance().addMessage("not", msg1);
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
   
            
           }
            con.close();
          }
          catch(Exception e)
            {
        e.printStackTrace();
          }
          }
        {
          try{
              String n;

              int m=1;
              int m1=5;
        Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","23456");
            PreparedStatement pst=con.prepareStatement("select medicine_name from newmedicine where medicine_quantity BETWEEN ? AND ?");
           pst.setInt(1,m);
           pst.setInt(2,m1);
            ResultSet rs=pst.executeQuery();
         
          while(rs.next())
            {
              n=rs.getString("medicine_name");
              // FacesMessage msg1;
              // msg1 = new FacesMessage(""+n+" is out of Stock .... ");
            //   FacesContext context = FacesContext.getCurrentInstance();
                  // context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,""+n+" is out of Stock .... ",null ));
       FacesContext.getCurrentInstance().addMessage("not", new FacesMessage(FacesMessage.SEVERITY_WARN, n+" is low on Stock","" ));
               // FacesContext.getCurrentInstance().addMessage("not", msg1);
             FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
   
            
           }
            con.close();
          }
          catch(Exception e)
            {
        e.printStackTrace();
          }
          }
    return null;}
}
