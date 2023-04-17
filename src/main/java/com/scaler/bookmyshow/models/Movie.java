package com.scaler.bookmyshow.models;

import javax.persistence.*;

import java.util.List;

@Entity
public class Movie extends BaseModel{
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Language> languages;

    private int rating;

    private int length;

    @ManyToMany
    private List<Actor> actors;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeature> movieFeatures;

}
