package dao;

import database.PolyNameDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDAO {
    
    // create a new player
    public void createPlayer(String Player_UUID, String role, int gameID) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "INSERT INTO player (Player_UUID, role, Game_ID, Player_ID) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, Player_UUID);
            statement.setString(2, role);
            statement.setInt(3, gameID);
            if (role.equals("wordMaster")){
                statement.setInt(4, 1);
            }
            else if (role.equals("intMaster")){
                statement.setInt(4, 2);
            }
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to create player.");
            e.printStackTrace();
        } 
    }

    // find a player by Player_UUID
    public int findPlayerByName(String Player_UUID, int gameID) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "SELECT Player_ID FROM player WHERE Player_UUID = ? AND Game_ID = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, Player_UUID);
            statement.setInt(2, gameID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int playerID = resultSet.getInt("Player_ID");
                return playerID;
            } else {
                return 0;
            }
            
        } catch (Exception e) {
            System.out.println("Failed to find player by Player_UUID.");
            e.printStackTrace();
            return 0; 
        }
    }

    // delete a player by Player_UUID
    public void deletePlayerByName(String Player_UUID) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "DELETE FROM player WHERE Player_UUID = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, Player_UUID);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to delete player by Player_UUID.");
        } 
    }

    // check if a player exists
    public boolean playerExists(String role, int gameID) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "SELECT * FROM player WHERE role = ? AND Game_ID = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, role);
            statement.setInt(2, gameID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Failed to check if player exists.");
            e.printStackTrace();
            return false; 
        }
    }

    // check if the player as the same UUID
    public boolean playerHasSameUUID(String Player_UUID, int gameID, String role) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3306, "poly_names", "root", "");
            String query = "SELECT * FROM player WHERE Player_UUID = ? AND Game_ID = ? AND role = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, Player_UUID);
            statement.setInt(2, gameID);
            statement.setString(3, role);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Failed to check if player has the same UUID.");
            return false; 
        }
    }
}
