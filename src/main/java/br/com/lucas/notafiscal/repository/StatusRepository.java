package br.com.lucas.notafiscal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucas.notafiscal.domain.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {

}
