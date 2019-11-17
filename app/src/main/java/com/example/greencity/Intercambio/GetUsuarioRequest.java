package com.example.greencity.Intercambio;

public class GetUsuarioRequest {
    private String id;

    public GetUsuarioRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
