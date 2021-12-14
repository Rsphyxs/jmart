package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.Algorithm;
import com.IlhamJmartMH.dbjson.JsonTable;
import com.IlhamJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface BasicGetController<T extends Serializable> {

    @GetMapping("/id")
    default T getById(int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    default List<T> getPage (int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }
}
