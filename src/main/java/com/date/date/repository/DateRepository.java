package com.date.date.repository;

import com.date.date.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Integer> {
}
