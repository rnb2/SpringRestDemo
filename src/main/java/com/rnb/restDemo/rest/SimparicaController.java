package com.rnb.restDemo.rest;

import com.rnb.restDemo.service.SimparicaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rnb/api/simparica")
public class SimparicaController {
    private final SimparicaService simparicaService;

    public SimparicaController(SimparicaService simparicaService) {
        this.simparicaService = simparicaService;
    }

    @GetMapping("/{year:\\d+}_{month:\\d+}_{day:\\d+}")
    public String getNextDayForSimparica(@PathVariable("year") int year,
                                         @PathVariable("month")  int month,
                                         @PathVariable("day") int day) {
        return simparicaService.getNextDayForSimparica(year, month, day);
    }
}
