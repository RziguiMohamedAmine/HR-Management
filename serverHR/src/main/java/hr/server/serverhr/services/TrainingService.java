package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Training;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainingService implements ITrainingService{

    @Autowired
    private TrainingRepository trainingClassRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Training addTrainingClass(Training trainingClass) {
        return trainingClassRepository.save(trainingClass);
    }

    @Override
    public Training assignEmployeesToTraining(Long trainingId, List<Integer> employeeIds) {
        Training trainingClass = trainingClassRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training class not found"));

        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        trainingClass.getAttendees().addAll(employees);

        return trainingClassRepository.save(trainingClass);
    }

    @Override
    public Training getTrainingClassById(Long id) {
        return trainingClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training class not found"));
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingClassRepository.findAll();
    }

    @Override
    public Training getTrainingClassByName(String name) {
        return trainingClassRepository.findTrainingByName(name)
                .orElseThrow(() -> new RuntimeException("Training class not found"));
    }
    @Override
    public List<Employee> getEmployeesForTrainingClass(Long trainingClassId) {
        Training trainingClass = trainingClassRepository.findById(trainingClassId)
                .orElseThrow(() -> new RuntimeException("Training class not found"));

        return trainingClass.getAttendees();
    }


}
