import java.io.*;
import java.net.*;
import java.util.*;


class tcp_client{
     public static void main(String[] args) throws Exception{
          String sentence;
          String modifiedSentence;

          Scanner inFromUser = null;
          Socket clientSocket = null;
          DataOutputStream outToServer = null;
          Scanner inFromServer = null;

          try{
               inFromUser = new Scanner(System.in);
               clientSocket = new Socket("localhost", 1667);
               outToServer = new DataOutputStream(clientSocket.getOutputStream());
               inFromServer = new Scanner(clientSocket.getInputStream());

               while(true){
                    //get input from keyboard
                    System.out.print("Insert Something To Server: ");
                    sentence = inFromUser.nextLine();

                    //send sentence to server
                    outToServer.writeBytes(sentence + '\n');

                    //empty string is Exit
                    if(sentence.equals("")){
                         break;
                    }

               }
               //from the other side check if client send empty string too server will send result back
               modifiedSentence = inFromServer.nextLine();
               System.out.println("Receive Data");
               System.out.println("Result IS: " + modifiedSentence);

               // clientSocket.close();
          }
          catch(IOException e){
               System.out.println("error occurred: Closing the connection");
          }
          finally{
               try{
                    if(inFromServer != null){
                         inFromServer.close();
                    }
                    if(outToServer != null){
                         outToServer.close();
                    }
                    if(clientSocket != null){
                         clientSocket.close();
                    }
               }
               catch(IOException e){
                    e.printStackTrace();
               }
          }
     }
}