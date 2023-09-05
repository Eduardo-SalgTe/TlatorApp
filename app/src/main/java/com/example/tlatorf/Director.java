package com.example.tlatorf;

public class Director {

    private String torn_id;
    private String nomb;
    private String apert;

    public Director(String idDir, String nombre, String apellidoP) {
        this.torn_id = idDir;
        this.nomb = nombre;
        this.apert = apellidoP;
    }
    public String getNombre(){
        return nomb;
    }
    public String getTorn_id(){ return torn_id;}
    public String getApert(){ return apert;}
}