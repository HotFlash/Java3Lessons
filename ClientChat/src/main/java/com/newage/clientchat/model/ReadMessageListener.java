package com.newage.clientchat.model;

import com.newage.command.Command;

public interface ReadMessageListener {

    void processReceivedCommand(Command command);

}
