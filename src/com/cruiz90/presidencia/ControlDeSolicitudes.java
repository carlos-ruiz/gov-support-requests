package com.cruiz90.presidencia;

import com.cruiz90.presidencia.interfaces.MainView;

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
//        String[] towns = {"San Angel", "El Puente", "Huaniqueo", "Santa Fe", "La Presa", "Santiago", "La Puerta", "El Cerrito", "El 20", "Jaripitiro", "Las Piedras", "Huapeo", "Manza", "Ojo de Agua", "Tacupillo", "Tecacho", "San Pedro Puruatiro", "Tendeparacua", "Coeperio", "Jesús María"};
//        Town.deleteAll();
//        for (String name : towns) {
//            Town town = new Town(name);
//            town.save();
//        }

//        String[] socialPrograms = {"Piso Firme", "Laminas", "Amplicacion de vivienda", "Alambre de puas"};
//        for (String sp : socialPrograms) {
//            SocialProgram p = new SocialProgram(sp);
//            p.save();
//        }
        MainView main = new MainView();
        main.setVisible(true);
    }

}
