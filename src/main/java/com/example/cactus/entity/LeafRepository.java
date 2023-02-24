package com.example.cactus.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeafRepository extends JpaRepository<Leaf, Long> {

    List<Leaf> findByStemIdAndStatus(Long stemId, Status status);
}
