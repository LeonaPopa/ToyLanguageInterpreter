package model.types;

import model.values.RefValue;
import model.values.ValueInterface;

public class RefType implements TypeInterface{
    @Override
    public ValueInterface defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public TypeInterface deepCopy() {
        return new RefType(inner.deepCopy());
    }

    TypeInterface inner;

    public RefType(TypeInterface inner){ this.inner = inner;}

    public TypeInterface getInner(){return inner;}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RefType)
            return inner.equals(((RefType) obj).getInner());
        else
            return false;
    }
    @Override
    public String toString(){
        return "Ref("+inner.toString()+")";}
}
