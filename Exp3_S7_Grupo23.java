package teatroarraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class TeatroArrayList {

    private static ArrayList<Venta> ventas = new ArrayList<>();
    private static final double DESCUENTO_JUVENIL = 0.10;
    private static final double DESCUENTO_ADULTO_MAYOR = 0.15;
    private static int totalVentas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int totalEntradasVendidas = 0;
        int totalEntradasDisponibles = 100;

        int asientosVIP = 10;
        int asientosPlateaBaja = 20;
        int asientosPlateaAlta = 30;
        int asientosPalcos = 40;

        System.out.println("## Bienvenido al sistema de ventas del Teatro Moro ##");
        System.out.println();

        System.out.println("- Para comprar entradas, presione: 1");
        System.out.println("- Para salir presione cualquier numero.");
        System.out.println();

        if (scanner.nextInt() == 1) {
            do {
                int ubicacion = 0;

                do {
                    mostrarOpciones(asientosVIP, asientosPlateaBaja, asientosPlateaAlta, asientosPalcos);

                    ubicacion = scanner.nextInt();

                    if (ubicacion >= 1 && ubicacion <= 4) {
                        int cantidadEntradas;
                        switch (ubicacion) {
                            case 1:
                                cantidadEntradas = solicitarCantidadEntradas(scanner, "VIP", asientosVIP);
                                totalEntradasVendidas += cantidadEntradas;
                                int totalVenta1 = calcularTotal(cantidadEntradas, 25000, "VIP", scanner);
                                ventas.add(new Venta(cantidadEntradas, "VIP", 25000, totalVenta1));
                                asientosVIP -= cantidadEntradas;
                                break;
                            case 2:
                                cantidadEntradas = solicitarCantidadEntradas(scanner, "Platea Baja", asientosPlateaBaja);
                                totalEntradasVendidas += cantidadEntradas;
                                int totalVenta2 = calcularTotal(cantidadEntradas, 19000, "Platea Baja", scanner);
                                ventas.add(new Venta(cantidadEntradas, "Platea Baja", 19000, totalVenta2));
                                asientosPlateaBaja -= cantidadEntradas;
                                break;
                            case 3:
                                cantidadEntradas = solicitarCantidadEntradas(scanner, "Platea Alta", asientosPlateaAlta);
                                totalEntradasVendidas += cantidadEntradas;
                                int totalVenta3 = calcularTotal(cantidadEntradas, 11000, "Platea Alta", scanner);
                                ventas.add(new Venta(cantidadEntradas, "Platea Alta", 11000, totalVenta3));
                                asientosPlateaAlta -= cantidadEntradas;
                                break;
                            case 4:
                                cantidadEntradas = solicitarCantidadEntradas(scanner, "Palcos", asientosPalcos);
                                totalEntradasVendidas += cantidadEntradas;
                                int totalVenta4 = calcularTotal(cantidadEntradas, 7200, "Palcos", scanner);
                                ventas.add(new Venta(cantidadEntradas, "Palcos", 7200, totalVenta4));
                                asientosPalcos -= cantidadEntradas;
                                break;
                        }
                    } else {
                        System.out.println("-----------------------------------------");
                        System.out.println("Ubicacion no valida, vuelva a intentarlo.");
                        System.out.println("-----------------------------------------");
                    }
                } while (ubicacion < 1 || ubicacion > 4);

                System.out.println("Desea hacer otra compra?");
                System.out.println("1: Si");
                System.out.println("Cualquier otro numero: No");
                opcion = scanner.nextInt();
            } while (opcion == 1);

            System.out.println("    ");
            System.out.println("===================================");
            System.out.println("         Resumen de ventas         ");
            System.out.println("===================================");
            for (Venta venta : ventas) {
                System.out.println("- Cantidad de entradas: " + venta.getCantidad());
                System.out.println("- Ubicacion: " + venta.getUbicacion());
                System.out.println("- Costo base: $" + venta.getPrecioUnitario());
                System.out.println("- Total de la venta: $" + venta.getTotal());
                System.out.println("    ");
            }
            System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
            System.out.println("Total de entradas disponibles: " + (totalEntradasDisponibles - totalEntradasVendidas));
            System.out.println("===================================");
            System.out.println("    ");
            System.out.println("Gracias por su visita al TEATRO MORO");
            System.out.println("    ");

            scanner.close();
        } else {
            System.out.println("Saliendo del sistema.");
        }
    }

    private static void mostrarOpciones(int asientosVIP, int asientosPlateaBaja, int asientosPlateaAlta, int asientosPalcos) {
        System.out.println("Indique la ubicacion que desea:");
        System.out.println("1: VIP - precio: $25.000 - Asientos disponibles: " + asientosVIP);
        System.out.println("2: Platea Baja - precio: $19.000 - Asientos disponibles: " + asientosPlateaBaja);
        System.out.println("3: Platea Alta - precio: $11.000 - Asientos disponibles: " + asientosPlateaAlta);
        System.out.println("4: Palcos - precio: $7.200 - Asientos disponibles: " + asientosPalcos);
    }

    private static int solicitarCantidadEntradas(Scanner scanner, String ubicacion, int asientosDisponibles) {
        int cantidad;
        do {
            System.out.println("Ha elegido la ubicacion: " + ubicacion);
            System.out.println("Cuantas entradas desea comprar?");
            cantidad = scanner.nextInt();
            if (cantidad <= 0 || cantidad > asientosDisponibles) {
                System.out.println("Cantidad de entradas invalida. Intente nuevamente.");
            }
        } while (cantidad <= 0 || cantidad > asientosDisponibles);
        return cantidad;
    }

    private static int calcularTotal(int cantidad, int precioUnitario, String ubicacion, Scanner scanner) {
        System.out.println("Indique su edad:");
        int edad = scanner.nextInt();
        int descuento;

        // ## BOLETA ##
        System.out.println("    ");
        System.out.println("-------------------------------------------------------");
        System.out.println("                  BOLETA TEATRO MORO                   ");
        System.out.println("-------------------------------------------------------");
        
        if (edad <= 17) {
            System.out.println("- Descuento: 10% por cada entrada.");
            descuento = (int) (precioUnitario * (1 - DESCUENTO_JUVENIL));
        } else if (edad >= 60) {
            System.out.println("- Descuento: 15% por cada entrada.");
            descuento = (int) (precioUnitario * (1 - DESCUENTO_ADULTO_MAYOR));
        } else {
            descuento = precioUnitario;
        }

        int total = cantidad * descuento;
        totalVentas += total;

        System.out.println("- Cantidad de entradas: " + cantidad);
        System.out.println("- Ubicacion: " + ubicacion);
        System.out.println("- Costo base: $" + precioUnitario);
        System.out.println("- Precio de entrada con descuento aplicado: $" + descuento);
        System.out.println("- El total de su compra: $" + total);
        System.out.println("    ");
        System.out.println("-------------------------------------------------------");
        System.out.println("    ");
        return total;
    }
}

class Venta {

    private int cantidad;
    private String ubicacion;
    private int precioUnitario;
    private int total;

    public Venta(int cantidad, String ubicacion, int precioUnitario, int total) {
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public int getTotal() {
        return total;
    }
}
