package model.program.fileTable;

import java.io.BufferedReader;

public interface MyIFileTable {
    void add(StringValue filename, BufferedReader fileDescriptor);
    void remove(StringValue filename);
    BufferedReader get(StringValue filename);
    boolean contains(StringValue filename);
    Map<StringValue, BufferedReader> getAll();
}
