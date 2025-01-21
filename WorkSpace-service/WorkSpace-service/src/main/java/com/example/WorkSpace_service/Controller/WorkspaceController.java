package com.example.WorkSpace_service.Controller;

import com.example.WorkSpace_service.Model.Workspace;
import com.example.WorkSpace_service.Service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("workspaces")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    // Create a new workspace
    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        return workspaceService.createWorkspace(workspace);
    }

    // Get a workspace by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id) {
        return workspaceService.getWorkspaceById(id);
    }

    // Get all workspaces
    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    // Update a workspace
    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable Long id, @RequestBody Workspace workspace) {
        return workspaceService.updateWorkspace(id, workspace);
    }

    // Delete a workspace by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long id) {
        return workspaceService.deleteWorkspace(id);
    }

    // Allocate a workspace
    @PostMapping("/{id}/allocate")
    public ResponseEntity<Workspace> allocateWorkspace(@PathVariable Long id) {
        return workspaceService.allocateWorkspace(id);
    }

    // Release a workspace
    @PostMapping("/{id}/release")
    public ResponseEntity<Void> releaseWorkspace(@PathVariable Long id) {
        return workspaceService.releaseWorkspace(id);
    }
}
