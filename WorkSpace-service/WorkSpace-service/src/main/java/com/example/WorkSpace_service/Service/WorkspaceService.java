package com.example.WorkSpace_service.Service;


import com.example.WorkSpace_service.Config.WorkspaceMessageProducer;
import com.example.WorkSpace_service.Model.Workspace;
import com.example.WorkSpace_service.Repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private WorkspaceMessageProducer messageProducer;

    public ResponseEntity<Workspace> createWorkspace(Workspace workspace) {
        Workspace createdWorkspace = workspaceRepository.save(workspace);
        return new ResponseEntity<>(createdWorkspace, HttpStatus.CREATED);
    }

    public ResponseEntity<Workspace> getWorkspaceById(Long id) {
        Optional<Workspace> workspace = workspaceRepository.findById(id);
        if (workspace.isPresent()) {
            return new ResponseEntity<>(workspace.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    public ResponseEntity<Workspace> updateWorkspace(Long id, Workspace updatedWorkspace) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            workspace.setName(updatedWorkspace.getName());
            workspace.setAvailable(updatedWorkspace.isAvailable());
            Workspace savedWorkspace = workspaceRepository.save(workspace);
            return new ResponseEntity<>(savedWorkspace, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteWorkspace(Long id) {
        if (workspaceRepository.existsById(id)) {
            workspaceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Workspace> allocateWorkspace(Long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            if (!workspace.isAvailable()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            workspace.setAvailable(false);
            Workspace updatedWorkspace = workspaceRepository.save(workspace);
            messageProducer.sendMessage("workspace-queue","WorkSpace number"+ workspace.getName() +"is successfully allocated");
            return new ResponseEntity<>(updatedWorkspace, HttpStatus.OK);
        } else {
            messageProducer.sendMessage("workspace-queue","Sorry,WorkSpace is not available");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> releaseWorkspace(Long id) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findById(id);
        if (optionalWorkspace.isPresent()) {
            Workspace workspace = optionalWorkspace.get();
            workspace.setAvailable(true);
            workspaceRepository.save(workspace);
            messageProducer.sendMessage("workspace-queue","WorkSpace number"+ workspace.getName() +"is successfully released");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
