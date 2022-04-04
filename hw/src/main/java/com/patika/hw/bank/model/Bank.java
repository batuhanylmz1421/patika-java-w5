package com.patika.hw.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patika.hw.user.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Bank {

    private UUID id;
    @NonNull
    private String name;
    private Set<User> users;
    private Double bankAmount;

    public Bank(@JsonProperty("id") UUID id,
                @NonNull @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
        this.users = new HashSet<>();
        this.bankAmount = 500000.0; // default value.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bank)
            return ((Bank) obj).id.equals(this.id);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
