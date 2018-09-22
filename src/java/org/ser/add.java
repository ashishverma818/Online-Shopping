package org.ser;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;
import java.util.Date;

@ManagedBean(name = "registeruser",eager=true)
@RequestScoped
public class add implements Serializable {
    private int uid;
    private String email;
    private String username;
    private String password;
    private String address;
    private int phoneno;
    private int pincode;
   

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
   
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(int phoneno) {
        this.phoneno = phoneno;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
        Connection con;
        
        
        
       
    public void adduser() throws SQLException, ClassNotFoundException
     
    { 
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
          int flag=0;
        
           Date date=new Date(); 
           
           java.sql.Date dor = new java.sql.Date(date.getTime());
           username="ashish";
           
           phoneno=5454545;
           
           address="mount mary";
           pincode=2122;
           
            
           PreparedStatement pst=con.prepareStatement("insert into user values(default,?,?,?,?,?,?,?)");
         
           PreparedStatement pst2;
           pst2 = con.prepareStatement("select user_email from user");
           
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setInt(3,phoneno);
            pst.setString(4,email);
            pst.setString(5,address);
            pst.setInt(6,pincode);
            pst.setDate(7,dor);
            String e;
            ResultSet rs=pst2.executeQuery();
                while(rs.next())
                {
                    e=rs.getString("user_email");
                    if(e.equals(email))
                    {
                        flag=1;
                        break;
                    }  
                }
           
               if(flag==1)
                {
                   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("User Already Exists !!!! " ));   
                   
                }  
               else
               {
                       pst.execute();
                  
                       FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("User Sucessfully Registered!!!! " ));   

                 
                }
                   
                   
               
        
         //fpprice=Float.parseFloat(pprice);
         
         
     
        //String exidate = dateFormat.format(exi);
        

         
        }      
        catch(Exception e)
        {
            e.printStackTrace();
        }
     
        
        }
        
    


/*  
public void onEdit(RowEditEvent event)
{
        int i,j=0;
        String id=((add) event.getObject()).getId();
        String name=((add) event.getObject()).getName();
        String compname=((add) event.getObject()).getCompname();
        
        String desc=((add) event.getObject()).getDesc();
       
        System.out.println(name+" "+compname+" "+desc);
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");    
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical","root","23456");
            Statement st=con.createStatement();
           i=st.executeUpdate("update newmedicine set medicine_name='"+name+"' , medicine_description='"+desc+"' , company_name='"+compname+"' where medicine_id='"+id+"'");
           j=st.executeUpdate("update medicines set medicine_name='"+name+"' , medicine_description='"+desc+"' , company_name='"+compname+"' where medicine_id='"+id+"'");
          
            if(i!=0||j!=0)    
            {
                
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Content Successfully Edited ....." ));
               FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml"); 
               System.out.println("successful update in product");        
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

*/
public List<add> listUser()
        {
            
            List<add> list=new ArrayList();
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/home","root","12345");
                PreparedStatement pst=con.prepareStatement("select user_id,user_name,user_pwd,user_phone,user_email,user_address,user_pincode from user");

                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    add m=new add();
                    m.setUid(rs.getInt("user_id"));
                    m.setUsername(rs.getString("user_name"));
                    m.setEmail(rs.getString("user_email"));
                    m.setAddress(rs.getString("user_address"));
                    m.setPhoneno(rs.getInt("user_phone"));
                    m.setPassword(rs.getString("user_pwd"));
                    m.setPincode(rs.getInt("user_pincode"));
                   // m.setId(rs.getString("medicine_id"));   

                    list.add(m);

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



 