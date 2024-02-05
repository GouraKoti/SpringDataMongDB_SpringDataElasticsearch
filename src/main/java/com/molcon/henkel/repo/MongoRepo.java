
package com.molcon.henkel.repo;


import com.molcon.henkel.entity.MongoHenkel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoRepo extends MongoRepository<MongoHenkel, String> {
    //create custom query method
    List<MongoHenkel> findBycommonName(String commonName);

    @Query("{ 'commonName' : ?0 }")
    List<MongoHenkel> findByTheChemicalsCommonname(String commonName);

}

