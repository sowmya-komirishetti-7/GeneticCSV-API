package com.example.csvfile.controller;

import net.datafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/csv")
public class CSVcontroller {

    private final Faker faker = new Faker(new Locale("en-IN"));

//    @GetMapping("/generate")
//    public ResponseEntity<Resource> generateCsv(@RequestParam List<String> headers) throws IOException {
//        StringWriter writer = new StringWriter();
//        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])));
//
//        for (int i=0; i < 1000; i++) {
//            List<String> row = new ArrayList<String>();
//            for (String header : headers) {
//                row.add(generateFakerData(header));
//            }
//            csvPrinter.printRecord(row);
//        }
//        csvPrinter.flush();
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(writer.toString().getBytes()));
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv")
//                .contentType(MediaType.parseMediaType("text/csv"))
//                .body((Resource) resource);
//    }
//
//    private String generateFakerData(String header) {
//
//        return switch (header.toLowerCase()) {
//
//            case "birthday" -> faker.date().birthday().toString();
//            case "full_name" -> faker.name().fullName();
//            case "firstname" -> faker.name().firstName();
//            case "lastname" -> faker.name().lastName();
//            case "email" -> faker.internet().emailAddress();
//            case "phone" -> faker.phoneNumber().phoneNumber();
//            case "address" -> faker.address().fullAddress();
//            case "city" -> faker.address().city();
//            case "state" -> faker.address().state();
//            case "zip" -> faker.address().zipCode();
//            case "country" -> faker.address().country();
//            case "company" -> faker.company().name();
//            case "movie" -> faker.movie().toString();
//            case "job" -> faker.job().title();
//
//            default -> "N/A";
//        };
//    }



    @GetMapping("/generate")
    public ResponseEntity<Resource> generateCsv(@RequestParam(name = "headers", required = false) List<String> headers) throws IOException {
        System.out.println("Received headers: " + headers); // Debugging output

        if (headers == null || headers.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        StringWriter writer = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])));

        for (int i = 0; i < 10; i++) {
            List<String> row = new ArrayList<>();
            for (String header : headers) {
                row.add(generateFakerData(header).trim()); // Trim spaces
            }
            csvPrinter.printRecord(row);
        }

        csvPrinter.flush();
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(writer.toString().getBytes()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }


    private String generateFakerData(String header) {


        Date startDate = java.sql.Date.valueOf(LocalDate.of(2023, 1, 1));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2025, 1, 1));

        LocalDate startLocalDate = LocalDate.of(2023, 1, 1);
        LocalDate endLocalDate = LocalDate.of(2025, 1, 1);


        return switch (header.toLowerCase()) {
            case "full_name" -> faker.name().fullName();
            case "firstname" -> faker.name().firstName();
            case "lastname" -> faker.name().lastName();
            case "email" -> faker.internet().emailAddress();
            case "phone" -> faker.phoneNumber().phoneNumber();
            case "address" -> faker.address().fullAddress();
            case "city" -> faker.address().city();
            case "state" -> faker.address().state();
            case "zip" -> faker.address().zipCode();
            case "country" -> faker.address().country();
            case "company" -> faker.company().name();
            case "movie" -> faker.movie().name();
            case "job" -> faker.job().title();
            case "birthday" -> faker.timeAndDate().birthday().toString();
            case "female firstname" -> faker.name().femaleFirstName();
            case "male firstname" -> faker.name().malefirstName();
            case "middle name" -> faker.name().nameWithMiddle();
            case "gender" -> faker.gender().toString();
            case "marketing" -> faker.marketing().toString();
            case "street name" -> faker.address().streetName();
            case "company industry" -> faker.company().industry();
//            case "future date" -> faker.date().future(10, TimeUnit.MINUTES).toString();
            case "between date" -> faker.timeAndDate()
                    .between(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                            endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                    .toString();
            case "animal" -> faker.animal().name();
            case "color" -> faker.color().name();
            case "coffee" -> faker.coffee().toString();
            case "book" -> faker.book().title();
            case "author" -> faker.book().author();
            case "camera" -> faker.camera().brand();
            case "computer" -> faker.computer().brand();
            case "beer" -> faker.beer().name();
            case "chess" -> faker.chess().title();
            case "dog" -> faker.dog().name();
            case "horse" -> faker.horse().name();
            case "car" -> faker.brand().car();
            case "cat" -> faker.cat().name();
            case "blood type" -> faker.bloodtype().toString();
            case "disease" -> faker.disease().anyDisease();
            case "dessert" -> faker.dessert().toString();
            case "domain" -> faker.domain().toString();
            case "finance" -> faker.finance().toString();
            case "food" -> faker.food().dish();
            case "driving license" -> faker.drivingLicense().toString();
            case "football coaches" -> faker.football().coaches();
            case "football players" -> faker.football().players();
            case "house furniture" -> faker.house().furniture();
            case "passport" -> faker.passport().valid();
            case "weather" -> faker.weather().toString();
            case "hobbit" -> faker.hobbit().toString();
            case "image" -> faker.image().toString();
            case "size" -> faker.size().toString();
            case "cricket" -> faker.cricket().toString();
            case "university"-> faker.university().toString();
            case "vehicle" -> faker.vehicle().toString();
            case "vehicle color" -> faker.vehicle().color();
            case "vehicle doors" -> faker.vehicle().doors();
            case "vehicle engine" -> faker.vehicle().engine();
            case "language code" -> faker.languageCode().toString();
            case "location" -> faker.location().toString();
            case "music" -> faker.music().toString();
            case "planet" -> faker.planet().toString();
            default -> "N/A";
        };
    }
}
