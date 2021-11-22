package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.Algorithm;
import com.IlhamJmartMH.Coupon;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import com.IlhamJmartMH.dbjson.JsonTable;

import java.util.ArrayList;
import java.util.List;

public class CouponController implements BasicGetController<Coupon>{

    @JsonAutowired(value = Coupon.class,filepath = "D:\\Ilham\\UI\\Smt5\\Oop\\Prak\\Modul 1\\jmart\\src\\GoldenSample\\random\\fileCoupon,json")
    public static JsonTable<Coupon> couponTable;

    boolean canApply(int id, double price, double discount){
        for(Coupon coupon : couponTable){
            if(coupon.id == id)
                return  coupon.canApply(price, discount);
        }
        return false;
    }

    List<Coupon> getAvailable(int page, int pageSize){
        ArrayList<Coupon> coupons = new ArrayList<>();
        for(Coupon coupon : couponTable){
            if(!coupon.isUsed()){
                coupons.add(coupon);
            }
        }
        return Algorithm.<Coupon>paginate(coupons, page, pageSize, coupon ->true);
    }

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    boolean isUsed(int id){
        for(Coupon coupon : couponTable){
            if(coupon.id == id){
                return coupon.isUsed();
            }
        }
        return false;
    }

}
