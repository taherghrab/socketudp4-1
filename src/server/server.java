package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class server {
    public static void main(String[] args) throws IOException {

            // Créer un socket UDP pour le serveur sur le port 1234
            DatagramSocket serverSocket = new DatagramSocket(1234);

            byte[] buffer = new byte[1024];

            System.out.println("Serveur UDP en attente sur le port 1234");

            while (true) {
                // Préparer le paquet pour recevoir les données du client
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                // Attendre la réception d'un paquet du client
                serverSocket.receive(receivePacket);

                // Extraire les données reçues
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Récupérer l'adresse IP et le numéro de port du client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Créer la réponse
                String response = "Bienvenue " + clientMessage;

                // Convertir la réponse en tableau de bytes
                byte[] res = response.getBytes();

                // Créer un paquet pour envoyer la réponse au client
                DatagramPacket sendPacket = new DatagramPacket(res, res.length, clientAddress, clientPort);

                // Envoyer la réponse au client
                serverSocket.send(sendPacket);
            }

        }
    }


