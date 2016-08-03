/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck.Bean;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import pck.DB.DAO;
import pck.Entity.Yazi;

/**
 *
 * @author caner
 */
@ManagedBean(name = "blogBean")
@RequestScoped
public class blogBean {
private Yazi yazi=new Yazi();

    public Yazi getYazi() {
        return yazi;
    }

    public void setYazi(Yazi yazi) {
        this.yazi = yazi;
    }
    
    @PostConstruct
    public void onLoad(){
        ServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DAO dao=new DAO();
        String link=req.getParameter("yazi");
        yazi=dao.getYazi(link);
        try {
            dao.daoClose();
        } catch (SQLException ex) {
            Logger.getLogger(blogBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(blogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
