package chat;

import chat.controllers.AuthController;
import chat.controllers.ClientController;
import chat.controllers.FilesHistory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class ClientChat extends Application {

    private static ClientChat INSTANCE;

    private Stage chatStage;
    private Stage authStage;

    private FXMLLoader chatWindowLoader;
    private FXMLLoader authLoader;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.chatStage = primaryStage;

        initViews();
        getChatStage().show();
        getAuthStage().show();
        getAuthController().initializeMessageHandler();

    }

    private void initViews() throws IOException {
        initChatWindow();
        initAuthDialog();
    }

    private void initChatWindow() throws IOException {
        chatWindowLoader = new FXMLLoader();
        chatWindowLoader.setLocation(ClientChat.class.getResource("/chat-template.fxml"));

        Parent root = chatWindowLoader.load();
        chatStage.setScene(new Scene(root));
        getChatController().initializeMessageHandler();
    }

    private void initAuthDialog() throws IOException {
        authLoader = new FXMLLoader();
        authLoader.setLocation(ClientChat.class.getResource("/authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(chatStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));
    }

    public void switchToMainChatWindow(String userName) {
        getChatStage().setTitle(userName);
        getAuthController().close();
        getAuthStage().close();
        FilesHistory.prepareLogFile();
    }


    public void readLogFile(String filename) throws IOException {

        BufferedReader bufferedReader = null;
        FileReader logfile = null;
        try {
            logfile = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        bufferedReader = new BufferedReader(logfile);
        String line;int
                count = 0;
            while ((line = bufferedReader.readLine()) != null && count <= 99) {
                    getChatController().chatTextArea.appendText(line);
                    getChatController().chatTextArea.appendText(System.lineSeparator());
                    count++;
            }

            bufferedReader.close();
            logfile.close();
    }





    @Override
    public void init() {
        INSTANCE = this;
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getAuthStage() {
        return authStage;
    }

    public Stage getChatStage() {
        return chatStage;
    }

    public ClientController getChatController() {
        return chatWindowLoader.getController();
    }

    public AuthController getAuthController() {
        return authLoader.getController();
    }

    public static ClientChat getInstance() {
        return INSTANCE;
    }


}