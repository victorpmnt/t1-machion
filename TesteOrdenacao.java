import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
public class TesteOrdenacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NossoVetor v;
        int t;
        Random rand = new Random();
        System.out.print("escolha o tamanho do vetor, 0 encerra: ");
        t = scanner.nextInt();
        while (t > 0) {
            v = new NossoVetor(t);
            v.preencheVetor();
            int elementoBusca = v.getElemento(rand.nextInt(t));
            // System.out.println( "vetor original:\n "+ v); vetor antes da ordenação

            // busca linear
            v.resetContadores();
            try {
                v.buscaLinear(elementoBusca);
                System.out.println("busca linear: " + v.getComparacoes() + " comparações");
            } catch (ElementoNaoEncontradoException e) {
                System.out.println("Elemento não encontrado na busca linear");
            }

            // bubbleSort
            long ini = Calendar.getInstance().getTimeInMillis();
            v.bubbleSort();
            long fim = Calendar.getInstance().getTimeInMillis();
            // System.out.println(ini);
            // System.out.println(fim);
            System.out.println("bubblesort demorou " + (fim-ini) + " milissegundos");
            System.out.println("bubblesort realizou " + v.getTrocas() + " trocas"); // Exibe trocas
            // System.out.println("\nvetor ordenado:\n" + v); // vetor ordenado
            // System.out.println("\nvetor ordenado:\n" + v);

            // selectionSort
            // v.preencheVetor();
            // // System.out.println( "vetor original:\n "+ v);
            // ini = Calendar.getInstance().getTimeInMillis();
            // v.selectionsort();
            // fim = Calendar.getInstance().getTimeInMillis();
            // v.selectionsort();
            // System.out.println("selectionSort demorou " + (fim-ini) + " milissegundos");

            // // insertionSort
            // v.preencheVetor();
            // // System.out.println( "vetor original:\n "+ v);
            // ini = Calendar.getInstance().getTimeInMillis();
            // v.insertionsort();
            // fim = Calendar.getInstance().getTimeInMillis();
            // v.insertionsort();
            // System.out.println("insertion demorou " + (fim-ini) + " milissegundos");

            // Busca Binária
            v.resetContadores();
            try {
                v.buscaBinaria(elementoBusca);
                System.out.println("busca binaria: " + v.getComparacoes() + " comparações");
            } catch (ElementoNaoEncontradoException e) {
                System.out.println("Elemento não encontrado na busca binária");
            }


            System.out.print("\nescolha o novo tamanho, 0 encerra: ");
            t = scanner.nextInt();
        }
        scanner.close();
    }
}