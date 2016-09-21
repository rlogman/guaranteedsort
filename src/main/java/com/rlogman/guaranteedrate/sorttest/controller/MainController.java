package com.rlogman.guaranteedrate.sorttest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rlogman.guaranteedrate.sorttest.LineParser;
import com.rlogman.guaranteedrate.sorttest.Person;
import com.rlogman.guaranteedrate.sorttest.PersonStore;
import com.rlogman.guaranteedrate.sorttest.SortAlgorithms;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class MainController {

  @Autowired
  private PersonStore personStore;

  @Autowired
  private LineParser lineParser;

  private static final String PEOPLE_ROUTE = "/people";
  private static final String SORTED_PEOPLE_ROUTE = PEOPLE_ROUTE + "/{sortType}";

  @RequestMapping(value = PEOPLE_ROUTE, method = RequestMethod.POST)
  @ApiOperation(
      value = "Inserts a single person from a data line in any of the 3 formats supported (pipe, comma or space separated)",
      response = Void.class)
  public void insertPerson(
      @ApiParam(value = "List of profile ids to be assigned") @RequestBody String personLine) {
    personStore.add(lineParser.parse(personLine));
  }

  @RequestMapping(value = SORTED_PEOPLE_ROUTE, method = RequestMethod.GET)
  @ApiOperation(value = "Gets all people, sorted by gender, birthdate or name)",
      response = Person.class, responseContainer = "Collection")
  public List<Person> findByGender(@ApiParam @PathVariable final String sortBy) {
    if ("gender".equals(sortBy)) {
      return personStore.getOrderedPeople(SortAlgorithms.get(SortAlgorithms.BY_GENDER));
    }
    if ("birthdate".equals(sortBy)) {
      return personStore.getOrderedPeople(SortAlgorithms.get(SortAlgorithms.BY_BIRTHDAY));
    }
    if ("name".equals(sortBy)) {
      return personStore.getOrderedPeople(SortAlgorithms.get(SortAlgorithms.BY_NAME));
    }
    throw new IllegalArgumentException(String.format("Sort crtierion not supported: %s", sortBy));
  }
}
