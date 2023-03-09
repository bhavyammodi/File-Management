package com.folderManager.Controller;

import com.folderManager.model.Node;
import com.folderManager.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MyController {

    final FileService fileService;
    // public final FileRepo fileRepo;

    public MyController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/")
    public ResponseEntity<Node> addFile(@RequestBody Node node) {
        Node save = this.fileService.saveFiles(node);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/")
    public List<Node> getFile() {
        return this.fileService.getAllFiles();
    }

    @GetMapping("/{pid}")
    public ResponseEntity<List<Node>> getFileByParent(@PathVariable int pid) {
        System.out.println("YES" + pid);
        return ResponseEntity.ok(this.fileService.findNodesByParent(pid));
    }

    @PutMapping("/")
    public void update(@RequestBody Node node) {
        this.fileService.saveFiles(node);
    }

    @DeleteMapping("/hardDelete/{id}")
    public void deleteById(@PathVariable int id) {
        this.fileService.deleteAllChild(id, false);
    }

    @DeleteMapping("/softDelete/{id}")
    public void softDeleteById(@PathVariable int id) {
        this.fileService.deleteAllChild(id, true);
    }
}
