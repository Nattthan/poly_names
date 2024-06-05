package dao;

import java.util.ArrayList;
import models.Product;
import database.PolyBayDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class ProductsDAO {

    public ProductsDAO() {
        
    }

    public ArrayList<Product> findAll(){
        try {
            PolyBayDatabase db = new PolyBayDatabase("localhost", 3306, "poly_bay", "root", "");
            String query = "SELECT * FROM product ORDER BY name ASC";
            PreparedStatement statement = db.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                float bid = resultSet.getFloat("bid");
                
                Product product = new Product(id, name, owner, bid);
                products.add(product);
            }

            return products;
        } catch (Exception e) {
            System.out.println("Failed to find all products.");
        }
        return null;
    }

    public Product bid(int id){
        try {
            PolyBayDatabase db = new PolyBayDatabase("localhost", 3306, "poly_bay", "root", "");
            String query = "UPDATE product SET bid = (bid + ?) WHERE id = ?";
            PreparedStatement statement = db.prepareStatement(query);
            statement.setFloat(1, 50);
            statement.setInt(2, id);
            statement.execute();

            String selectQuery = "SELECT * FROM product WHERE id = ?";
            PreparedStatement selectStatement = db.prepareStatement(selectQuery);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            
            if (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                float bid = resultSet.getFloat("bid");
    
                Product product = new Product(productId, name, owner, bid);
                System.out.println("Bid successful.");
                return product;
            }
        } catch (Exception e) {
            System.out.println("Failed to bid on product.");
        }
        return null;  
    } 
}



