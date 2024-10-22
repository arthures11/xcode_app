package com.bryja.nbp_api.repository;

import com.bryja.nbp_api.classes.requestEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface requestEntryRepository extends JpaRepository<requestEntry, Long> {

    //w przyszłości niestandardowe zapytania sql w razie potrzeby
}
