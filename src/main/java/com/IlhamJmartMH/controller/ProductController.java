package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.*;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import com.IlhamJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ProductController yang akan mengatur seluruh aktivitas terkait produk
 * @author Muhammad Ilham M S
 * @version 16 Desember 2021
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "fileProduct.json")
    public static JsonTable<Product> productTable;

    /**
     * Method getJsonTable yang akan mengembalikan keseluruhan tabel produk
     */
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    /**
     * Method getProductById yang akan mengembalikan data produk berdasarkan id produknya
     * @param id id dari produk
     */
    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) { return getById(id); }

    /**
     * Method getProductByStpre yang akan mengembalikan data produk berdasarkan id dari storenya
     * @param id id dari store
     * @param page adalah halaman dari data yang akan dikembalikan
     * @param pageSize adalah jumlah data per halaman yang akan dikembalikan
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    /**
     * Method create yang akan mendaftarkan produk baru
     * @param accountId adalah id dari akun yang mendaftarkan produk
     * @param name adalah nama dari produk
     * @param weight sebagai berat dari produk
     * @param conditionUsed sebagai kondisi dari produk
     * @param price sebagai harga dari produk
     * @param discount sebagai diskon dari produk
     * @param category sebagai kategori dari produk
     * @param shipmentPlans sebagai rencana pengiriman dari produk
     */
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

    /**
     * Method getProductFiltered yang akan mengembalikan data yang telah difilter
     * @param page halaman dari data produk yang akan ditampilkan
     * @param pageSize jumlah data per halaman yang akan ditampilkan
     * @param accountId id dari akun
     * @param category kategori dari produk
     * @param maxPrice harga batas atas
     * @param minPrice harga batas bawah
     * @param search keyword search
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category) {

        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateSearch, minPrice, maxPrice, category);
    }

    /**
     * Method paginateProductListFilteredAll yang akan melakukan proses filter
     * @param page halaman dari data produk yang akan ditampilkan
     * @param pageSize jumlah data per halaman yang akan ditampilkan
     * @param category kategori dari produk
     * @param maxPrice harga batas atas
     * @param minPrice harga batas bawah
     * @param predSearch keyword search
     */
    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predSearch, int minPrice, int maxPrice, ProductCategory category){
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product product : getJsonTable())
            {
                double productPrice = product.price;
                if(productPrice > minPrice && productPrice < maxPrice && predSearch.predicate(product) && product.category == category)
                {
                    newList.add(product);
                }
            }
        }
        else if(minPrice == 0.0 && maxPrice != 0.0)
        {
            for(Product product : getJsonTable())
            {
                double productPrice = product.price;
                if(productPrice < maxPrice && predSearch.predicate(product) && product.category == category)
                {
                    newList.add(product);
                }
            }
        }
        else if(maxPrice == 0.0 && minPrice != 0.0)
        {
            for(Product product : getJsonTable())
            {
                double productPrice = product.price;
                if(productPrice > minPrice && predSearch.predicate(product) && product.category == category)
                {
                    newList.add(product);
                }
            }
        }
        else {
            for(Product product : getJsonTable())
            {
                double productPrice = product.price;
                if(predSearch.predicate(product) && product.category == category)
                {
                    newList.add(product);
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

