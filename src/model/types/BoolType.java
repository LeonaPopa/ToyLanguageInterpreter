package model.types;

import model.values.BoolValue;
import model.values.ValueInterface;

public class BoolType implements TypeInterface{
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BoolType)
            return  true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }
}
