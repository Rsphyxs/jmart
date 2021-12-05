package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.*;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import com.IlhamJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "fileProduct.json")
    public static JsonTable<Product> productTable;

    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    @PostMapping("/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,
                   @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
        for (Account account : AccountController.accountTable) {
            if (account.id == accountId && account.store != null) {
                Product p = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(p);
                return p;
            }
        }
        return null;
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category){
//        List<Product> newList = getJsonTable();
//        if(accountId != 0){
//            newList = Jmart.filterByAccountId(newList, accountId, page, pageSize);
//        }
//        if(!search.equals("")){
//            newList = Jmart.filterByName(newList, search, page, pageSize);
//        }
//        if(minPrice != 0.0 || maxPrice != 0.0){
//            newList = Jmart.filterByPrice(newList, minPrice, maxPrice);
//        }
//        if(category != null){
//            newList = Jmart.filterByCategory(newList, category);
//        }
//        return newList;
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateAccountId = product -> (product.accountId == accountId);
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateAccountId, predicateSearch, minPrice, maxPrice, category);
    }

    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predAccountId, Predicate<Product> predSearch, int minPrice, int maxPrice, ProductCategory category){
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice && predAccountId.predicate(p) && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice < maxPrice && predAccountId.predicate(p) && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0 && minPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && predAccountId.predicate(p) && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(predAccountId.predicate(p) && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<Product> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }
}

