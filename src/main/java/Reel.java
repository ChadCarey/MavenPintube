/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brady
 */
public class Reel {
    int ID;
    String title;
    public Reel (int id1, String name1)
    {
        ID = id1;
        title = name1;
    }
    int getID()
    {
        return ID;
    }
    String getTitle()
    {
        return title;
    }
}

