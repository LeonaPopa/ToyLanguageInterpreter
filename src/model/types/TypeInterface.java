package model.types;

import model.values.ValueInterface;

public interface TypeInterface {
    ValueInterface defaultValue();
    TypeInterface deepCopy();
}
