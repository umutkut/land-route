package com.example.landroad.controller;

import com.example.landroad.application.RoadCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@DependsOn(value = "countryLoaderService")
@RequiredArgsConstructor
public class LandRoadController {

    private final RoadCalculatorService roadCalculatorService;

    @GetMapping("/routing/{origin}/{destination}")
    ResponseEntity path(@PathVariable String origin, @PathVariable String destination) {
        try {
            return ResponseEntity.of(Optional.of(roadCalculatorService.calculateRoad(origin, destination)));
        } catch (Exception e){
            return new ResponseEntity<ResponseBody>(HttpStatus.BAD_REQUEST);
        }
    }

}
