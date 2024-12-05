package hr.server.serverhr.controllers;

import hr.server.serverhr.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {
    @Autowired
    private IContractService contractService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void cleanupExpiredContracts() {
        contractService.deleteExpiredContracts();
    }
}
