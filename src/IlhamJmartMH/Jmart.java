package IlhamJmartMH;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            List<Product>filtered = filterByPrice(list, 0.0, 20000.0);
            filtered.forEach(product -> System.out.println(product.price));
        }
        catch (Throwable t){
            t.printStackTrace();
        }

//        String filepath = "/Ilham/UI/Smt5/Oop/Prak/Modul 1/jmart/city.json";
//        Gson gson = new Gson();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(filepath));
//            Country input = gson.fromJson(br, Country.class);
//            System.out.println("name: " + input.name);
//            System.out.println("population: " + input.population);
//            System.out.println("states: ");
//            input.listOfStates.forEach(state -> System.out.println(state));
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
        List<Product>tempList = new ArrayList<Product>();
        for(Product p : list)
        {
            if(p.category == category)
            {
                tempList.add(p);
            }
        }
        return tempList;
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

    public static List<Product> read(String filepath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);
        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);
        return list;
    }
}


