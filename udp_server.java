import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class udp_server {
     public static void main(String args[]) throws Exception {
          DatagramSocket serverSocket = new DatagramSocket(9876);

          byte[] receiveData = new byte[1024];
          byte[] sendData = new byte[1024];

          while(true){
               DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

               serverSocket.receive(receivePacket);

               // String sentence = new String(receivePacket.getData());

               InetAddress IPAddress = receivePacket.getAddress();

               int port = receivePacket.getPort();

               // String capitalizedSentence = sentence.toUpperCase();

               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
               LocalDateTime now = LocalDateTime.now();
               // System.out.println(dtf.format(now));

               sendData = dtf.format(now).getBytes();

               DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

               serverSocket.send(sendPacket);
          }
     }
}
