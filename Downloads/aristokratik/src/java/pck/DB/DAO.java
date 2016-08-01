/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import pck.Entity.Yazi;

/**
 *
 * @author caner
 */
public class DAO {
    
    InitialContext initContext=null;
    Context envContext=null;
    DataSource ds=null;
    Connection conn=null;
    Statement statement=null;
            
    public DAO(){
        try {
            initContext = new InitialContext();                     
            envContext  = (Context)initContext.lookup("java:comp/env/");
            ds = (DataSource)envContext.lookup("jdbc/Test");
            conn = ds.getConnection();
            
            statement=conn.createStatement(); 
        } catch (NamingException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }                        
               
    }
    
    public ResultSet getYazilar(){                
        ResultSet set = null;
        try {
            set = statement.executeQuery("select *from yazilar");

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;        
    }
    
    public void saveYazi(String baslik,String yazi,String url) throws SQLException{
        statement.execute("insert into yazilar(baslik,yazi,link) values('"+baslik+"','"+yazi+"','"+url+"')");
    }
    
    public Yazi getEndYazi(){
        Yazi yazi=null;
        try {
            ResultSet set=null;
            
            try {
                set=statement.executeQuery("select baslik,yazi from yazilar ORDER BY idyazilar DESC LIMIT 1");
                set.next();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            yazi=new Yazi();
            yazi.setBaslik(set.getString("baslik"));
            yazi.setContent(set.getString("yazi"));           
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return yazi;
    }
    
    public void daoClose() throws SQLException, NamingException{
        statement.close();
        conn.close();

    }
}
