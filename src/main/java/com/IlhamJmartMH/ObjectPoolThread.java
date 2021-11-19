package com.IlhamJmartMH;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread {
    private boolean exitSignal;
    private Vector<T> objectPool;
    private Function<T, Boolean> routine;

    public ObjectPoolThread(String name, Function<T, Boolean> routine) {
        super(name);
        this.routine = routine;
    }

    public ObjectPoolThread(Function<T, Boolean> routine) {
        this.routine = routine;
    }

    public synchronized void add(T object) {
        objectPool.add(object);
        this.notify();
    }

    public void exit() {
        exitSignal = true;
    }

    public synchronized void run() {
        while (!exitSignal) {
            try {
                for (int i = 0; i < objectPool.size(); i++) {
                    if (routine.apply(objectPool.get(i))) {
                        objectPool.remove(objectPool.get(i));
                    }
                }
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return objectPool.size();
    }

}
