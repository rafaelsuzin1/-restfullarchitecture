package org.rest.repository;

import java.util.List;

import org.rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
		public List<Cliente> findByNomeCliente(String nomeCliente);
}
