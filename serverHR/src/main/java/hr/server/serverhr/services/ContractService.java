package hr.server.serverhr.services;

import hr.server.serverhr.entities.Contract;
import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.repositories.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ContractService implements IContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Contract addContract(int employeeId, Contract contract) {
        Employee employee = employeeService.RetrieveEmployee(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        contract.setEmployee(employee);
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getActiveContracts() {
        Date currentDate = new Date();
        return contractRepository.findAll().stream()
                .filter(contract -> contract.getEndDate().after(currentDate))
                .toList();
    }

    @Override
    public void deleteExpiredContracts() {
        Date currentDate = new Date();
        List<Contract> expiredContracts = contractRepository.findByEndDateBefore(currentDate);
        for (Contract contract : expiredContracts) {
            contractRepository.delete(contract);
            employeeService.DeleteEmployee(contract.getEmployee().getIdEmployee());
        }
    }
}
