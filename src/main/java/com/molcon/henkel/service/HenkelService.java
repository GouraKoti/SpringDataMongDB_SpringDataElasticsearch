package com.molcon.henkel.service;

import com.molcon.henkel.entity.ElasticHenkel;
import com.molcon.henkel.entity.MongoHenkel;
import com.molcon.henkel.repo.ElasticRepo;
import com.molcon.henkel.repo.MongoRepo;
import com.molcon.henkel.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class HenkelService {
    @Autowired
    MongoRepo mongoRepo;

    @Autowired
    ElasticRepo elasticRepo;

    public ResponseEntity<String> uploadService(MultipartFile file) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        excelReader.readExcel(file, mongoRepo);
        return new ResponseEntity<>("File Uploaded successfully into Mongo", HttpStatus.OK);
    }

    public ResponseEntity<?> readMongo() {
        for (MongoHenkel mongoHenkel : mongoRepo.findAll()) {
            ElasticHenkel elasticHenkel = new ElasticHenkel(mongoHenkel.getMcid(), mongoHenkel.getCommonName(), mongoHenkel.getDescription(), mongoHenkel.getSapCs());
            elasticRepo.save(elasticHenkel);
        }

        return new ResponseEntity<>(elasticRepo.findAll(), HttpStatus.OK);

    }

    public Iterable<ElasticHenkel> getChemicals() {
        return elasticRepo.findAll();
    }

    public Optional<ElasticHenkel> findById(String mcid) {
        return elasticRepo.findById(mcid);
    }


    public ElasticHenkel insertOneChemical(ElasticHenkel henkel) {
        return elasticRepo.save(henkel);
    }


    public ResponseEntity<?> putElastic(ElasticHenkel updateHenkel, String mcid) {
        Optional<ElasticHenkel> elasticHenkel = elasticRepo.findById(mcid);
        elasticHenkel.ifPresent(m -> {
            m.setCommonName(updateHenkel.getCommonName());
            m.setDescription(updateHenkel.getDescription());
            m.setSapCs(updateHenkel.getSapCs());
            elasticRepo.save(m);
        });
        return new ResponseEntity<>("Updated Chemical id " + mcid, HttpStatus.OK);
    }


    public ResponseEntity<?> patchElastic(String mcid, String commonName) {
        ElasticHenkel elasticHenkel = elasticRepo.findById(mcid).get();
        elasticHenkel.setCommonName(commonName);
        return new ResponseEntity<ElasticHenkel>(elasticRepo.save(elasticHenkel), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteElastic(String mcid) {
        ElasticHenkel elasticHenkel = elasticRepo.findById(mcid).get();
        elasticRepo.delete(elasticHenkel);
        return new ResponseEntity<>("Deleted Chemical id " + mcid, HttpStatus.OK);
    }
}
