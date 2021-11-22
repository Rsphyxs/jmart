package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.Product;
import com.IlhamJmartMH.ProductCategory;
import com.IlhamJmartMH.dbjson.JsonTable;

import java.util.List;

public class ProductController implements BasicGetController<Product>{
    public static JsonTable<Product> productTable;

    Product create(int accountId, String name, int weight, boolean conditionUsed,
                   double price, double discount, ProductCategory category, byte shipmentPlans){
        return null;

    }

    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    List<Product> getProductByStore(int id, int page, int pageSize){
        return null;
    }

    List<Product> getProductFiltered(int page, int pageSize, int accountId, String search, int minPrice, int maxPrice, ProductCategory category){
        return null;
    }
}
