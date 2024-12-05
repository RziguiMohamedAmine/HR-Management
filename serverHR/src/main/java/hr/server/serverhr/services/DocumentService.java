package hr.server.serverhr.services;

import hr.server.serverhr.entities.Document;
import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DocumentService implements IDocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private IEmployeeService employeeService;



    @Override
    public Document uploadDocument(int employeeId, MultipartFile file) throws Exception {
        Employee employee = employeeService.RetrieveEmployee(employeeId);
        if (employee == null) {
            throw new Exception("Employee not found");
        }

        Document document = new Document();
        document.setEmployee(employee);
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFileData(file.getBytes());
        document.setUploadedDate(new Date());

        return documentRepository.save(document);
    }

    @Override
    public List<Document> getDocumentsByEmployeeId(int employeeId) {
        return documentRepository.findByEmployeeIdEmployee(employeeId);
    }

    @Override
    public void deleteDocument(Long documentId) throws Exception {
        if (!documentRepository.existsById(documentId)) {
            throw new Exception("Document not found");
        }
        documentRepository.deleteById(documentId);
    }

    @Override
    public Document downloadDocument(Long documentId) throws Exception {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new Exception("Document not found"));
    }
}
