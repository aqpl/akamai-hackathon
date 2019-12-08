package com.hackathon.hackathon;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public class AntPatternCorsConfiguration extends CorsConfiguration {

  private List<String> allowedOriginsPatterns;
  private final AntPathMatcher pathMatcher;

  public AntPatternCorsConfiguration() {
    allowedOriginsPatterns = new ArrayList<>();
    pathMatcher = new AntPathMatcher();
    pathMatcher.setCaseSensitive(false);
  }

  public AntPatternCorsConfiguration(CorsConfiguration other) {
    this();
    setAllowCredentials(other.getAllowCredentials());
    setAllowedOrigins(other.getAllowedOrigins());
    setAllowedHeaders(other.getAllowedHeaders());
    setAllowedMethods(other.getAllowedMethods());
    setExposedHeaders(other.getExposedHeaders());
    setMaxAge(other.getMaxAge());
  }

  @Override
  public void addAllowedOrigin(String origin) {
    super.addAllowedOrigin(origin);
    allowedOriginsPatterns.add(origin);
  }

  @Override
  public void setAllowedOrigins(List<String> origins) {
    super.setAllowedOrigins(origins);
    this.allowedOriginsPatterns = origins;
  }

  public void addAllowedOrigins(String[] origins) {
    addAllowedOrigins(asList(origins));
  }

  public void addAllowedOrigins(List<String> origins) {
    origins.forEach(this::addAllowedOrigin);
  }

  @Override
  public String checkOrigin(String requestOrigin) {
    String result = super.checkOrigin(requestOrigin);
    return result != null ? result : checkOriginWithAntPatterns(requestOrigin);
  }

  private String checkOriginWithAntPatterns(String requestOrigin) {
    return allowedOriginsPatterns.stream()
      .filter(pattern -> pathMatcher.match(pattern, requestOrigin))
      .map(pattern -> requestOrigin)
      .findFirst()
      .orElse(null);
  }

  @Override
  public CorsConfiguration combine(CorsConfiguration other) {
    if (other == null) {
      return this;
    }
    CorsConfiguration config = new AntPatternCorsConfiguration(this);
    config.setAllowedOrigins(combine(this.getAllowedOrigins(), other.getAllowedOrigins()));
    config.setAllowedMethods(combine(this.getAllowedMethods(), other.getAllowedMethods()));
    config.setAllowedHeaders(combine(this.getAllowedHeaders(), other.getAllowedHeaders()));
    config.setExposedHeaders(combine(this.getExposedHeaders(), other.getExposedHeaders()));
    Boolean allowCredentials = other.getAllowCredentials();
    if (allowCredentials != null) {
      config.setAllowCredentials(allowCredentials);
    }
    Long maxAge = other.getMaxAge();
    if (maxAge != null) {
      config.setMaxAge(maxAge);
    }
    return config;
  }

  private List<String> combine(List<String> source, List<String> other) {
    if (other == null || other.contains(ALL)) {
      return source;
    }
    if (source == null || source.contains(ALL)) {
      return other;
    }
    Set<String> combined = new HashSet<>(source);
    combined.addAll(other);
    return new ArrayList<>(combined);
  }

  public static class CorsFilterBuilder {

    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;

    private CorsFilterBuilder() {
      allowedOrigins = newArrayList();
      allowedMethods = newArrayList("*");
      allowedHeaders = newArrayList("*");
    }

    public static CorsFilterBuilder newInstance() {
      return new CorsFilterBuilder();
    }

    public CorsFilterBuilder addAllowedOrigins(String[] origins) {
      return addAllowedOrigins(asList(origins));
    }

    public CorsFilterBuilder addAllowedOrigins(List<String> origins) {
      allowedOrigins.addAll(origins);
      return this;
    }

    public CorsFilterBuilder setAllowedMethods(List<String> methods) {
      allowedMethods = methods;
      return this;
    }

    public CorsFilterBuilder setAllowedHeaders(List<String> headers) {
      allowedHeaders = headers;
      return this;
    }

    public CorsFilter build() {
      AntPatternCorsConfiguration configuration = new AntPatternCorsConfiguration();
      configuration.addAllowedOrigins(allowedOrigins);
      configuration.setAllowedMethods(allowedMethods);
      configuration.setAllowedHeaders(allowedHeaders);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return new CorsFilter(source);
    }

  }
}
