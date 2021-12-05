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
                Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(product);
                return product;
            }
        }
        return null;
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category) {

        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateAccountId = product -> (product.accountId == accountId);
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateAccountId, predicateSearch, minPrice, maxPrice, category);
    }

    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predAccountId, Predicate<Product> predSearch, int lowestPrice, int highestPrice, ProductCategory category) {
        List<Product> newList = new ArrayList<Product>();
        if (lowestPrice != 0.0 && highestPrice != 0.0) {
            for (Product product : getJsonTable()) {
                double productPrice = product.price;
                if (productPrice > lowestPrice && productPrice < highestPrice && predAccountId.predicate(product) && predSearch.predicate(product) && product.category == category) {
                    newList.add(product);
                }
            }
        } else if (lowestPrice == 0.0 && highestPrice != 0.0) {
            for (Product product : getJsonTable()) {
                double productPrice = product.price;
                if (productPrice < highestPrice && predAccountId.predicate(product) && predSearch.predicate(product) && product.category == category) {
                    newList.add(product);
                }
            }
        } else if (highestPrice == 0.0 && lowestPrice != 0.0) {
            for (Product product : getJsonTable()) {
                double productPrice = product.price;
                if (productPrice > lowestPrice && predAccountId.predicate(product) && predSearch.predicate(product) && product.category == category) {
                    newList.add(product);
                }
            }
        } else {
            for (Product product : getJsonTable()) {
                double productPrice = product.price;
                if (predAccountId.predicate(product) && predSearch.predicate(product) && product.category == category) {
                    newList.add(product);
                }
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<Product> paginatedList;
        if (endIndex > newList.size()) {
            paginatedList = newList.subList(startIndex, newList.size());
        } else {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }
}

