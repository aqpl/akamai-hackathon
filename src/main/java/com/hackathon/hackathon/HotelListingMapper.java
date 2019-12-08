package com.hackathon.hackathon;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.hackathon.hackathon.Constants.HotelListingTables.*;


@Component
public class HotelListingMapper implements RowMapper<HotelListing> {
  @Override
  public HotelListing mapRow(ResultSet resultSet, int i) throws SQLException {
    HotelListing hotelListing = new HotelListing()
      .name(resultSet.getString(NAME))
      .hostName(resultSet.getString(HOST_NAME))
      .neighbourhoodGroup(resultSet.getString(NEIGHBOURHOOD_GROUP))
      .neighbourhood(resultSet.getString(NEIGHBOURHOOD))
      .latitude(resultSet.getDouble(LATITUDE))
      .roomType(resultSet.getString(ROOM_TYPE))
      .longitude(resultSet.getDouble(LONGITUDE))
      .price(resultSet.getInt(PRICE));
    return hotelListing;
  }
}
