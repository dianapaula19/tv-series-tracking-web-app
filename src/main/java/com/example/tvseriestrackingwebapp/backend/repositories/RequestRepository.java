package com.example.tvseriestrackingwebapp.backend.repositories;


import com.example.tvseriestrackingwebapp.backend.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
