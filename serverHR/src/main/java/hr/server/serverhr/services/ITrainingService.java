package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Training;

import java.util.List;
import java.util.Optional;

public interface ITrainingService {

    public Training addTrainingClass(Training trainingClass);
    public Training assignEmployeesToTraining(Long trainingId, List<Integer> employeeIds);
    public Training getTrainingClassById(Long id);
    List<Training> getAllTrainings();
    public Training getTrainingClassByName(String name);
    public List<Employee> getEmployeesForTrainingClass(Long trainingClassId);
}
