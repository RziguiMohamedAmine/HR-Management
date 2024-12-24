package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Document;
import hr.server.serverhr.services.IDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/HrMangement/Document")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @PostMapping("/upload/{employeeId}")
    public ResponseEntity<String> uploadDocument(@PathVariable int employeeId,
                                                 @RequestParam("file") MultipartFile file) {
        try {
            documentService.uploadDocument(employeeId, file);
            return ResponseEntity.ok("Document uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Document>> getDocumentsByEmployee(@PathVariable int employeeId) {
        List<Document> documents = documentService.getDocumentsByEmployeeId(employeeId);
        return ResponseEntity.ok(documents);
    }

    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        try {
            documentService.deleteDocument(documentId);
            return ResponseEntity.ok("Document deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long documentId) {
        try {
            Document document = documentService.downloadDocument(documentId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + document.getFileName())
                    .body(document.getFileData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
