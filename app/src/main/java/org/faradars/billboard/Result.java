package org.faradars.billboard;

public class Result<T extends String> {
    T object;
    String status;

    public T getObject() {

        return object;
    }
    public String getStatus(){
        return status;
    }
}
