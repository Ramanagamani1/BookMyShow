package com.scaler.bookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Actor extends BaseModel{
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
