package controllers;

import java.util.ArrayList;

import com.google.gson.Gson;

import dao.ProductsDAO;
import models.Product;
import webserver.WebServerContext;

public class ProductsController {
    
    public static ArrayList<Product> findAll(WebServerContext webServerContext){
        try {
            ProductsDAO productsDAO = new ProductsDAO();
            //webServerContext.getResponse().ok("Tous les produits");
            webServerContext.getResponse().json(productsDAO.findAll());
            return null;
        } catch (Exception e) {
            System.out.println("Failed to find all products.");
        }
        return null;
    }

    public static boolean bid(WebServerContext webServerContext){
        try {

            ProductsDAO productsDAO = new ProductsDAO();
            String id = webServerContext.getRequest().getParam("productId");
            int new_id = Integer.parseInt(id);
            Product updatedProduct = productsDAO.bid(new_id);

            String json = new Gson().toJson(updatedProduct);

            webServerContext.getSSE().emit("bids", json);
            webServerContext.getResponse().ok(json);
            
            return true;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to bid on product.");
            return false;
        }
    }
}
