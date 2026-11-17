# Informe - Parcial Parte B: API GraphQL de pedidos tipo RAPPI

**Estudiante:** ________________  **Fecha:** ________________  **Profesor:** ________________

## 1. Descripcion
Aplicacion Java (Spring Boot 2.6 + GraphQL Java Kickstart + JPA + H2) que expone una API GraphQL
para consultar, registrar y reportar pedidos de domicilios. Incluye un frontend web
(`/index.html`) y la consola GraphiQL (`/graphiql`).

| Requisito | Implementacion |
|---|---|
| 1. Consulta general de pedidos del dia | Query `pedidosDelDia` |
| 2. Consulta por cedula o ID | Queries `pedidosPorCedula`, `pedidoPorId` |
| 3. Ingresar nuevos pedidos | Mutation `crearPedido` (y `cambiarEstado`) |
| 4. Reportes basicos en PDF | Queries `reporteDiarioPdf`, `reportePedidoPdf` (OpenPDF, Base64) |
| Schema con atributos adecuados | `src/main/resources/graphql/schema.graphqls` |
| Frontend integrado | `src/main/resources/static/index.html` |
| JAVADOC | Comentarios en todas las clases (Tools > Generate Javadoc en NetBeans) |

## 2. Como ejecutar en NetBeans
1. Requisitos: JDK 8, NetBeans 8.2 con un Maven externo 3.6+ configurado (Herramientas > Opciones > Java > Maven) e internet.
2. `File > Open Project...` y seleccionar la carpeta `rappi-graphql`.
3. Clic derecho en el proyecto > `Clean and Build` y luego `Run`
   (o ejecutar `RappiGraphqlApplication.java` con `Run File`).
4. Abrir en el navegador:
   - Frontend: http://localhost:8080/index.html
   - GraphiQL: http://localhost:8080/graphiql
   - Consola H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:rappidb`, usuario `sa`, sin clave)

## 3. Ejemplos de Queries y Mutations (para probar en GraphiQL)
```graphql
{ pedidosDelDia { id nombreCliente estado total } }
{ pedidosPorCedula(cedula: "1001234567") { id descripcion estado } }
{ pedidoPorId(id: 1) { id nombreCliente direccionEntrega } }
mutation { crearPedido(input:{cedulaCliente:"1022334455", nombreCliente:"Juan Diaz",
  direccionEntrega:"Calle 1 #2-3", descripcion:"Combo pollo", total:35000}) { id estado fecha } }
{ reporteDiarioPdf }
```

## 4. Evidencias (insertar capturas de pantalla)
Tome las capturas con la app corriendo y peguelas aqui:

**4.1 Proyecto abierto y ejecutandose en NetBeans (ventana Output con "Started RappiGraphqlApplication")**
> [Captura]

**4.2 Frontend: pedidos del dia**
> [Captura]

**4.3 Frontend: busqueda por cedula y por ID**
> [Captura]

**4.4 Frontend: creacion de un nuevo pedido (mensaje de exito y pedido en la tabla)**
> [Captura]

**4.5 GraphiQL: Query y Mutation con su respuesta**
> [Captura]

**4.6 Reporte PDF descargado y abierto**
> [Captura]

## 5. Uso de herramientas de I.A.
Se uso Claude (Anthropic) para generar la estructura inicial del proyecto, el schema GraphQL,
los resolvers, el generador de PDF y el frontend. El codigo fue revisado, ejecutado y probado por el estudiante.

## 6. Conclusiones
(Escribir 3-4 lineas: que aprendio de GraphQL frente a REST, dificultades, mejoras posibles.)
