package com.hackathon.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.hackathon.hackathon.DistanceCalculator.distance;
import static com.hackathon.hackathon.EditDistance.getEditDist;
import static com.hackathon.hackathon.SortType.NONE;
import static java.util.Objects.isNull;

@Service
public class HotelListingService {

  @Autowired
  private HotelListingsDB hotelListingsDB;

  public HotelListingsResponse getAllHotels() {
    return new HotelListingsResponse(hotelListingsDB.getAllHotels());
  }

  public HotelListingsResponse getHotelsByName(String hotelName) {
    return new HotelListingsResponse(hotelListingsDB.getHotelsByName(hotelName));
  }

  public HotelListingsResponse getHotelsBySimilarNames(String hotelName) {
    if(isNull(hotelName)){
      hotelName = "";
    }
    List<HotelListing> hotelListings = hotelListingsDB.getAllHotels();
    hotelListings = filterBySimilarNames(hotelListings, hotelName);
    hotelListings = unionResults(hotelListings, getHotelsByName(hotelName).hotelListingList());
    return new HotelListingsResponse(hotelListings);
  }

  private List<HotelListing> unionResults(List<HotelListing> hotelListings, List<HotelListing> hotelListingList) {
    Set<HotelListing> hotelListingsSet = new HashSet<>(hotelListingList);
    hotelListingsSet.addAll(hotelListings);
    return new ArrayList<>(hotelListingsSet);
  }

  private List<HotelListing> filterBySimilarNames(List<HotelListing> hotelListings, String hotelName) {
    List<HotelListing> hotelListingsFinal = new ArrayList<>();
    for (HotelListing hotelListing : hotelListings) {
      if (getEditDist(hotelListing.name(), hotelName) <= 8) {
        hotelListingsFinal.add(hotelListing);
      }
    }
    return hotelListingsFinal;
  }

  public HotelListingsResponse getHotelsByFilters(FilterHotelsRequest request) {
    List<HotelListing> hotelListingList;
    if (request.sortType() == NONE) {
      hotelListingList = hotelListingsDB.getAllHotelsByRegionFilters(request.regions());
    } else if (request.regions().isEmpty()) {
      hotelListingList = hotelListingsDB.getHotelsSortedByPrice(request.sortType());
    } else {
      hotelListingList = hotelListingsDB.getHotelsSortedByPriceAndFilteredByRegion(request.sortType(), request.regions());
    }
    return new HotelListingsResponse(hotelListingList);
  }

  public HotelListingsResponse getHotelsByCustomFilters(CustomFiltersRequest req) {
    List<HotelListing> hotelListingList = hotelListingsDB.getHotelsByPriceRange(req.min(), req.max());
    if (hotelListingList.isEmpty()) {
      return new HotelListingsResponse(hotelListingList);
    }
    hotelListingList = filterByName(hotelListingList, req.name());
    hotelListingList = filterByNeighbourHoodRegion(hotelListingList, req.regions());
    hotelListingList = filterByNeighbourhood(hotelListingList, req.neighbourhood());
    hotelListingList = filterByDistance(hotelListingList, req.sourceLat(), req.sourceLong(), req.radius());
    return new HotelListingsResponse(hotelListingList);
  }

  private List<HotelListing> filterByDistance(List<HotelListing> hotelListingList, Double sourceLat, Double sourceLong, double radius) {
    if (isNull(sourceLat) || isNull(sourceLong)) {
      return hotelListingList;
    }
    List<HotelListing> hotelListings = new ArrayList<>();
    for (HotelListing hotelListing : hotelListingList) {
      if (distance(sourceLat, sourceLong, hotelListing.latitude(), hotelListing.longitude()) <= radius) {
        hotelListings.add(hotelListing);
      }
    }
    return hotelListings;
  }

  private List<HotelListing> filterByNeighbourhood(List<HotelListing> hotelListingList, List<String> neighbourhood) {
    if (neighbourhood.isEmpty()) {
      return hotelListingList;
    }
    List<HotelListing> hotelListings = new ArrayList<>();
    Set<String> neighbourhoodSet = new HashSet<>(neighbourhood);
    for (HotelListing hotelListing : hotelListingList) {
      if (neighbourhoodSet.contains(hotelListing.neighbourhood())) {
        hotelListings.add(hotelListing);
      }
    }
    return hotelListings;
  }

  private List<HotelListing> filterByNeighbourHoodRegion(List<HotelListing> hotelListingList, List<String> regions) {
    if (regions.isEmpty()) {
      return hotelListingList;
    }
    List<HotelListing> hotelListings = new ArrayList<>();
    Set<String> regionsSet = new HashSet<>(regions);
    for (HotelListing hotelListing : hotelListingList) {
      if (regionsSet.contains(hotelListing.neighbourhoodGroup())) {
        hotelListings.add(hotelListing);
      }
    }
    return hotelListings;
  }

  private List<HotelListing> filterByName(List<HotelListing> hotelListingList, String name) {
    if (isNull(name) || name.length() == 0) {
      return hotelListingList;
    }
    List<HotelListing> hotelListings = new ArrayList<>();
    for (HotelListing hotelListing : hotelListingList) {
      if (getEditDist(hotelListing.name(), name) <= 8) {
        hotelListings.add(hotelListing);
      }
    }
    return hotelListings;
  }
}
