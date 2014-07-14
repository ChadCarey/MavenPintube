


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chad
 */
class UserController {

    private Database database;
    
    public UserController() {
        database = new Database();
        database.connect();
    }     
    
    /**
     * this will check to see if the user and password are valid
     * @param user
     * @param pass
     * @return boolean
     */
    public boolean validLogin(String user, String pass) {
        return database.checkUser(user, pass);
    }

    /**
     * This checks to see f the user exists already to avoid duplicate names
     * @param user
     * @return 
     */
    public boolean userExists(String user) {
        return false;
    }
    
    /**
     * This will add a new user if the user doesn't already exist
     * @param user
     * @param pass
     * @return 
     */
    public boolean addUser(String user, String pass) {
        database.addUser(user, pass);
        return true;
    }
    
}
