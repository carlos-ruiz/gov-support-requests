package com.cruiz90.presidencia;

import com.cruiz90.presidencia.database.models.Application;
import com.cruiz90.presidencia.database.models.SocialProgram;
import com.cruiz90.presidencia.database.models.Town;
import com.cruiz90.presidencia.database.models.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ControlDeSolicitudes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User user = new User("Carlos", "cruiz", "gforce");
//        user.save();
        List<User> users = User.getAllUsers();
        System.out.println("Numero de usuarios: " + users.size());

        Town town = new Town("Tecacho");
        town.save();

        SocialProgram sp = new SocialProgram("Piso firme");
        sp.save();

        Application app = new Application("Cemento", 10, "Ramon Corona", null, new Date(new java.util.Date().getTime()), town, sp);
        app.save();

        List<Town> towns = Town.getAll();
        System.out.println("Numero de towns: " + towns.size());

        List<SocialProgram> sps = SocialProgram.getAll();
        System.out.println("Numero de sp: " + sps.size());

        List<Application> apps = Application.getAll();
        System.out.println("Numero de applications: " + apps.size());
    }

}
