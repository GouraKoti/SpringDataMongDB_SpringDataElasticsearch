package com.molcon.henkel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "henkeldataindex", createIndex = true)
public class ElasticHenkel {
    @Id //for primary key
    private String mcid;
    private String commonName;
    private String description;
    private String sapCs;

}
