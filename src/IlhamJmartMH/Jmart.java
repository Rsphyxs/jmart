package IlhamJmartMH;
import java.io.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class Jmart {
    class Country {
        public String name;
        public int population;
        public List<String> listOfStates;
    }

    public static void main(String args[]) {
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);

        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);

        try {
            List<Product>list = read("D:\\Ilham\\UI\\Smt5\\Oop\\Prak\\Modul 1\\jmart\\src\\GoldenSample\\randomProductList.json");
            List<Product> filteredPrice = filterByPrice(list, 0.0, 20000.0);
            filteredPrice.forEach(product -> System.out.println(product.price));
            List<Product> filteredName = filterByName(list, "GTX", 1, 5);
            filteredName.forEach(product -> System.out.println(product.name));
            List<Product> filteredId = filterByAccountId(list, 1, 0, 5);
            filteredId.forEach(product -> System.out.println(product.name));
        }
        catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static List<Product> filterByAccountId(List<Product > list, int accountId, int page, int pageSize){
        Predicate<Product> temp = product -> (product.accountId == accountId);
        List<Product> paginatedList = paginate(list, page, pageSize, temp);
        return paginatedList;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
        List<Product>tempList = new ArrayList<Product>();
        for(Product p : list){
            if(p.category == category){
                tempList.add(p);
            }
        }
        return tempList;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize){
        String temp = search.toLowerCase();
        Predicate<Product> predicate = product -> product.name.toLowerCase().contains(temp );
        List<Product> paginatedList = paginate(list, page, pageSize, predicate);
        return paginatedList ;
    }



    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {
        List<Product> tempList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0){
            for(Product p : list){
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice)
                {
                    tempList.add(p);
                }
            }
        }
        else if(minPrice == 0.0){
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice < maxPrice)
                {
                    tempList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0){
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice > minPrice)
                {
                    tempList.add(p);
                }
            }
        }
        return tempList;
    }

    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred){
        List<Product> temp = new ArrayList<>();
        for(Product i : list){
            if(pred.predicate(i)){
                temp.add(i);
            }
        }
        List<Product> paginatedList = new ArrayList<>();
        int startIndex = page * pageSize;
        for(int i = startIndex; i < (startIndex + pageSize); i++){
            paginatedList.add(temp.get(i));
        }
        return paginatedList;
    }

    public static List<Product> read(String filepath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);
        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);
        return list;
    }
}


