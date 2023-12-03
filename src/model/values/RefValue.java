package model.values;

import model.types.RefType;
import model.types.TypeInterface;

public class RefValue implements ValueInterface{
    int address;
    TypeInterface locationType;
    public int  getAddr(){ return address;}

    public RefValue(int addr, TypeInterface locationType){
        this.address = addr;
        this.locationType = locationType;
    }

    @Override
    public TypeInterface getType(){return new RefType(locationType);
    }
    public String toString(){ return "("+ address + "," + locationType + ")";}

}
