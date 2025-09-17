package simulacion;

import java.util.Random;

public class simulacionHilos {

    static class ProcesoSuma {
        String nombre;
        int limite;   // límite superior
        int paso;     // incremento (random)
        int actual;   // valor actual

        public ProcesoSuma(String nombre, int inicio, int limite, int paso) {
            this.nombre = nombre;
            this.actual = inicio;
            this.limite = limite;
            this.paso = Math.max(1, paso); // asegurar que nunca sea 0
        }

        public boolean ejecutarCiclo() {
            if (actual + paso <= limite) {
                int siguiente = actual + paso;
                System.out.println(nombre + ": " + actual + " + " + paso + " = " + siguiente);
                actual = siguiente;
                try {
                    Thread.sleep(200); // simula tiempo de CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return true;
            } else {
                System.out.println(nombre + " terminado.");
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();

        int paso1 = rnd.nextInt(10) + 1; // random entre 1 y 10
        int paso2 = rnd.nextInt(10) + 1;

        System.out.println("Random para Hilo1 = " + paso1);
        System.out.println("Random para Hilo2 = " + paso2);
        System.out.println("=== Simulación iniciada ===");

        ProcesoSuma h1 = new ProcesoSuma("Hilo1", paso1, 100, paso1);
        ProcesoSuma h2 = new ProcesoSuma("Hilo2", 100, 150, paso2);

        boolean ejecutando1 = true;
        boolean ejecutando2 = true;
        int numProceso = 1;

        while (ejecutando1 || ejecutando2) {
            System.out.println("------ Proceso " + numProceso + " ------");

            if (ejecutando1) ejecutando1 = h1.ejecutarCiclo();
            if (ejecutando2) ejecutando2 = h2.ejecutarCiclo();

            numProceso++;
        }

        System.out.println("=== Todos los procesos completados ===");
    }
}
