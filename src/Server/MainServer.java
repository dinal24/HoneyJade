/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Vimukthi Weerasiri
 */
public class MainServer {

    private static MainServer mainServer;

    private MainServer() {

    }

    public static MainServer getInstance() {
        if (mainServer == null) {
            return mainServer;
        }
        return mainServer;
    }

    public int findRouteIdByImei(String imie) {
        return 0;
    }

    public void executeLocation(LocationBox locationbox) {
        int routeid = findRouteIdByImei(locationbox.getImei());
        Trip.getInstance(routeid).execute(locationbox);

    }

    public Trip getInstanceOfTrip() {
        return null;
    }

}
