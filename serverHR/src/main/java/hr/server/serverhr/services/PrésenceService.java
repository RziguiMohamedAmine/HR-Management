package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.PrésenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class PrésenceService implements IPrésenceService{

    @Autowired
    PrésenceRepository présenceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Présence ajouterPrésence(Présence présence) {
        return présenceRepository.save(présence);
    }

    @Override
    public Présence updatePrésence(Présence présence) {
        return présenceRepository.save(présence);
    }

    @Override
    public List<Présence> retrieveAllPrésence(int id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        return présenceRepository.findAllByEmployee(employee);
    }

    @Override
    public List<Présence> getAllDayPresence(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            System.out.println(date+"\t"+date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return présenceRepository.findAllByDay(date1);
    }

    @Override
    public void DeletePresence(int id) {
        présenceRepository.deleteById(id);
    }
}
