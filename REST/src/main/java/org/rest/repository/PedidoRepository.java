package org.rest.repository;

import java.util.List;

import org.rest.model.Cliente;
import org.rest.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	public List<Pedido> findByCliente(Cliente cliente);
}
