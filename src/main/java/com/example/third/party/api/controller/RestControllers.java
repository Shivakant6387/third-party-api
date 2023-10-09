package com.example.third.party.api.controller;

import com.example.third.party.api.model.AirlineModel;
import com.example.third.party.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class RestControllers {
    @Autowired
    private PostService postService;

    @GetMapping("/getPosts")
    List<Map<String, Object>> getAllPosts() {
        return postService.getPost();
    }

    @GetMapping("/getPostsById/{id}")
    Map<String, Object> getAllPosts(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PostMapping("/insertPosts")
    Map<String, Object> insertPost(@RequestBody Map<String, Object> payload) {
        return postService.insertPost(payload);
    }

    @PutMapping("/updatePosts/{id}")
    Map<String, Object> insertPost(@RequestBody Map<String, Object> payload, @PathVariable int id) {
        return postService.updatePost(payload, id);
    }

    @PutMapping("/deletePosts/{id}")
    Map<String, Object> insertPosts(@PathVariable int id) {
        return postService.deletePostById(id);
    }

    @GetMapping("/cities")
    public String getAllCities() {
        String url = "https://countriesnow.space/api/v0.1/countries";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/population")
    public String getPopulation() {
        String url = "https://countriesnow.space/api/v0.1/countries/population/cities";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


    @PostMapping("/airline_rest")
    public ResponseEntity<?> createAirlineRest(@RequestBody AirlineModel body) {
        try {

            String uri = "https://api.instantwebtools.net/v1/airlines";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.<String>postForEntity(uri, body, String.class);

            return new ResponseEntity<>(result.getStatusCodeValue() == 200 ? "Airline created successfully" : "Airline Not created successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
