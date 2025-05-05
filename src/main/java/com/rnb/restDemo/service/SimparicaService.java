package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Simparica;

import java.util.List;

public interface SimparicaService {
    String getNextDayForSimparica(int year, int month, int day, String name);

    List<Simparica> getSimparicas();

    String getNextDayForSimparica(Simparica simparica);
}
