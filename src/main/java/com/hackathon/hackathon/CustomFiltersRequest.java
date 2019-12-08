package com.hackathon.hackathon;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class CustomFiltersRequest {

  @Expose
  private String name;
  @Expose
  private Double sourceLat;
  @Expose
  private Double sourceLong;
  @Expose
  private Integer min;
  @Expose
  private Integer max;
  @Expose
  private List<String> regions = new ArrayList<>();
  @Expose
  private List<String> neighbourhood = new ArrayList<>();
  @Expose
  private Double radius = 100000.0;// in kms

  public double radius() {
    return this.radius;
  }

  public String name() {
    return this.name;
  }

  public Double sourceLat() {
    return this.sourceLat;
  }

  public Double sourceLong() {
    return this.sourceLong;
  }

  public int min() {
    return this.min;
  }

  public int max() {
    return this.max;
  }

  public List<String> regions() {
    return this.regions;
  }

  public List<String> neighbourhood() {
    return this.neighbourhood;
  }

  public CustomFiltersRequest name(final String name) {
    this.name = name;
    return this;
  }

  public CustomFiltersRequest sourceLat(final Double sourceLat) {
    this.sourceLat = sourceLat;
    return this;
  }

  public CustomFiltersRequest sourceLong(final Double sourceLong) {
    this.sourceLong = sourceLong;
    return this;
  }

  public CustomFiltersRequest min(final Integer min) {
    this.min = min;
    if(isNull(min)) {
      this.min = 0;
    }
    return this;
  }

  public CustomFiltersRequest max(final Integer max) {
    this.max = max;
    if(isNull(max)) {
      this.max = 100000;
    }
    return this;
  }

  public CustomFiltersRequest regions(final List<String> regions) {
    this.regions = regions;
    if(isNull(regions)) {
      this.regions = new ArrayList<>();
    }
    return this;
  }

  public CustomFiltersRequest neighbourhood(final List<String> neighbourhood) {
    this.neighbourhood = neighbourhood;
    if(isNull(neighbourhood)) {
      this.neighbourhood = new ArrayList<>();
    }
    return this;
  }

  public CustomFiltersRequest radius(final Double radius) {
    this.radius = radius;
    if(isNull(radius)) {
      this.radius = 100000.0;
    }
    return this;
  }
}
