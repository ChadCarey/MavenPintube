

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

    private static Database database;
    
    public UserController() {
        if (database == null) {
         database = new Database();   
        }
    }
    
    /**
     * this will check to see if the user and password are valid
     * @param user
     * @param pass
     * @return boolean
     */
    public boolean validLogin(String user, String pass) {
        database.connect();
        boolean check = database.checkUser(user, pass);
        database.disconnect();
        return check;
    }

    /**
     * This checks to see f the user exists already to avoid duplicate names
     * @param user
     * @return 
     */
    public boolean userExists(String user) {
    	database.connect();
        boolean check = database.userExists(user);
        database.disconnect();
        return check;
    }
    
    /**
     * This will add a new user if the user doesn't already exist
     * @param user
     * @param pass
     * @return 
     */
    public boolean addUser(String user, String pass) {
        database.connect();
        database.addUser(user, pass);
        database.disconnect();
        return true;
    }
    
}
