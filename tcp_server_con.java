import java.io.*;
import java.net.*;
import java.util.*;

public class tcp_server_con {
     public static void main(String[] args) throws Exception {

          ServerSocket welcomeSocket = null;
          try{
               welcomeSocket = new ServerSocket(1667);
               welcomeSocket.setReuseAddress(true);
          }
          catch(IOException e){
               System.out.println("Cannot create a welcome socket");
               System.exit(1);
          }
          while(true){
               try{
                    System.out.println("Server is Waiting for connection at port 1667");
                    Socket connectionSocket = welcomeSocket.accept();
                    EchoThread echoThread = new EchoThread(connectionSocket);
                    echoThread.start();
               }
               catch(IOException e){
                    System.out.println("Cannot create this connection");
               }

          }
     }
}

class EchoThread extends Thread{
     private Socket connectionSocket;

     public EchoThread(Socket connectionSocket){
          this.connectionSocket = connectionSocket;
     }

     public void run(){
          Scanner inFromClient = null;
          DataOutputStream outToClient = null;
          int result;

          try{
               result = 0;
               inFromClient = new Scanner(connectionSocket.getInputStream());
               outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               System.out.println("Connecting From " + connectionSocket.getInetAddress().getHostAddress());
               while(true){
                    String clientSentence;
                    clientSentence = inFromClient.nextLine();
                    if(clientSentence.equals("")){
                         //stop input
                         outToClient.writeBytes(String.valueOf(result));
                         System.out.println("send data");
                         System.out.println("Server Close");
                         inFromClient.close();
                         outToClient.close();
                         break;
                    }
                    else{
                         result += Integer.parseInt(clientSentence);
                    }
               }
               // outToClient.writeBytes(capitalizedSentence);
          }

          catch(IOException e){
               System.err.println("Closing Socket connection");
          }
          finally{
               try{
                    if(outToClient != null) {
                         outToClient.close();
                    }
                    if(inFromClient != null){
                         inFromClient.close();
                    }
                    if(connectionSocket != null){
                         connectionSocket.close();
                    }
               }
               catch(IOException e){
                    e.printStackTrace();
               }
          }
     }
}
