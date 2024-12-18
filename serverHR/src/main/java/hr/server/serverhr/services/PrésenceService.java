package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.PrésenceRepository;
import hr.server.serverhr.repositories.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
    public Présence addArrival(int employeeId, Date date, LocalDateTime arrivalTime) {
        // Fetch the presence record for the employee on the given date
        Présence présence = présenceRepository.findByEmployeeIdEmployeeAndDay(employeeId, date)
                .orElseThrow(() -> new RuntimeException("Presence record not found for employee ID " + employeeId + " on " + date));

        // Set the arrival time
        présence.setArrivalTime(arrivalTime);

        // Save and return the updated presence record
        return présenceRepository.save(présence);
    }

    @Override
    public Présence addDeparture(int employeeId, Date date, LocalDateTime departureTime) {
        // Fetch the presence record
        Présence présence = présenceRepository.findByEmployeeIdEmployeeAndDay(employeeId, date)
                .orElseThrow(() -> new RuntimeException("Presence record not found for employee ID " + employeeId + " on " + date));

        // Set the arrival time
        présence.setDepartureTime(departureTime);

        // Calculate the total time worked (if arrival time exists)
        if (présence.getArrivalTime() != null) {
            Duration duration = Duration.between(présence.getArrivalTime(), departureTime);
            duration = duration.minusHours(1); // Assuming a 1-hour break
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            présence.setTotalTimeWorked(String.format("%02d:%02d", hours, minutes));
        }

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

    // Run daily at 6:00 AM
    @Scheduled(cron = "0 0 10 * * ?")
    public void initializeDailyAttendance() {
        Date today = new Date();

        // Fetch all employees
        List<Employee> employees = employeeRepository.findAll();

        // Add attendance record for each employee
        for (Employee employee : employees) {
            // Check if today's record already exists
            boolean alreadyExists = présenceRepository.existsByEmployeeIdEmployeeAndDay(employee.getIdEmployee(), today);
            if (!alreadyExists) {
                Présence attendance = new Présence();
                attendance.setEmployee(employee);
                attendance.setDay(today); // Assuming Attendance has a `date` field
                attendance.setArrivalTime(null); // No arrival time (default)
                attendance.setDepartureTime(null); // No departure time (default)
                attendance.setJustifiedAbsence(false); // Default: unjustified absence

                présenceRepository.save(attendance);
            }
        }
    }

    @Override
    public Présence justifyAbsence(int présenceId, String justification) {
        Présence présence = présenceRepository.findById(présenceId)
                .orElseThrow(() -> new RuntimeException("Présence not found"));

        if (Boolean.TRUE.equals(présence.getAbsent())) {
            présence.setJustification(justification);
            return présenceRepository.save(présence);
        } else {
            throw new IllegalStateException("Cannot justify a presence that is not marked as absent");
        }
    }

    @Override
    public List<Présence> getAllPrésence() {
        return présenceRepository.findAll();
    }




}
