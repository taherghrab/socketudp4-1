package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {

            // Créer un socket UDP pour le client
            DatagramSocket clientSocket = new DatagramSocket();

            // Adresse IP et port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            // Demander le prénom et le nom à l'utilisateur
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre prénom : ");
            String prenom = scanner.nextLine();
            System.out.print("Entrez votre nom : ");
            String nom = scanner.nextLine();

            // Concaténer le prénom et le nom en une seule chaîne de caractères
            String message = prenom + " " + nom;

            // Convertir le message en tableau de bytes
            byte[] msg = message.getBytes();

            // Créer un paquet pour envoyer les données au serveur
            DatagramPacket sendPacket = new DatagramPacket( msg, msg.length, serverAddress, serverPort);

            // Envoyer le message au serveur
            clientSocket.send(sendPacket);

            // Préparer un paquet pour recevoir la réponse du serveur
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

            // Attendre la réponse du serveur
            clientSocket.receive(receivePacket);

            // Extraire la réponse du paquet
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Afficher la réponse et les informations du serveur
            System.out.println(" serveur : " + serverResponse);
            System.out.println("Adresse du serveur : " + receivePacket.getAddress().getHostAddress());
            System.out.println("Port du serveur : " + receivePacket.getPort());

            // Fermer le socket client
            clientSocket.close();

        }
    }
