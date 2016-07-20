/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck.Bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import pck.DB.DAO;
import pck.Entity.Yazi;
import pck.Entity.Yazilar;

/**
 *
 * @author caner
 */
@ManagedBean(name = "yazilarBean")
@SessionScoped
public class yazilarBean {
    
    List<Yazi> listeYazi=new ArrayList<>();
    String yaziContent;
    String yaziURL;
    String baslik;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    
    
    private String Content="";//yazılacak yazıların p:editör nesne içeriği

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    
    
    
    public String getYaziURL() {
        return yaziURL;
    }

    public void setYaziURL(String yaziURL) {
        this.yaziURL = yaziURL;
    }

    public String getYaziContent() {
        return yaziContent;
    }

    public void setYaziContent(String yaziContent) {
        this.yaziContent = yaziContent;
    }
    
    public List<Yazi> getListeYazi() { 
        
        listeYazi=new Yazilar().getYazilar();
        Collections.reverse(listeYazi);
       return listeYazi;
    }

    public void setListeYazi(List<Yazi> listeYazi) {
        this.listeYazi = listeYazi;
    }

    @PostConstruct
    public void pageLoad(){
        getListeYazi();
    }
    
    public String getYaziLink(Yazi yazi){
        yaziContent=yazi.getContent();
        yaziURL=yazi.getLink();
        baslik=yazi.getBaslik();

        return "blog.xhtml?faces-redirect=true&yazi="+yaziURL;
    }
    
    public void save() throws SQLException, NamingException{
        DAO dao=new DAO();
        dao.saveYazi(baslik,Content,yaziURL);
        dao.daoClose();        
    }
    
}
