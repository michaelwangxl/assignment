package com.michael.assignment.repository;

import com.michael.assignment.entity.DJIndex;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author michaelwang on 2021-08-18
 */
@Component
public interface DJIndexRepository extends Repository<DJIndex,Integer> {
    List<DJIndex> findDJIndicesByStock(String stock);
    List<DJIndex> findAll();
    void save(DJIndex djIndex);
}
