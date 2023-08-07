package com.stroymaster.dao.api;

import com.stroymaster.entity.Work;

import java.util.List;
import java.util.UUID;

public interface WorkDAO {

    public List<Work> getAllWorks();

    public void addNewWork(Work work);

    public Work getWork(UUID workId);

    public void updateWork(UUID workId, Work updatedWork);

    public void deleteWork(UUID workId);

}
