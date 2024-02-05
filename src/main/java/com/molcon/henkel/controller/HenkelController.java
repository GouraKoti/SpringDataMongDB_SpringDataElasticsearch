package com.molcon.henkel.controller;

import com.molcon.henkel.entity.ElasticHenkel;
import com.molcon.henkel.entity.MongoHenkel;
import com.molcon.henkel.service.HenkelService;
import com.molcon.henkel.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/henkelFullProject")
public class HenkelController {

    @Autowired
    HenkelService service;

    //Parse Xls file
    //Store into MongoDB
    @PostMapping("/uploadExcelIntoMongoDB")
    ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return  service.uploadService(file);
    }


    //Read from Mongodb and index it to ES
    @GetMapping("/getFromMongo")
    public ResponseEntity<?> getFromMongo() {
        return service.readMongo();
    }

    //Access ES through Rest APis
    @GetMapping("/findFromElastic")
    Iterable<ElasticHenkel> findAll(){
        return service.getChemicals();
    }

    @GetMapping("/findFromElastic/{mcid}")
    public Optional<ElasticHenkel> findById(@PathVariable String mcid) {
        return service.findById(mcid);
    }

    @PostMapping("/insertIntoElastic")
    public ElasticHenkel insertChemical(@RequestBody ElasticHenkel henkel){
        return service.insertOneChemical(henkel);
    }

    @PutMapping("/updateIntoElastic/{mcid}")
    public ResponseEntity<?> updateChemical(@RequestBody ElasticHenkel updateHenkel, @PathVariable String mcid){
        return service.putElastic(updateHenkel, mcid);
    }

    @PatchMapping("/updateIntoElastic/{mcid}/{commonName}")
    public ResponseEntity<?> updateNameElastic(@PathVariable String mcid, @PathVariable String commonName){
        return service.patchElastic(mcid, commonName);
    }

    @DeleteMapping("/deleteFromElastic/{mcid}")
    public ResponseEntity<?> updateNameElastic(@PathVariable String mcid){
        return service.deleteElastic(mcid);
    }
}
