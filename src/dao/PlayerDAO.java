package dao;

import database.PolyNameDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDAO {
    
    // create a new player
    public void createPlayer(String name, String role, int gameID) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "INSERT INTO player (name, role, Game_ID) VALUES (?, ?, ?)";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, role);
            statement.setInt(3, gameID);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to create player.");
        } 
    }

    // find a player by name
    public int findPlayerByName(String name) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "SELECT Player_ID FROM player WHERE name = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, name);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            int playerID = resultSet.getInt("Player_ID");
            return playerID;
            
        } catch (Exception e) {
            System.out.println("Failed to find player by name.");
            return 0; 
        }
    }

    // delete a player by name
    public void deletePlayerByName(String name) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "DELETE FROM player WHERE name = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, name);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to delete player by name.");
        } 
    }
}
