package com.ctlfab.estatehandle.repository;

import com.ctlfab.estatehandle.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
