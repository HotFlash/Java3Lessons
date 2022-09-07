package command.commands;

import java.io.Serializable;

public class UpdateUsername implements Serializable {
    private final String login;
    private final String username;

    public UpdateUsername(String login, String username) {
        this.login = login;
        this.username = username;
    }
    public String getLogin(){
        return login;
    }
    public String getUsername(){
        return username;
    }
}
