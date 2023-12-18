package model.types;

import model.values.IntValue;
import model.values.ValueInterface;

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

    @Override
    public ValueInterface defaultValue() {
        return new IntValue(0);
    }

    @Override
    public TypeInterface deepCopy() {
        return new IntType();
    }
}

