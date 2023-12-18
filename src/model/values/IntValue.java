package model.values;

import model.types.IntType;
import model.types.TypeInterface;

public class IntValue implements ValueInterface{
    int val;
    public IntValue(int v){val = v;}
    public int getVal(){return val;}
    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public TypeInterface getType() {
        return new IntType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new IntValue(val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntValue)
            return true;
        return false;
    }

}
