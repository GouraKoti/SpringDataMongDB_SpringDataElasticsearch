package com.molcon.henkel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HenkelData {
    @Id //for primary key
    private String mcid;
    private String commonName;
    private String description;
    private String sapCs;
}
