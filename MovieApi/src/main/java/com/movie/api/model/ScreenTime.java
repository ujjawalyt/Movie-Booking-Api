package com.movie.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ScreenTime {

@Id 
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Integer screenTimeId;
private String theaterName;
private String screen;
private String showTime;
private String ShowName;

@ManyToOne()
@JoinColumn(name = "managerId")
private Manager manager;

@ManyToOne
@JoinColumn(name="theaterId")
private Theater theater;




}

