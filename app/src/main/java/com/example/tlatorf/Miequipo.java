package com.example.tlatorf;

public class Miequipo {

    private String equipo_id;
    private String nombre;
    private String torneo_fk;

    public Miequipo(String equipo_id, String nombre, String torneo_fk) {
        this.equipo_id = equipo_id;
        this.nombre = nombre;
        this.torneo_fk = torneo_fk;
    }
    public String getNombre(){
        return nombre;
    }
    public String getEquipo_id(){ return equipo_id;}
    public String getTorneo_fk(){ return torneo_fk;}
}