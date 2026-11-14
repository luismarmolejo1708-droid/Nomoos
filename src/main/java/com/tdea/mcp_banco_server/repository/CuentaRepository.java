package com.tdea.mcp_banco_server.repository;

import com.tdea.mcp_banco_server.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}
