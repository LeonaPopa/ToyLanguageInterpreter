package model.values;

import model.types.StringType;
import model.types.TypeInterface;

import java.util.Objects;

public class StringValue implements ValueInterface{
    private String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue)
            return true;
        return false;
    }

}
