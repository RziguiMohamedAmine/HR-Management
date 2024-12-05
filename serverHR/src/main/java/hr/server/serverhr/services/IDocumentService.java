package hr.server.serverhr.services;

import hr.server.serverhr.entities.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocumentService {
    Document uploadDocument(int employeeId, MultipartFile file) throws Exception;

    List<Document> getDocumentsByEmployeeId(int employeeId);

    void deleteDocument(Long documentId) throws Exception;

    Document downloadDocument(Long documentId) throws Exception;
}
