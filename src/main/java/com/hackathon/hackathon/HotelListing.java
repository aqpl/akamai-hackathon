package com.hackathon.hackathon;

import com.google.gson.annotations.Expose;

public class HotelListing {

  @Expose
  private String name;
  @Expose
  private String hostName;
  @Expose
  private String neighbourhoodGroup;
  @Expose
  private String neighbourhood;
  @Expose
  private double latitude;
  @Expose
  private double longitude;
  @Expose
  private String roomType;
  @Expose
  private int price;

  public String name() {
    return this.name;
  }

  public String hostName() {
    return this.hostName;
  }

  public String neighbourhoodGroup() {
    return this.neighbourhoodGroup;
  }

  public String neighbourhood() {
    return this.neighbourhood;
  }

  public double latitude() {
    return this.latitude;
  }

  public double longitude() {
    return this.longitude;
  }

  public String roomType() {
    return this.roomType;
  }

  public int price() {
    return this.price;
  }

  public HotelListing name(final String name) {
    this.name = name;
    return this;
  }

  public HotelListing hostName(final String hostName) {
    this.hostName = hostName;
    return this;
  }

  public HotelListing neighbourhoodGroup(final String neighbourhoodGroup) {
    this.neighbourhoodGroup = neighbourhoodGroup;
    return this;
  }

  public HotelListing neighbourhood(final String neighbourhood) {
    this.neighbourhood = neighbourhood;
    return this;
  }

  public HotelListing latitude(final double latitude) {
    this.latitude = latitude;
    return this;
  }

  public HotelListing longitude(final double longitude) {
    this.longitude = longitude;
    return this;
  }

  public HotelListing roomType(final String roomType) {
    this.roomType = roomType;
    return this;
  }

  public HotelListing price(final int price) {
    this.price = price;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    HotelListing hotelListing = (HotelListing) obj;
    return this.name.equals(hotelListing.name) && this.hostName.equals(hotelListing.hostName);
  }

  @Override
  public int hashCode() {
    return name.hashCode() + hostName.hashCode();
  }


}
