## Correciones del Sistema
### Experiencia de usuario (GUI)
1. El correo de recuperación requiere más contenido relevante. 
2. ~~Bugs en la interfaz en la pantalla de rol-opciones.~~ 
3. ~~En el control de cuentas contables (select2) dentro de la pantalla de asiento contable separar los códigos de cuentas por guiones (-) en vez de pleca (/).~~

### Arquitectura de Software
4. ~~Las prórrogas no deben asignarse en las fechas de los PC y PF. Sino que la opción de abrir o cerrar un PC y PF.~~
5. El rol de Administrador debe poder cambiar de empresa una vez autenticado
6. ~~Filtrar en la tabla de asientos contables que se visualicen los asientos registrados únicamente en el periodo contable que se está trabajando.~~
7. Cuando se registra un asiento contable no se sugiere el tipo de cambio según la fecha del asiento contable. 
8. Se requiere poder visualizar la lista de comprobantes de diario y comprobantes de ck por rangos de fecha, número de comprobante o rango de número de comprobantes. 
9. ~~Los asientos contables carecen de número de documento.~~
10. ~~El sistema permite guardar un asiento contable sin su detalle.~~
11. ~~El sistema permite guardar un asiento contable cuando el periodo contable está cerrado.~~
12. ~~El sistema no permite abrir un periodo contable cerrado.~~
13. Si un usuario cierra los PC y PF, estos se cierran para todas las empresas, separar la funcionalidad para los PC y PF según empresa. 

### Diseño de reportes personalizados
14. Revisar que el reporte de CD cumpla con todos los aspectos del formato sugerido a excepción de la columna "parcial".
Diseñar el reporte de comprobante de CK. 
15. No se presentaron los reportes de Balance General y Estado de Resultados. 
