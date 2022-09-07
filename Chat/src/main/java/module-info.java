module me.chat_v3.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires Commands;


    opens chat to javafx.fxml;
    exports chat;
    opens chat.controllers to javafx.fxml;
    exports chat.controllers;
}