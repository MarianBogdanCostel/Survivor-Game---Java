package PaooGame;
import java.sql.*;

public class DataBase {
    Connection c;
    Statement stmt;
    ResultSet rs;

    /*! \fn public DBHandler()
        \brief Constructorul de initializare al clasei DBHandler in care se indica driverul si baza de date la care sa ne conectam.
     */
    public DataBase() {
        try {
            Class.forName("org.sqlite.JDBC"); //incarcam driverul
            c = DriverManager.getConnection("jdbc:sqlite:Database.db"); // facem conexiunea cu baza de date.
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void updateAll(int playerX, int playerY, int playerLife, int score, int level ) throws SQLException
    {
        String instruction = "UPDATE SaveGame set PlayerX =" + playerX + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
        instruction = "UPDATE SaveGame set PlayerY =" + playerY + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
        instruction = "UPDATE SaveGame set PlayerLife =" + playerLife + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
        instruction = "UPDATE SaveGame set Score =" + score + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
        instruction = "UPDATE SaveGame set Level =" + level + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);

    }
    /*! \fn public void updateDifficulty(int difficulty) throws SQLException
        \brief Functie ce scrie in baza de date dificultatea aleasa de utilizator in meniul "Settings".

        \param difficulty Este un int care ia valori de la 1 la 3 si reprezinta dificultatea jocului. Folosit pentru a "scala" viteza inamicilor/etc.
     */
    public void updateDifficulty(int difficulty) throws SQLException {
        String instruction = "UPDATE Settings set Difficulty =" + difficulty + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

    /*! \fn public void updateMenuMusicVolume(int volume) throws  SQLException
        \brief Functie ce scrie in baza de date volumul muzicii din MenuState pe care-l doreste utilizatorul(Selectand din setari).

        \param volume este intregul care reprezinta procentajul la care utilizatorul doreste sa se auda muzica.
     */
    public void updateMenuMusicVolume(int volume) throws SQLException {
        String instruction = "UPDATE Settings set Sound =" + volume + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }


    /*! \fn public int getDifficulty() throws SQLException
        \brief Functie de extragere a dificultatii din baza de date pentru ne folosi de ea in joc.
     */
    public int getDifficulty() throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM Settings;");
        return rs.getInt("Difficulty");
    }

    /*! \fn public int getMenuVolume() throws SQLException
        \brief Functie de extragere a volumului din meniu din baza de date pentru a-l modifica in timp real.
     */
    public int getMenuVolume() throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM Settings;");
        return rs.getInt("Sound");
    }

    /*! \fn public float getHeroX() throws SQLException
        \brief Functie de extragere a coordonatei pe axa X a eroului din baza de date.
     */
    public int getPlayerX() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SaveGame");
        return rs.getInt("PlayerX");
    }

    /*! \fn public float getHeroY() throws SQLException
        \brief Functie de extragere a coordonatei pe axa Y a eroului din baza de date.
     */
    public int getPlayerY() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SaveGame");
        return rs.getInt("PlayerY");
    }

    /*! \fn public int getHeroLife() throws SQLException
        \brief Functie de extragere a vietii eroului din baza de date.
     */
    public int getPlayerLife() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SaveGame");
        return rs.getInt("PlayerLife");
    }

    /*! \fn public int getHeroScore() throws SQLException
        \brief Functie de extragere a scorului eroului din baza de date.
     */
    public int getScore() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SaveGame");
        return rs.getInt("Score");
    }

    public int getLevel() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SaveGame");
        return rs.getInt("Level");
    }

    public void updatePlayerX(int playerX) throws SQLException {
        String instruction = "UPDATE Settings set PlayerX =" + playerX + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

    public void updatePlayerY(int playerY) throws SQLException {
        String instruction = "UPDATE Settings set PlayerY =" + playerY + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

    public void updatePlayerLife(int playerLife) throws SQLException {
        String instruction = "UPDATE Settings set PlayerLife =" + playerLife + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

    public void updateScore(int score) throws SQLException {
        String instruction = "UPDATE Settings set Score =" + score + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

    public void updateLevel(int level) throws SQLException {
        String instruction = "UPDATE Settings set Level =" + level + " WHERE Nr=1;";
        stmt.executeUpdate(instruction);
    }

}


