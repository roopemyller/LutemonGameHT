package com.example.lutemongameht;

import android.content.Context;

import com.example.lutemongameht.lutemons.Lutemon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static Storage storage = null;

    private Storage(){

    }

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public int getNumberOfDaysPassed() {
        int numberOfDaysPassed = 0;
        int totalBattles = 0;
        for (Lutemon l : storage.lutemons){
            numberOfDaysPassed++;
            numberOfDaysPassed += l.getTrainingDays();
            totalBattles += l.getNumberOfBattles();
        }
        totalBattles = totalBattles / 2;
        numberOfDaysPassed += totalBattles;
        return numberOfDaysPassed;

    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon){
        lutemons.add(lutemon);
    }


    public Lutemon getLutemon(int pos){
        return lutemons.get(pos);
    }


    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            writer.writeObject(lutemons);
            writer.close();
        } catch (IOException e) {
            System.out.println("Tietojen tallentaminen ei onnistunut");
        }
    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream reader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) reader.readObject();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tietojen lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Tietojen lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Tietojen lukeminen ei onnistunut");
            e.printStackTrace();
        }
    }

    public void clearList() {
        this.lutemons.clear();
    }

    public int getNumberOfTrainingDays() {
        int numberOfTrainingDays = 0;
        for(Lutemon l : lutemons){
            numberOfTrainingDays += l.getTrainingDays();
        }
        return numberOfTrainingDays;
    }

    public int getNumberOfBattles() {
        int numberOfBattles = 0;
        for(Lutemon l : lutemons){
            numberOfBattles += l.getNumberOfBattles();
        }
        return numberOfBattles / 2;
    }
}

