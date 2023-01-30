import java.io.*;
import java.net.*;

public class tcp_server {
     public static void main(String argv[]) throws Exception{

          int result;
          BufferedReader inFromClient = null;
          DataOutputStream outToClient = null;
          Socket connectionSocket = null;

          ServerSocket welcomeSocket = new ServerSocket(6789);

          while(true){
               try{
                    connectionSocket = welcomeSocket.accept();
                    System.out.println("Connecting to " + connectionSocket.getInetAddress().getHostAddress());
                    result = 0;
                    inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    while(true){
                         String clientSentence;
                         clientSentence = inFromClient.readLine();
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
                              System.out.println("Get Data:" + clientSentence);
                         }
                    }
                    connectionSocket.close();



                    // clientSentence2 = inFromClient.readLine();
                    // if(clientSentence2 == null){
                    //      connectionSocket.close();
                    //      inFromClient.close();
                    //      outToClient.close();
                    // }

                    // result = clientSentence1 != null ? result += Integer.parseInt(clientSentence1): result;
                    // result = clientSentence2 != null ? result += Integer.parseInt(clientSentence2): result;


                    // inFromClient.close();
                    // outToClient.close();
                    // connectionSocket.close();

               }
               catch(IOException e) {
                    System.out.println("error");
               }
               finally{
                    try{
                         if(inFromClient != null){
                              inFromClient.close();
                         }
                         if(outToClient != null) {
                              outToClient.close();
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
}
