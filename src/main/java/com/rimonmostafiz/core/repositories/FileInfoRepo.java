package com.rimonmostafiz.core.repositories;

import com.rimonmostafiz.core.model.FileInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rimon Mostafiz
 */
@Repository
public interface FileInfoRepo extends PagingAndSortingRepository<FileInfo, Long> {
    FileInfo findByFileName(String fileName);

    List<FileInfo> findByActive(Boolean active);
}
