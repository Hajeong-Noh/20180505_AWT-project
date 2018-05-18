package com.polimi.awt.repository;

import com.polimi.awt.model.Peak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeakRepository extends JpaRepository<Peak, Long> {

}
