/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.User;
import form.FormMode;

/**
 *
 * @author Korisnik
 */
public class Session {
    private static Session instance;
    private User currentUser;
    private FormMode formMode;

    public Session() {
    }

    public Session(User currentUser, FormMode formMode) {
        this.currentUser = currentUser;
        this.formMode = formMode;
    }

    public static Session getInstance() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static void setInstance(Session aInstance) {
        instance = aInstance;
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public FormMode getFormMode() {
        return formMode;
    }

    public void setFormMode(FormMode formMode) {
        this.formMode = formMode;
    }
    
    
    
}
