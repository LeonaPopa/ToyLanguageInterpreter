package model.types;

import model.values.StringValue;
import model.values.ValueInterface;

public class StringType implements TypeInterface{
    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }

    @Override
    public TypeInterface deepCopy() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringType)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "string";
    }
}
