package com.hackathon.hackathon;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import static com.hackathon.hackathon.ErrorCode.InvalidRequest;
import static com.hackathon.hackathon.SortType.NONE;
import static java.util.Objects.isNull;

public class FilterHotelsRequest {
  @Expose
  private SortType sortType = NONE;
  @Expose
  private List<String> regions = new ArrayList<>();

  public SortType sortType() {
    return this.sortType;
  }

  public List<String> regions() {
    return this.regions;
  }

  public FilterHotelsRequest sortType(final SortType sortType) {
    this.sortType = sortType;
    if(isNull(sortType)) {
      this.sortType = NONE;
    }
    return this;
  }

  public FilterHotelsRequest regions(final List<String> regions) {
    this.regions = regions;
    if(isNull(regions)) {
      this.regions = new ArrayList<>();
    }
    return this;
  }

  public void validate() {
    if(sortType == NONE && regions.isEmpty()) {
      throw new AppException(InvalidRequest);
    }
  }

}
