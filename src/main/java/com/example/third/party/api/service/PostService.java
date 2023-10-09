package com.example.third.party.api.service;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Map<String, Object>> getPost();
    Map<String,Object>getPostById(int id);
    Map<String,Object>insertPost(Map<String,Object>payload);
    Map<String,Object>updatePost(Map<String,Object>payload,int id);
    Map<String,Object>deletePostById(int id);
}
