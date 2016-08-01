/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import pck.DB.DAO;

/**
 *
 * @author caner
 */
public class Yazilar {
    
    private List<Yazi> listYazilar=new ArrayList<>();
    
    public List<Yazi> getYazilar(){
        DAO dao=new DAO();
        ResultSet resultSet=dao.getYazilar();
        
        try {
            while(resultSet.next()){
                Yazi yazi=new Yazi();
                yazi.setId(Integer.parseInt(resultSet.getString(1)));
                yazi.setBaslik(resultSet.getString(2));
                yazi.setContent(resultSet.getString(3));
                yazi.setLink(resultSet.getString(4));            
                listYazilar.add(yazi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Yazilar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(Yazilar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            dao.daoClose();
        } catch (SQLException ex) {
            Logger.getLogger(Yazilar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Yazilar.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listYazilar;    
    }
}

