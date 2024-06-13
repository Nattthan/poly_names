package dao;
import database.PolyNameDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Word;

public class CardsDAO {
    
    //select 8 random words from word table to be used as blue cards and put them into the card table
    public void createBlueCards(int game_ID){
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "INSERT INTO card (Word_ID, Color_ID, Game_ID, isFound) SELECT Word_ID, ?, ?, FALSE FROM word ORDER BY RAND() LIMIT 8";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 1);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to select blue cards.");
        }
    }

    //select 15 random words from word table to be used as grey cards and put them into the card table
    public void createGreyCards(int game_ID){
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "INSERT INTO card (Word_ID, Color_ID, Game_ID, isFound) SELECT Word_ID, ?, ?, FALSE FROM word ORDER BY RAND() LIMIT 15";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 2);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            statement.execute();
        } catch (Exception e) {
            System.out.println("Failed to select grey cards.");
        }
    }

    //select 2 random words from word table to be used as black cards and put them into the card table
    public void createBlackCards(int game_ID){
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "INSERT INTO card (Word_ID, Color_ID, Game_ID, isFound) SELECT Word_ID, ?, ?, FALSE FROM word ORDER BY RAND() LIMIT 2";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 3);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            statement.execute();
        } catch (Exception e) {
            System.out.println("Failed to select black cards.");
        }
    }

    // delete all cards depending on the game ID
    public void deleteCards(int game_ID){
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "DELETE FROM card WHERE Game_ID = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, game_ID);
            System.out.println("Executing query: " + query);
            statement.execute();
        } catch (Exception e) {
            System.out.println("Failed to delete cards.");
        }
    }

    //draw 8 blue cards
    public ArrayList<Word> drawBlueCards(int game_ID){
        ArrayList<Word> blueCards = new ArrayList<>();
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * from card INNER JOIN word ON card.Word_ID = word.Word_ID WHERE Color_ID = ? AND Game_ID = ? ORDER BY RAND() LIMIT 8";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 1);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("Word_ID");
                String content = resultSet.getString("content");

                Word wordObj = new Word(id, content);
                blueCards.add(wordObj);
            }

        } catch (Exception e) {
            System.out.println("Failed to draw blue cards.");
        }
        return blueCards;
    }

    //draw 15 grey cards
    public ArrayList<Word> drawGreyCards(int game_ID){
        ArrayList<Word> greyCards = new ArrayList<>();
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * from card INNER JOIN word ON card.Word_ID = word.Word_ID WHERE Color_ID = ? AND Game_ID = ? ORDER BY RAND() LIMIT 15";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 2);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("Word_ID");
                String content = resultSet.getString("content");

                Word wordObj = new Word(id, content);
                greyCards.add(wordObj);
            }

        } catch (Exception e) {
            System.out.println("Failed to draw grey cards.");
        }
        return greyCards;
    }

    //draw 2 black cards
    public ArrayList<Word> drawBlackCards(int game_ID){
        ArrayList<Word> blackCards = new ArrayList<>();
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * from card INNER JOIN word ON card.Word_ID = word.Word_ID WHERE Color_ID = ? AND Game_ID = ? ORDER BY RAND() LIMIT 2";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, 3);
            statement.setInt(2, game_ID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("Word_ID");
                String content = resultSet.getString("content");

                Word wordObj = new Word(id, content);
                blackCards.add(wordObj);
            }

        } catch (Exception e) {
            System.out.println("Failed to draw black cards.");
        }
        return blackCards;
    }

    // draw all cards depending on the game ID
    public ArrayList<Word> drawAllCards(int game_ID){
        ArrayList<Word> allCards = new ArrayList<>();
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * FROM card INNER JOIN word ON card.Word_ID = word.Word_ID WHERE Game_ID = ? ORDER BY RAND() LIMIT 25";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setInt(1, game_ID);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("Word_ID");
                String content = resultSet.getString("content");

                Word wordObj = new Word(id, content);
                allCards.add(wordObj);
            }

        } catch (Exception e) {
            System.out.println("Failed to draw all cards.");
            e.printStackTrace();
        }
        return allCards;
    }
    public ArrayList<Word> drawAllColorCards(int game_ID){
        ArrayList<Word> allCards = new ArrayList<>();
        try{
            ArrayList<Word> blackCards = drawBlackCards(game_ID);
            ArrayList<Word> blueCards = drawBlueCards(game_ID);
            ArrayList<Word> grayCards = drawGreyCards(game_ID);
            allCards.add(blackCards.get(0));
            allCards.add(blackCards.get(1));
            for (int i = 0; i < 8; i++){
                allCards.add(blueCards.get(i));
            }
            for (int i = 0; i < 15; i++){
                allCards.add(grayCards.get(i));
            }
        }
        catch (Exception e) {
            System.out.println("Failed to draw all cards.");
            e.printStackTrace();
        }
        return allCards;
    }
}
