package com.molcon.henkel.repo;


import com.molcon.henkel.entity.ElasticHenkel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepo extends ElasticsearchRepository<ElasticHenkel, String> {
}


