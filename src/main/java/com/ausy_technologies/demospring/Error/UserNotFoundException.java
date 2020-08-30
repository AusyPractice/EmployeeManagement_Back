package com.ausy_technologies.demospring.Error;

public class UserNotFoundException extends RuntimeException { // Ne definim propria clasa de exceptie. Atentie ca e o exceptie de RunTime(unchecked) nu de compile time(checked)

    private int id;

    public UserNotFoundException(int id)
    {
        this.id=id;
    }

    @Override
    public String toString() {
        return "No User found with id "+id;
    }
}
