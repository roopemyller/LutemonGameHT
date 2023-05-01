package com.example.lutemongameht.lutemons;

import com.example.lutemongameht.Storage;

import java.io.Serializable;

public class Lutemon implements Serializable {

    protected String name;
    protected String color;
    protected int attack;
    protected int defence;
    protected int exp;
    protected int health;
    protected int maxHealth;
    protected int id;
    protected int image;
    protected int numberOfWins = 0;
    protected int numberOfBattles = 0;
    protected int trainingDays = 0;

    public Lutemon(String name) {
        this.name = name;
        this.id = Storage.getInstance().getLutemons().size();
    }

    public int getTrainingDays() {
        return trainingDays;
    }

    public void addTrainingDays() {
        this.trainingDays++;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = this.exp + exp;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getImage() { return image;}
    public int getId() {
        return id;
    }
    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void addNumberOfWins() {
        this.numberOfWins++;
    }

    public int getNumberOfBattles() {
        return numberOfBattles;
    }

    public void addNumberOfBattles() {
        this.numberOfBattles++;
    }

    public void defence(Lutemon attackerLutemon){
        this.health -= (Math.random() * 3 + (attackerLutemon.attack() - (this.defence)));
    }

    public int attack(){
        return attack;
    }

    public void restoreHealth() {
        this.health = this.maxHealth;
    }
}
