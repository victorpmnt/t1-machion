import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class TesteOrdenacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Escolha o tamanho do vetor: ");
        int tamanhoVetor = scanner.nextInt();

        System.out.print("Quantas execuções deseja realizar? ");
        int repeticoes = scanner.nextInt();

        for (int execucao = 1; execucao <= repeticoes; execucao++) {
            System.out.println("\n--- Execução " + execucao + " ---");

            // Cria um novo vetor a cada execução (para evitar ordenação prévia)
            NossoVetor v = new NossoVetor(tamanhoVetor);
            v.preencheVetor();
            int elementoBusca = v.getElemento(rand.nextInt(tamanhoVetor));

            // Busca linear (antes de ordenar)
            v.resetContadores();
            try {
                v.buscaLinear(elementoBusca);
                System.out.println("Busca linear: " + v.getComparacoes() + " comparações");
            } catch (ElementoNaoEncontradoException e) {
                System.out.println("Elemento não encontrado (busca linear)");
            }


            // bubbleSort
            // long iniBubble = Calendar.getInstance().getTimeInMillis();
            // v.bubbleSort();
            // long fimBubble = Calendar.getInstance().getTimeInMillis();
            // System.out.println("\nbubblesort demorou " + (fimBubble-iniBubble) + " ms | " + v.getTrocas() + " trocas");


            // InsertionSort (ordenar para depois fazer busca binária)
            // long inicioInsertion = Calendar.getInstance().getTimeInMillis();
            // v.insertionSort();
            // long fimInsertion = Calendar.getInstance().getTimeInMillis();
            // System.out.println("\nInsertionSort: " + (fimInsertion - inicioInsertion) + " ms | " + v.getTrocas() + " trocas");

            // // selectionSort
            // // System.out.println( "vetor original:\n "+ v);
            long iniInser = Calendar.getInstance().getTimeInMillis();
            v.selectionSort();
            long fimInser = Calendar.getInstance().getTimeInMillis();
            System.out.println("\nselectionSort demorou " + (fimInser-iniInser) + " ms | " + v.getTrocas() + " trocas");


            // Busca binária (após ordenação)
            v.resetContadores();
            try {
                v.buscaBinaria(elementoBusca);
                System.out.println("\nBusca binária: " + v.getComparacoes() + " comparações");
            } catch (ElementoNaoEncontradoException e) {
                System.out.println("Elemento não encontrado (busca binária)");
            }
        }

        scanner.close();
    }
}