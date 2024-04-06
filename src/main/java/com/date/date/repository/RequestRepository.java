package com.date.date.repository;

import com.date.date.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findRequestByRequested(int requesterId);
}
