package PaooGame;

import PaooGame.GameWindow.GameWindow;

import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("Survivor", 1280, 768);
        paooGame.StartGame();
    }


}
