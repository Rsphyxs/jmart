package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.Algorithm;
import com.IlhamJmartMH.dbjson.JsonTable;
import com.IlhamJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Class BasicGetController yang akan mengatur aktivitas pengambilan data akun dan juga data produk
 * @author Muhammad Ilham M S
 * @version 16 Desember 2021
 */
public interface BasicGetController<T extends Serializable> {

    /**
     * Method getById yang akan mengambil data akun berdasarkan id
     * @param id adalah id dari akun
     */
    @GetMapping("/id")
    default T getById(int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable();

    /**
     * Method getPage yang akan mengambil data produk berdasarkan halaman dan ukuran halaman
     * @param page adalah halaman dari tampilan produk
     * @param pageSize adalah ukuran dari produk perhalaman
     */
    @GetMapping("/page")
    default List<T> getPage (int page, int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e->true);
    }
}
