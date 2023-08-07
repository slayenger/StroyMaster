package com.stroymaster.service.api;

import com.stroymaster.entity.Work;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface WorkService {

    public ResponseEntity<?> getAllWorks();

    public ResponseEntity<?> addNewWork(Work work);

    public ResponseEntity<?> getWork(UUID workId);

    public ResponseEntity<?> updateWork(UUID workId, Work updatedWork);

    public ResponseEntity<?> deleteWork(UUID workId);
}
