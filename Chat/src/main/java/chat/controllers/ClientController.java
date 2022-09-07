package chat.controllers;

import chat.ClientChat;
import chat.dialogs.Dialogs;
import chat.model.Network;
import chat.model.ReadMessageListener;
import command.Command;
import command.CommandType;
import command.commands.ClientMessageCommandData;
import command.commands.UpdateUserListCommandData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class ClientController {

    @FXML
    public TextField messageTextArea;

    @FXML
    public Button sendMessageButton;

    @FXML
    public TextArea chatTextArea;

    @FXML
    public ListView userList;

    public void sendMessage() {
        String message = messageTextArea.getText();

        if (message.isEmpty()) {
            messageTextArea.clear();
            return;
        }

        String sender = null;
        if (!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem().toString();
        }

        try {
            if (sender != null) {
                Network.getInstance().sendPrivateMessage(sender, message);
            } else {
                Network.getInstance().sendMessage(message);
            }

        } catch (IOException e) {
            Dialogs.NetworkError.SEND_MESSAGE.show();
        }

        appendMessageToChat("Я", message);
        try {
            writeHistoryMessagesToFile("Я", message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUsername() {
//        String message = messageTextArea.getText();
//
//
//        if (message != null) {
//            Network.getInstance().updateUsername( message);
       // }
    }

    public void appendMessageToChat(String sender, String message) {
        chatTextArea.appendText(DateFormat.getInstance().format(new Date()));
        chatTextArea.appendText(System.lineSeparator());

        if (sender != null) {
            chatTextArea.appendText(sender + ":");
            chatTextArea.appendText(System.lineSeparator());
        }

        chatTextArea.appendText(message);
        chatTextArea.appendText(System.lineSeparator());
        chatTextArea.appendText(System.lineSeparator());
        requestFocusForTextArea();
        messageTextArea.clear();
    }

    public static void writeHistoryMessagesToFile(String sender, String message) throws IOException {
        String outputFileName = "Chat/ChatLogs/" + ClientChat.getInstance().getChatStage().getTitle() + ".log";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))){
            writer.write(DateFormat.getInstance().format(new Date()) + "\n");
            if (sender != null) {
                writer.write(sender + ":\n");
            }
            writer.write(message + "\n");
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(message);
    }

    private void requestFocusForTextArea() {
        Platform.runLater(() -> messageTextArea.requestFocus());
    }

    public void initializeMessageHandler() {
        Network.getInstance().addReadMessageListener(new ReadMessageListener() {
            @Override
            public void processReceivedCommand(Command command) {
                if (command.getType() == CommandType.CLIENT_MESSAGE) {
                    ClientMessageCommandData data = (ClientMessageCommandData) command.getData();
                    appendMessageToChat(data.getSender(), data.getMessage());
                    try {
                        writeHistoryMessagesToFile(data.getSender(), data.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (command.getType() == CommandType.UPDATE_USERS_LIST) {
                    UpdateUserListCommandData data = (UpdateUserListCommandData) command.getData();
                    Platform.runLater(() -> {
                        userList.setItems(FXCollections.observableArrayList(data.getUsers()));
                    });
                }

            }
        });
    }
}