package org.rest.repository;

import java.util.List;

import org.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
		public List<Client> findByNameClient(String nameClient);
}
