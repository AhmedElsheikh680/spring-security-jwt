package com.spring.security.jwt.repo;

import com.spring.security.jwt.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepo extends JpaRepository<Authorities, Long> {
}
