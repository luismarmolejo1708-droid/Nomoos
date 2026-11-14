package com.tdea.mcp_banco_server.service;

import com.tdea.mcp_banco_server.model.Cuenta;
import com.tdea.mcp_banco_server.repository.CuentaRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {

    private final CuentaRepository cuentaRepository;

    public BancoService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        // Precargar cuentas de prueba al iniciar
        cuentaRepository.save(new Cuenta("1001", "Ana Pérez", 500000.0));
        cuentaRepository.save(new Cuenta("1002", "Carlos Gómez", 1200000.0));
    }

    @Tool(name = "consultarSaldo", description = "Permite consultar el saldo actual de una cuenta bancaria dado su número de cuenta.")
    public String consultarSaldo(String numeroCuenta) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(numeroCuenta);
        if (cuenta.isPresent()) {
            return "El saldo de la cuenta " + numeroCuenta + " (Titular: " + cuenta.get().getTitular() + ") es: $" + cuenta.get().getSaldo();
        }
        return "Error: La cuenta número " + numeroCuenta + " no fue encontrada.";
    }

    @Tool(name = "transferirDinero", description = "Realiza una transferencia de dinero entre dos cuentas. Requiere cuentaOrigen, cuentaDestino y monto.")
    public String transferirDinero(String cuentaOrigen, String cuentaDestino, double monto) {
        if (monto <= 0) {
            return "Error: El monto a transferir debe ser mayor a 0.";
        }

        Optional<Cuenta> origenOpt = cuentaRepository.findById(cuentaOrigen);
        Optional<Cuenta> destinoOpt = cuentaRepository.findById(cuentaDestino);

        if (origenOpt.isEmpty() || destinoOpt.isEmpty()) {
            return "Error: Una de las cuentas especificadas no existe.";
        }

        Cuenta origen = origenOpt.get();
        if (origen.getSaldo() < monto) {
            return "Error: Fondos insuficientes. Saldo disponible: $" + origen.getSaldo();
        }

        Cuenta destino = destinoOpt.get();

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        cuentaRepository.save(origen);
        cuentaRepository.save(destino);

        return "Transferencia exitosa de $" + monto + " desde " + cuentaOrigen + " a " + cuentaDestino + ".";
    }

    @Tool(name = "consignarDinero", description = "Permite realizar un depósito o consignación de dinero a una cuenta bancaria especificada.")
    public String consignarDinero(String numeroCuenta, double monto) {
        if (monto <= 0) {
            return "Error: El monto a consignar debe ser mayor a 0.";
        }

        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numeroCuenta);
        if (cuentaOpt.isEmpty()) {
            return "Error: La cuenta número " + numeroCuenta + " no existe.";
        }

        Cuenta cuenta = cuentaOpt.get();
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);

        return "Consignación exitosa de $" + monto + " a la cuenta " + numeroCuenta + ". Nuevo saldo: $" + cuenta.getSaldo();
    }

    @Tool(name = "listarCuentas", description = "Obtiene la lista de todas las cuentas bancarias registradas en el sistema.")
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }
}
