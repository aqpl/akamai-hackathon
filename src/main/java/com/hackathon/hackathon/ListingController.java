package com.hackathon.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListingController {

  @Autowired
  private HotelListingService hotelListingService;

  @GetMapping("/getAllHotels")
  public HotelListingsResponse getAllHotels() {
    return hotelListingService.getAllHotels();
  }

  @GetMapping("/getHotelsByName")
  public HotelListingsResponse getHotelsByName(@RequestParam(name = "name") String hotelName) {
    return hotelListingService.getHotelsByName(hotelName);
  }

  @GetMapping("/getHotelsBySimilarNames")
  public HotelListingsResponse getHotelsBySimilarName(@RequestParam(required = false, name = "name") String hotelName) {
    return hotelListingService.getHotelsBySimilarNames(hotelName);
  }

  @GetMapping("/filterHotels")
  public HotelListingsResponse getHotelsByFilters(@RequestParam(required = false, name = "sortType") SortType sortType,
                                                  @RequestParam(required = false, name = "regions") List<String> regions) {
    FilterHotelsRequest request = new FilterHotelsRequest().sortType(sortType).regions(regions);
    request.validate();
    return hotelListingService.getHotelsByFilters(request);
  }

  @GetMapping("/customFiltersReq")
  public HotelListingsResponse getHotelsByCustomFilters(@RequestParam(required = false, name = "name") String name,
                                                        @RequestParam(required = false, name = "sourceLat") Double sourceLat,
                                                        @RequestParam(required = false, name = "sourceLong") Double sourceLong,
                                                        @RequestParam(required = false, name = "min") Integer min,
                                                        @RequestParam(required = false, name = "max") Integer max,
                                                        @RequestParam(required = false, name = "regions") List<String> regions,
                                                        @RequestParam(required = false, name = "neighbourhood") List<String> neighbourhood,
                                                        @RequestParam(required = false, name = "radius") Double radius) {
    CustomFiltersRequest req = new CustomFiltersRequest()
      .name(name)
      .sourceLat(sourceLat)
      .sourceLong(sourceLong)
      .min(min)
      .max(max)
      .regions(regions)
      .neighbourhood(neighbourhood)
      .radius(radius);
    return hotelListingService.getHotelsByCustomFilters(req);
  }

}
