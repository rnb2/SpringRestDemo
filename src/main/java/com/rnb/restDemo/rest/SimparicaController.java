package com.rnb.restDemo.rest;

import com.rnb.restDemo.entity.Simparica;
import com.rnb.restDemo.service.SimparicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return simparicaService.getNextDayForSimparica(year, month, day, "Simparica 10-20");
    }

    @PostMapping("/")
    public String getNextDayForSimparica(@RequestBody Simparica simparica) {
        return simparicaService.getNextDayForSimparica(simparica);
    }

    @GetMapping("/")
    public List<Simparica> getSimparicas() {
        return simparicaService.getSimparicas();
    }
}
