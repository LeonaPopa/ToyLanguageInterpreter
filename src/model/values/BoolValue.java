package model.values;

import model.types.BoolType;
import model.types.TypeInterface;

public class BoolValue implements ValueInterface{
    boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }
}
