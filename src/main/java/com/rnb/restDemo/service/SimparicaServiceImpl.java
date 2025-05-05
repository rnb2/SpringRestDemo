package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Simparica;
import com.rnb.restDemo.repository.SimparicaRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SimparicaServiceImpl implements SimparicaService {
    private static final int PERIOD = 28;
    public static final String DD_MM_YYYY = "dd.MM.yyyy";

    private final SimparicaRepository repository;

    public SimparicaServiceImpl(SimparicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getNextDayForSimparica(int year, int month, int day, String name) {
        LocalDate from = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        Period period = Period.between(from, now);
        LocalDate needToGive = from.plusDays(PERIOD);

        String needToGiveFormatted = needToGive.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
        String numberOfDays = period.getDays() > 0
                ? ", number of days: " + period.getDays()
                : "";
        Simparica simparica = new Simparica();
        simparica.setName(name);
        simparica.setPeriod(PERIOD);
        simparica.setDateFrom(Date.valueOf(from));
        simparica.setDateNext(Date.valueOf(needToGive));
        repository.save(simparica);

        return "Simparica was given: " + from + numberOfDays + "\nNext Simparica reception: " + needToGiveFormatted;
    }

    @Override
    public List<Simparica> getSimparicas() {
        return repository.findAll();
    }

    @Override
    public String getNextDayForSimparica(Simparica simparica) {
        LocalDate from = LocalDate.ofInstant(simparica.getDateFrom().toInstant(), ZoneId.systemDefault());
        LocalDate now = LocalDate.now();
        Period period = Period.between(from, now);
        LocalDate needToGive = simparica.getPeriod() <=0
                ? from.plusDays(PERIOD)
                : from.plusDays(simparica.getPeriod());

        save(simparica, needToGive);

        String needToGiveFormatted = needToGive.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
        String numberOfDays = period.getDays() > 0
                ? ", number of days: " + period.getDays()
                : "";
        return simparica.getName() + " was given: from" + numberOfDays + "\nNext " + simparica.getName() + " reception: " + needToGiveFormatted;
    }

    private void save(Simparica simparica, LocalDate needToGive) {
        Simparica simparicaNew = new Simparica();
        simparicaNew.setName(simparica.getName());
        simparicaNew.setPeriod(simparica.getPeriod());
        simparicaNew.setDateFrom(simparica.getDateFrom());
        simparicaNew.setDateNext(Date.valueOf(needToGive));
        repository.save(simparicaNew);
    }
}
