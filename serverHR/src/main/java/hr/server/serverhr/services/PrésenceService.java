package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.PrésenceRepository;
import hr.server.serverhr.repositories.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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

    @Autowired
    private SalaryRepository salaryRepository;

    private static final double HOURLY_BONUS_RATE = 20.0; // 20$ per extra hour

    @Override
    public Présence ajouterPrésence(Présence présence) {

        Duration duration = Duration.between(présence.getArrivalTime(), présence.getDepartureTime());
        duration = duration.minusHours(1);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        présence.setTotalTimeWorked(String.format("%02d:%02d", hours, minutes));
        System.out.println(présence.getTotalTimeWorked());

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


    public double calculateTotalBonusForEmployee(int employeeId) {
        List<Présence> attendanceRecords = présenceRepository.findByEmployeeIdEmployee(employeeId);

        return attendanceRecords.stream()
                .mapToDouble(attendance ->
                        attendance.calculateBonusForExtraHours(HOURLY_BONUS_RATE))
                .sum();
    }
    @Override
    public void updateSalaryWithExtraHoursBonus(int employeeId) {

        double totalBonus = calculateTotalBonusForEmployee(employeeId);

        Salary salary = salaryRepository.findByEmployeeIdEmployee(employeeId)
                .orElseThrow(() -> new RuntimeException("Salary not found for employee"));

        // Update the bonus in the salary
        salary.setBonuses(salary.getBonuses() + totalBonus);
        salaryRepository.save(salary);

        List<Présence> attendanceRecords = présenceRepository.findByEmployeeIdEmployee(employeeId);
        if (attendanceRecords.isEmpty()) {
            throw new RuntimeException("Attendance records not found for employee");
        }

        attendanceRecords.forEach(attendance -> attendance.setTotalTimeWorked("8:00"));
        présenceRepository.saveAll(attendanceRecords);

    }



}
