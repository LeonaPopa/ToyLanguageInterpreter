package model.types;

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
}
