package com.patika.hw.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class User {

    private final UUID id;
    @NonNull
    private final String kimlikNo;
    @NonNull
    private final String name;
    @NonNull
    private final String password;
    private Double balance;

    public User(@JsonProperty("id") UUID id,
                @NonNull @JsonProperty("kimlikNo") String kimlikNo,
                @NonNull @JsonProperty("name") String name,
                @NonNull @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.kimlikNo = kimlikNo;
        this.password = password;
        this.balance = 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User)
            return ((User) obj).id.equals(this.id);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
