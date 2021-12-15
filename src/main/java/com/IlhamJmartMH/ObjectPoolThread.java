package com.IlhamJmartMH;

import java.util.Vector;
import java.lang.Object;
import java.util.function.Function;

public class ObjectPoolThread <T> extends Thread{
    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<T>();
    private Function<T, Boolean> routine;

    /**
     * Constructor untuk ObjectPoolThread
     * @param name nama thread
     * @param routine method yang dijadikan routine
     */
    public ObjectPoolThread(String name, Function<T, Boolean> routine){
        super(name);
        this.routine = routine;
    }

    /**
     * Constructor untuk ObjectPoolThread
     * @param routine method yang dijadikan routine
     */
    public ObjectPoolThread(Function<T, Boolean> routine){
        super();
        this.routine = routine;
    }

    /**
     * Method untuk mengembalikan jumlah thread yang berjalan
     * @return jumlah thread yang berjalan
     */
    public int size(){
        return objectPool.size();
    }

    /**
     * Method untuk menambah object pada thread (disinkronisasi object monitornya agar tidak racing condition)
     * @param object object yang ingin ditambahkan
     */
    public synchronized void add(T object){
        objectPool.add(object);
        this.notify();
    }

    /**
     * Method untuk keluar dari loop run pada thread agar thread mati
     */
    public synchronized void exit(){
        exitSignal = true;
        this.notify();
    }

    /**
     * Method yang akan dijalankan oleh thread ketika thread hidup/bangun
     */
    public void run()
    {
        while(true)
        {
            //Loop mencari object yang sudah selesai diproses pada timekeeper
            for(int i = 0; i < objectPool.size(); i++)
            {
                T object = objectPool.get(i);
                if (routine.apply(object))      //jika apply routine timekeeper return true, artinya proses sudah selesai
                {
                    objectPool.remove(i);       //karena sudah selesai akan diremove
                    --i;
                }
            }
            synchronized (this)                     //disinkronisasi
            {
                while (objectPool.isEmpty() && !exitSignal)         //Selama proses belum selesai atau belum ada tanda sinyal exit
                {
                    try { this.wait(); }                            //maka thread akan dibuat wait atau tidur
                    catch (InterruptedException e) {}
                }
                if(exitSignal) {                        //jika sudah ada sinyal exit, maka akan keluar loop untuk mematikan thread
                    break;
                }
            }
        }
    }
}
