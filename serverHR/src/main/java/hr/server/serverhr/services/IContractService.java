package hr.server.serverhr.services;

import hr.server.serverhr.entities.Contract;

import java.util.List;

public interface IContractService {
    Contract addContract(int employeeId, Contract contract);

    List<Contract> getActiveContracts();

    void deleteExpiredContracts();
}
