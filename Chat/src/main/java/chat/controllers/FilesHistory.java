package chat.controllers;


import chat.ClientChat;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


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

//    public static String loadHistory(String username){
//        StringBuilder stb = new StringBuilder();
//        int linesToRead = 100;
//        List<String> result = new ArrayList<>();
//        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(new File("Chat/ChatLogs/" + username + ".log"), StandardCharsets.UTF_8)) {
//
//            String line;
//            while ((line=reader.readLine()) != null && result.size() < linesToRead){
//                result.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (String str : result) {
//            stb.insert(0,linesToRead-- + ")" + str + "\n");
//        }
//        return stb.toString();
//    }

}
