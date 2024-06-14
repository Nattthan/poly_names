package dao;

import database.PolyNameDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameDAO {

    // create a new game
    public void createGame(String code) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "INSERT INTO game (code, score, turn) VALUES (?, 0, 'guess')";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to create game.");
        } 
    }

    // find a game by code
    public int findGameByCode(String code) {
        int gameID = 0;
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT Game_ID FROM game WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                gameID = resultSet.getInt("Game_ID");
            }
            
        } catch (Exception e) {
            System.out.println("Failed to find game by code.");
        }
        return gameID;
    }

    // delete a game by code
    public void deleteGameByCode(String code) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "DELETE FROM game WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to delete game by code.");
        } 
    }

    // update the score of a game
    public void updateScore(String code, String score) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "UPDATE game SET score = ? WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, score);
            statement.setString(2, code);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to update score.");
        } 
    }

    // get the score of a game
    public int getScore(String code){
        int score = 0;
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT score FROM game WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                score = resultSet.getInt("score"); 
                return score;
            }
        } catch (Exception e) {
            System.out.println("Failed to get score.");
            return 0;
        }
        return score;
    }

    // manage turns
    public void manageTurns(String code, String turn) {
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "UPDATE game SET turn = ? WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, turn);
            statement.setString(2, code);
            System.out.println("Executing query: " + query);
            statement.execute();
            
        } catch (Exception e) {
            System.out.println("Failed to manage turns.");
        } 
    }

    // get the turn of a game
    public String getTurn(String code){
        String turn = "default";
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT turn FROM game WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                turn = resultSet.getString("turn");
                return turn;
            }

        } catch (Exception e) {
            System.out.println("Failed to get turn.");
            return "";
        }
        return turn;
    }

    // check if the game exists
    public boolean gameExists(String code){
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * FROM game WHERE code = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, code);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Failed to check if game exists.");
            return false;
        }
    }
    
}
