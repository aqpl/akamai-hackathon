package com.hackathon.hackathon;

import com.google.gson.annotations.Expose;

import java.util.List;

public class HotelListingsResponse {

  @Expose
  private List<HotelListing> hotelListingList;

  public HotelListingsResponse(List<HotelListing> hotelListingList) {
    this.hotelListingList = hotelListingList;
  }

  public List<HotelListing> hotelListingList() {
    return this.hotelListingList;
  }

  public HotelListingsResponse hotelListingList(final List<HotelListing> hotelListingList) {
    this.hotelListingList = hotelListingList;
    return this;
  }

}
