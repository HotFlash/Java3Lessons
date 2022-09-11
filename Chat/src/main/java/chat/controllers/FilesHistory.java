package chat.controllers;


import chat.ClientChat;
import java.io.*;
import java.io.IOException;


public class FilesHistory {

    public static void prepareLogFile(){
        String historyFileName = ClientChat.getInstance().getChatStage().getTitle() + "_Log";
        if (!createLogDir()) {
            System.out.printf("directory already exist \n");
        }
        if (!createLogFile()) {
            System.out.printf("file already exist \n");
        }

    }

    private static boolean createLogDir() {
        File dir = new File("Chat/ChatLogs");
        return dir.mkdir();
    }

    private static boolean createLogFile() {
        File file = new File("Chat/ChatLogs/" + ClientChat.getInstance().getChatStage().getTitle() + ".log");
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
