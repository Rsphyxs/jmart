package com.IlhamJmartMH;
import java.io.*;
import java.util.*;

import com.IlhamJmartMH.dbjson.JsonDBEngine;
import com.IlhamJmartMH.dbjson.JsonTable;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart {

    public static long DELIVERED_LIMIT_MS;
    public static long ON_DELIVERY_LIMIT_MS;
    public static long ON_PROGRESS_LIMIT_MS;
    public static long WAITING_CONF_LIMIT_MS;

    class Country {
        public String name;
        public int population;
        public List<String> listOfStates;
    }

    public static void main(String args[]) {
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()) );

//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);

//        try {
//            List<Product>list = read("D:\\Ilham\\UI\\Smt5\\Oop\\Prak\\Modul 1\\jmart\\src\\GoldenSample\\randomProductList.json");
//            List<Product> filteredPrice = filterByPrice(list, 0.0, 20000.0);
//            filteredPrice.forEach(product -> System.out.println(product.price));
//            List<Product> filteredName = filterByName(list, "GTX", 1, 5);
//            filteredName.forEach(product -> System.out.println(product.name));
//            List<Product> filteredId = filterByAccountId(list, 1, 0, 5);
//            filteredId.forEach(product -> System.out.println(product.name));
//        }
//        catch (Throwable t){
//            t.printStackTrace();
//        }

//        try {
//            String filepath = "a/b/c/account.json";
//
//            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
//            tableAccount.add(new Account("name", "email", "password", 0.0));
//            tableAccount.writeJson();
//
//            tableAccount = new JsonTable<>(Account.class, filepath);
//            tableAccount.forEach(account -> System.out.println(account.toString()));
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }

//        try{
//            // sesuaikan argument dibawah dengan lokasi resource file yang Anda unduh di EMAS!
//            JsonTable<Payment> table = new JsonTable<>(Payment.class, "D:\\Ilham\\UI\\Smt5\\Oop\\Prak\\Modul 1\\jmart\\src\\GoldenSample\\randomPaymentList.json");
//            // membuat thread untuk payment pool
//            ObjectPoolThread<Payment>paymentPool =new ObjectPoolThread<Payment>("Thread-pp", Jmart::paymentTimekeeper);
//            // menjalankan thread (ingat menggunakan start bukan run), run melakukan instruksi dalam current thread
//            paymentPool.start();
//            //tambahkan seluruh payment hasil baca ke dalam pool
//            table.forEach(payment ->paymentPool.add(payment));
//            // berikan sinyal untuk keluar dari routine apabila seluruh objek telah di proses
//            while (paymentPool.size() != 0);
//            paymentPool.exit();
//            // tunggu hingga thread selesai di eksekusi
//            while (paymentPool.isAlive());
//            // thread telah berhasil di selesaikan
//            System.out.println("Thread exited successfully");
//            // cek hasil output
//            Gson gson = new Gson();
//            table.forEach(payment -> {
//                String history = gson.toJson(payment.history);
//                System.out.println(history);
//            });
//        }
//        catch (Throwable t){
//            t.printStackTrace();
//        }
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

    public static boolean paymentTimekeeper(Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "DELIVERED"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "FINISHED"));
                return true;
            }
        }
        return false;
    }

}


