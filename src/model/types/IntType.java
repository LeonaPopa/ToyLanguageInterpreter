package model.types;

public class IntType implements TypeInterface{
    @Override
    public boolean equals(Object another){
        if(another instanceof IntType)
            return true;
        else
            return false;
    }
    @Override
    public String toString() {
        return "int";
    }
}

