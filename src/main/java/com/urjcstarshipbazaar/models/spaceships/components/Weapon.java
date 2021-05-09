package com.urjcstarshipbazaar.models.spaceships.components;

public class Weapon {

    private WeaponType weaponType;
    private double potencyGj;

    public Weapon(WeaponType weaponType, double potencyGj) {
        this.weaponType = weaponType;
        this.potencyGj = potencyGj;
    }

    public Weapon() {

    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public double getPotencyGj() {
        return potencyGj;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setPotencyGj(double potencyGj) {
        this.potencyGj = potencyGj;
    }

}
