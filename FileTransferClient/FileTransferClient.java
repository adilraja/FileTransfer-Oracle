/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.io.*;
import java.net.*;


public class FileTransferClient {
    
    public final static String FILE_TO_RECEIVED = "c:/temp/source-downloaded.pdf";  // you may change this, I give a
                                                            // different name because i don't want to
                                                            // overwrite the one used by server...
    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
                                               // should bigger than the file to be downloaded
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java TelnetClient <host name> <port number>");
            System.exit(1);
        }
        
        int bytesRead;
        int current = 0;
     //   FileOutputStream fos = null;
     //   BufferedOutputStream bos = null;
    

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        byte [] mybytearray  = new byte [FILE_SIZE];
      
     
   //   current = bytesRead;

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput, serverOutput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
              //  fos = new FileOutputStream(userInput+".txt");
               // bos = new BufferedOutputStream(fos);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(userInput+".txt")));
		System.out.println("FileTransfer: ");
                current=0;
                serverOutput=null;
		while(!(serverOutput=in.readLine()).equalsIgnoreCase("Bye:")){
	    //           bos.write(serverOutput.getBytes(), 0, serverOutput.getBytes().length);
                       //current=current+serverOutput.getBytes().length;
			pw.println(serverOutput);			
			System.out.println(serverOutput);
                }
		System.out.println("Bye Client");
          //      fos.close();
            //    bos.close();
		pw.close();
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
