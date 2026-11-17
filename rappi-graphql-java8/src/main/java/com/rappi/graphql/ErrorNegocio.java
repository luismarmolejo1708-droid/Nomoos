package com.rappi.graphql;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.List;

/**
 * Error de validacion de negocio que GraphQL muestra con su mensaje original.
 */
public class ErrorNegocio extends RuntimeException implements GraphQLError {

    /**
     * Crea el error.
     * @param mensaje mensaje que vera el cliente
     */
    public ErrorNegocio(String mensaje) {
        super(mensaje);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
