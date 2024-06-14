package dao;

import java.util.ArrayList;
import models.Word;
import database.PolyNameDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class WordsDAO {

    public WordsDAO() {
        
    }

    //find all words in the database
    public ArrayList<Word> findAll(){
        ArrayList<Word> content = new ArrayList<>();
        try {
            PolyNameDatabase db = new PolyNameDatabase("localhost", 3307, "poly_names", "root", "");
            String query = "SELECT * FROM word ORDER BY content ASC";
            PreparedStatement statement = db.prepareStatement(query);
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Word_ID");
                String contents = resultSet.getString("content");

                Word wordObj = new Word(id, contents);
                content.add(wordObj);

            }
            
        } catch (Exception e) {
            System.out.println("Failed to find all products.");
        }
        return content;
    }

    
}



