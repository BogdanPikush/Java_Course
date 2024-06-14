package com.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "things")
public class Things {
    @Id
    private String id;
    private String idUser;
    private String name;
    private String description;
    private String give;

    public Things(String id, String idUser, String name, String description, String give) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.give = give;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }
}
