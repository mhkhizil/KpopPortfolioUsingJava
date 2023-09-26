/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kpop_Portfilio_System;

/**
 *
 * @author yemyat
 */
public class KpopArtist {

    private String artistName;
    private String group;
    private int age;
    private String position;
    private int debutYear;
    private String famousSong;

    // Constructor
    public KpopArtist(String artistName, String group, int age, String position, int debutYear, String famousSong) {
        this.artistName = artistName;
        this.group = group;
        this.age = age;
        this.position = position;
        this.debutYear = debutYear;
        this.famousSong = famousSong;
    }

    // Getters
    public String getArtistName() {
        return artistName;
    }

    public String getGroup() {
        return group;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public String getFamousSong() {
        return famousSong;
    }

    // Setters
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    public void setFamousSong(String famousSong) {
        this.famousSong = famousSong;
    }
}
