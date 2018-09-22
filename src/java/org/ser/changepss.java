/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ser;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped


public class changepss implements Serializable
{
private String oldpass;
private String newpass;
private String confpass;
Connection con;

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getConfpass() {
        return confpass;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }

   
    public String password() throws SQLException
    {
        
         try
        {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","23456");
       
          // String oldpassv=oldpass;
           
           PreparedStatement pst=con.prepareStatement("select password from login where password=?");
           
           pst.setString(1,oldpass);
           System.out.print(oldpass);
           ResultSet rs=pst.executeQuery();
          // System.out.print(rs.getString("username"));
           if(rs.next())
           {
           if(newpass.equals(confpass))
               
           {
               
             PreparedStatement pst1=con.prepareStatement("update login set password=? where username=?");
           pst1.setString(1,newpass);
           pst1.setString(2,"ashver");
           pst1.executeUpdate();
           
          // FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO," Change Password Operation "," SUCCESSFUL !!!!! ");
        //RequestContext.getCurrentInstance().showMessageInDialog(message);
            
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Password Changed Successfully !!!! " ));
           FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "index.xhtml?faces-redirect=true";
           }
           else
               
           {
           con.close();
              FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("New Password Did Not Entered Correctly !!!! " ));
                        
           }
           
           }
           else
           {
                 con.close();
              FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" Old Password Did Not Match !!!! " ));
                         
           
           
           }
           
           
           
           
           
        
        
        }
         
         catch(Exception e)
         {
         System.out.println(e);
         }
   return null;
    }
   
    
    
}

