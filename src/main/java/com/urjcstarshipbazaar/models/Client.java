package com.urjcstarshipbazaar.models;

public class Client extends User {

    private String originPlanet;
    private String species;

    public Client(int id, String name, String nickname, String email, String originPlanet, String species) {
        super(id, name, nickname, email);
        this.originPlanet = originPlanet;
        this.species = species;
    }

    public Client(String name, String nickname, String email, String originPlanet, String species) {
        super(name, nickname, email);
        this.originPlanet = originPlanet;
        this.species = species;
    }

    public Client() {
        super();
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
