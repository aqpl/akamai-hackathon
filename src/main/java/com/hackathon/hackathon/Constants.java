package com.hackathon.hackathon;

public class Constants {

  public interface HotelListingTables {
    String NAME = "name";
    String HOST_NAME = "host_name";
    String NEIGHBOURHOOD_GROUP = "neighbourhood_group";
    String NEIGHBOURHOOD = "neighbourhood";
    String LONGITUDE = "longitude";
    String LATITUDE = "latitude";
    String ROOM_TYPE = "room_type";
    String PRICE = "price";
    String SORT_TYPE = "sortType";
    String PRICE_LOW = "priceLow";
    String PRICE_HIGH = "priceHigh";
  }

  public interface HotelListingQuery {
    String GET_ALL_HOTELS = "SELECT * FROM listings";
    String GET_HOTEL_BY_NAME = "SELECT * FROM listings WHERE name LIKE :name";
    String GET_HOTELS_BY_FILTERS = "SELECT * FROM listings WHERE neighbourhood_group IN(:neighbourhood_group)";
    String GET_HOTELS_SORTED_BY_PRICE_ASC = "SELECT * FROM listings ORDER BY price ASC";
    String GET_HOTELS_SORTED_BY_PRICE_DESC = "SELECT * FROM listings ORDER BY price DESC";
    String GET_HOTELS_FILTERED_BY_REGION_AND_SORTED_BY_PRICE_DESC = "SELECT * FROM listings WHERE neighbourhood_group IN(:neighbourhood_group) ORDER BY price DESC";
    String GET_HOTELS_FILTERED_BY_REGION_AND_SORTED_BY_PRICE_ASC = "SELECT * FROM listings WHERE neighbourhood_group IN(:neighbourhood_group) ORDER BY price ASC";
    String GET_HOTELS_BY_PRICE_RANGE = "SELECT * FROM listings WHERE price >= :priceLow AND price <= :priceHigh ORDER BY price ASC";
  }

}
