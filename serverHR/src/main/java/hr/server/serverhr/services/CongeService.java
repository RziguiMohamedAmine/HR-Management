package hr.server.serverhr.services;

import hr.server.serverhr.entities.Conge;
import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.repositories.CongéRepository;
import hr.server.serverhr.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CongeService implements ICongeService{

    @Autowired
    CongéRepository congéRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Conge DemanderConge(Conge conge) {
        conge.setValidé(false);
        return congéRepository.save(conge);
    }

    @Override
    public boolean approuverConge(Conge conge) {
        Conge conge1 = congéRepository.findById(conge.getId()).orElseThrow(() -> new RuntimeException("Congé not found"));
        Employee employee = employeeRepository.findById(conge1.getEmployee().getIdEmployee()).orElseThrow(() -> new RuntimeException("Employee not found"));
        conge.setEmployee(employee);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(conge1.getDateDebutConge());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
        int year = calendar.get(Calendar.YEAR);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(conge1.getDateFinConge());
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        int month2 = calendar2.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
        int year2 = calendar2.get(Calendar.YEAR);

        LocalDate date1 = LocalDate.of(year, month, day); // Exemple : 20 novembre 2024
        LocalDate date2 = LocalDate.of(year2, month2, day2); // Exemple : 27 novembre 2024
        System.out.println(date1);
        System.out.println(date2);
        long differenceEnJours = ChronoUnit.DAYS.between(date1, date2);
        System.out.println(differenceEnJours);
        employee.setSoldecongé(employee.getSoldecongé() - differenceEnJours);
        conge1.setValidé(true);
        congéRepository.save(conge1);
        employeeRepository.save(employee);
       return conge1.isValidé();
    }

    @Override
    public boolean refuserConge(Conge conge) {
        Conge conge1 = congéRepository.findById(conge.getId()).orElseThrow(() -> new RuntimeException("Congé not found"));
        Employee employee = employeeRepository.findById(conge1.getEmployee().getIdEmployee()).orElseThrow(() -> new RuntimeException("Employee not found"));
        conge.setEmployee(employee);
        conge1.setValidé(false);
        congéRepository.save(conge1);
        return false;
    }



    @Override
    public List<Conge> RetrieveHistoriqueConge(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return congéRepository.findAllByEmployee(employee);
    }

    @Override
    public void DeleteConge(int id) {
        congéRepository.deleteById(id);
    }
}
