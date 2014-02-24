/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.lang.Thread.sleep;
import java.util.logging.Level;

/**
 *
 * @author Vimukthi Weerasiri
 */
public class TK103DeviceHandler {

    private static TK103DeviceHandler tK103DeviceHandler;

    private TK103DeviceHandler() {

    }

    public static TK103DeviceHandler getInstance() {
        if (tK103DeviceHandler == null) {
            return tK103DeviceHandler;
        }
        return tK103DeviceHandler;
    }

    //starts listening to the port and to recieve data
    public void executeServer() {
        //set the port number
        int port = 4480;

        try {
            ServerSocket welcomeSocket = new ServerSocket(port);
            //until shut down
            while (true) {
                //making the socket connection
                System.out.println("waiting for a connection...");
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println(connectionSocket.getInetAddress().toString() + " connected");

                //making the input & output streams
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                int c = 5;
                String s = "";
                while (c > 0) {
                    try {
                        c = inFromClient.read();
                        //System.out.print((char) c);
                        s += (char) c;
                        if (c == ';') {
                            System.out.println(s + " " + new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
                            {
                                if (s.contains("##")) {
                                    outToClient.writeBytes("LOAD");
                                    sleep(3000);
                                    outToClient.writeBytes("ON");
                                    sleep(1000);
                                    outToClient.writeBytes("ON");
                                    sleep(3000);
                                    outToClient.writeBytes("**,imei:863070014296916,C,10s");
                                    sleep(1000);
                                    outToClient.writeBytes("**,imei:863070014296916,C,10s");
                                    System.out.println("Initializing...");
                                }
                            }
                            handleData(s);
                            s = "";
                        }
                    } catch (IOException e) {
                        try {
                            sleep(2000);
                        } catch (InterruptedException ie) {
                        }
                    } catch (InterruptedException ie) {
                    }
                }
            }
        } catch (IOException ioe) {
        }
    }

    //get recieved string from server and decide the type and what to do
    public void handleData(String s) {
        DeviceHandlingServer.getInstance().saveLog(s);
        //if it is a Location
        DeviceHandlingServer.getInstance().saveLocation(null);
        DeviceHandlingServer.getInstance().executeLocation(createLocation(s));
        
    }

    //decode the incoming string into the parametes which needed to make a LocationBox
    public LocationBox createLocation(String s) {
        return new LocationBox();
    }

}
