/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ser;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author ashish
 */
@ManagedBean(name = "call",eager=true)
@RequestScoped
public class Addstaff {
        
    Connection con;
    private int sid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    private String sname;
    private String semail;
    private long sphone;
    private String sadd;
    private String stype;
    private Date jdate;
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public long getSphone() {
        return sphone;
    }

    public void setSphone(long sphone) {
        this.sphone = sphone;
    }

    public String getSadd() {
        return sadd;
    }

    public void setSadd(String sadd) {
        this.sadd = sadd;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public Date getJdate() {
        return jdate;
    }

    public void setJdate(Date jdate) {
        this.jdate = jdate;
    }
    
    public void addstaff() throws SQLException, ClassNotFoundException
    
    { 
        try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
                int flag=0;

                Date date=new Date(); 

                java.sql.Date dor = new java.sql.Date(date.getTime());



                PreparedStatement pst=con.prepareStatement("insert into staff values(default,?,?,?,?,?,?)");
                pst.setString(1, sname);
                pst.setString(2, semail);
                pst.setLong(3,sphone);
                pst.setString(4, sadd);
                pst.setString(5, stype);
                pst.setDate(6, dor);
                pst.execute();

                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sucessfully Added!!!! " ));   


                     




              //fpprice=Float.parseFloat(pprice);



             //String exidate = dateFormat.format(exi);


         
        }      
        catch(Exception e)
        {
            e.printStackTrace();
        }
     
        
        }
        
    
    
    public List<Addstaff> listStaff()
        {
            
            List<Addstaff> list=new ArrayList();
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
                PreparedStatement pst=con.prepareStatement("select S_Id,S_Name,S_Email,S_Phone,S_Address,ServiceType,S_jdate from staff");

                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    Addstaff b=new Addstaff();
                    b.setSid(rs.getInt("S_Id"));
                    b.setSname(rs.getString("S_Name"));
                    b.setSemail(rs.getString("S_Email"));
                    b.setSphone(rs.getInt("S_Phone"));
                    b.setSadd(rs.getString("S_Address"));
                    b.setStype(rs.getString("ServiceType"));
                    b.setJdate(rs.getDate("S_jdate"));
                    list.add(b);

                }

                return list;
            }
            
            catch(Exception e)
            {
              e.printStackTrace();
              return null;
            }
            
        }
}
