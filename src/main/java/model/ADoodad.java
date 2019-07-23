package model;

import lombok.Getter;
import lombok.Setter;

// first lombok, generates getter and setter methods
@Getter
@Setter

/*
abstract keyword is used to create a abstract class and method.
Abstract class in java can’t be instantiated.
An abstract class is mostly used to provide a base for subclasses to extend
    and implement the abstract methods and override or use the implemented methods in abstract class.
 */
public abstract class ADoodad {
    long id = 0;

    long getUniqueId() {
        return id++;
    }

    // does image fill whole block
    //public boolean fullSize = true;

    // protected is a version of public restricted only to subclasses
    protected String name = "";
    protected int coordX = 0;
    protected int coordY = 0;

    protected ADoodad(String name, int coordX, int coordY) {
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;

        //make random ID
        this.id = getUniqueId();
    }


    //TODO Gui variables and getters

}
