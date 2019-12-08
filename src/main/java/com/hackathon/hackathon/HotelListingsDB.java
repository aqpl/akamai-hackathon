package com.hackathon.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackathon.hackathon.Constants.HotelListingQuery.*;
import static com.hackathon.hackathon.Constants.HotelListingTables.*;
import static com.hackathon.hackathon.SortType.ASC;

@Service
public class HotelListingsDB {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  @Autowired
  private HotelListingMapper hotelListingMapper;

  public List<HotelListing> getAllHotels() {
    return jdbcTemplate
      .query(GET_ALL_HOTELS,
        new MapSqlParameterSource(),
        hotelListingMapper);
  }

  public List<HotelListing> getHotelsByName(String hotelName) {
    String nameLikeQuery = "%" + hotelName + "%";
    return jdbcTemplate
      .query(GET_HOTEL_BY_NAME,
        new MapSqlParameterSource(NAME, nameLikeQuery),
        hotelListingMapper);
  }

  public List<HotelListing> getAllHotelsByRegionFilters(List<String> regions) {
    return jdbcTemplate.query(GET_HOTELS_BY_FILTERS,
      new MapSqlParameterSource(NEIGHBOURHOOD_GROUP, regions), hotelListingMapper);
  }

  public List<HotelListing> getHotelsSortedByPrice(SortType sortType) {
    String qry = GET_HOTELS_SORTED_BY_PRICE_DESC;
    if (sortType == ASC) {
      qry = GET_HOTELS_SORTED_BY_PRICE_ASC;
    }
    return jdbcTemplate.query(qry,
      new MapSqlParameterSource(SORT_TYPE, sortType.name()), hotelListingMapper);
  }

  public List<HotelListing> getHotelsSortedByPriceAndFilteredByRegion(SortType sortType, List<String> regions) {
    String qry = GET_HOTELS_FILTERED_BY_REGION_AND_SORTED_BY_PRICE_DESC;
    if (sortType == ASC) {
      qry = GET_HOTELS_FILTERED_BY_REGION_AND_SORTED_BY_PRICE_ASC;
    }
    return jdbcTemplate.query(qry,
      new MapSqlParameterSource(SORT_TYPE, sortType.name()).addValue(NEIGHBOURHOOD_GROUP, regions), hotelListingMapper);
  }

  public List<HotelListing> getHotelsByPriceRange(int priceMin, int priceMax) {
    return jdbcTemplate.query(GET_HOTELS_BY_PRICE_RANGE,
      new MapSqlParameterSource(PRICE_LOW, priceMin).addValue(PRICE_HIGH, priceMax), hotelListingMapper);
  }

}


