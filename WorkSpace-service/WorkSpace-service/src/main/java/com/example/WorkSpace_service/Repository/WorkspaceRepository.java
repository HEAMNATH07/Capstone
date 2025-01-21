package com.example.WorkSpace_service.Repository;

import com.example.WorkSpace_service.Model.Workspace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, Long> {
}