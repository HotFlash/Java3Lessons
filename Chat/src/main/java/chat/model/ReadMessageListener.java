package chat.model;

import command.Command;

public interface ReadMessageListener {

    void processReceivedCommand(Command command);

}
