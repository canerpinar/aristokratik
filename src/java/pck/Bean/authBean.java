/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck.Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;

/**
 *
 * @author caner
 */
@ManagedBean(name = "authBean")
@RequestScoped
public class authBean {
    private String username;
    private String password;

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
 
    
    public String girisControl(){
        return "/index.xhtml";
    }
     public String girisiKontrolEt(){
        
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            currentUser.login(token);

        if(username.equals("mastercan"))
        return "/faces/admin/admin.xhtml?faces-redirect=true";
        else
        return "bilgilendirme.xhtml?faces-redirect=true";
    }
    
    public String logout() {
 
        Subject currentUser = SecurityUtils.getSubject();
        
        
            currentUser.logout();               
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
