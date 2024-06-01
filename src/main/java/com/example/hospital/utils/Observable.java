package com.example.hospital.utils;

import java.util.ArrayList;

public class Observable {
    ArrayList<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void notifyObservers(){
        observers.forEach(observer -> observer.update());
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }


}