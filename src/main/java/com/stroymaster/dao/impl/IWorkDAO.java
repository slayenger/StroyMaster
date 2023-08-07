package com.stroymaster.dao.impl;

import com.stroymaster.dao.api.WorkDAO;
import com.stroymaster.entity.Work;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.repository.WorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class IWorkDAO implements WorkDAO {

    private final WorkRepository repository;

    @Override
    public List<Work> getAllWorks()
    {
        return repository.findAll();
    }

    @Override
    public void addNewWork(Work work)
    {
        Work newWork = repository.save(work);
    }

    @Override
    public Work getWork(UUID workId)
    {
        if (repository.existsById(workId))
        {
            return repository.getReferenceById(workId);
        }
        else
        {
            throw new NotFoundException("Work with ID " + workId + " not found!");
        }
    }

    private void copyRequestProperties(Work source, Work target)
    {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setImageUrl(source.getImageUrl());
    }

    @Override
    public void updateWork(UUID workId, Work updatedWork)
    {
        if (repository.existsById(workId))
        {
            Work existingWork = repository.getReferenceById(workId);
            copyRequestProperties(updatedWork, existingWork);
            repository.save(existingWork);
        }
        else
        {
            throw new NotFoundException("Work with ID " + workId + " not found!");
        }
    }

    @Override
    public void deleteWork(UUID workId)
    {
        repository.deleteById(workId);
    }
}
