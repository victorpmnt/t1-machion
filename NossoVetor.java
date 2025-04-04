import java.util.Random;

public class NossoVetor{
    private int[] vetor;
    private int ocupacao;
    private long trocas;
    private long comparacoes;
     
    public NossoVetor (int tamanho){ 
        vetor = new int[tamanho];
        ocupacao = 0;
    } 
    public NossoVetor() {
        this(10);
    }

    public void carregarAleatorio() {
        Random gerador = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = gerador.nextInt(vetor.length);
        }
        ocupacao = vetor.length;
    }
    // void aumentaVetor() {
    //     int[] temp = new int[vetor.length*2];
    //     for (int i=0; i < ocupacao; i++) 
    //         temp[i] = vetor[i];
    //     vetor = temp;
    // }
    // void reduzVetor() {
    //     int[] temp = new int[vetor.length / 2];
    //     for (int i=0; i < ocupacao; i++)
    //         temp[i] = vetor[i];
    //     vetor = temp;
    // }
    void redimensionaVetor(int novoTamanho) {
        int[] temp = new int[novoTamanho];
        for (int i=0; i < ocupacao; i++) 
            temp[i] = vetor[i];
        vetor = temp;
    }
    public void insere (int i) {
        if (estaCheio()) {
            redimensionaVetor(vetor.length * 2);
        }
        vetor[ocupacao++] = i;
    }

    public void insereOrdanado(int i){
        if(estaCheio()){
            redimensionaVetor(vetor.length * 2);
        }
        int x = 0;
        while (x < ocupacao && vetor[x] < i) {
            x++;
        }
        for(int j = ocupacao; j > x; j--){
            vetor[j] = vetor[j - 1];
        }
        vetor[x] = i;
        ocupacao++;
    }

    public boolean estaCheio() {
        return ocupacao == vetor.length;
    }
    public boolean estaVazio() {
        return ocupacao == 0;
    }
    // public int remove () {
    //     if (!estaVazio()) {
    //         int aux = vetor[--ocupacao];
    //         if (vetor.length >= 6 && ocupacao <= vetor.length/4)
    //             redimensionaVetor(vetor.length / 2);
    //         return aux;
    //     }
    //     return -1;
    // }
    public int remove() {
        if (estaVazio()) {
            throw new VetorVazioException("Vetor vazio, nao ha o que remover");
        }
        int aux = vetor[--ocupacao];
        if (vetor.length >= 6 && ocupacao <= vetor.length / 4) {
            redimensionaVetor(vetor.length / 2);
        }
        return aux;
    }
    public boolean contem (int elemento) {
        for (int i=0; i< ocupacao; i++) 
            if (vetor[i] == elemento)
                return true;
        return false;
    }
    public int indiceDe (int elemento) {
        if (estaVazio())
            throw new VetorVazioException("vetor esta vazio");
        for (int i=0; i<ocupacao; i++)
            if (vetor[i] == elemento)
                return i;
        throw new ElementoNaoEncontradoException(elemento + " nao encontrado");
    }

    public int buscaLinear(int elemento) {
        comparacoes = 0;  // Resetamos o contador
        for (int i = 0; i < ocupacao; i++) {
            comparacoes++;  // Contamos cada comparação
            if (vetor[i] == elemento) {
                return i;
            }
        }
        return -1;
    }

    public int buscaBinaria(int elemento) {
        resetContadores();
        int inicio = 0, fim = ocupacao - 1;
        while (inicio <= fim) {
            comparacoes++;
            int meio = (inicio + fim) / 2;
            if (vetor[meio] == elemento) {
                return meio;
            } else if (vetor[meio] < elemento) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    public long getTrocas() {
        return trocas;
    }
    
    public void resetTrocas() {
        trocas = 0;
    }

    public void resetContadores() {
        trocas = 0;
        comparacoes = 0;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public int getTamanho () {
        return vetor.length;
    }

    public int getElemento(int index) {
        return vetor[index];
    }

    public int[] getVetor() {
        return vetor;
    }


    public void preencheVetor () {
        Random random = new Random();
        for (int i=0; i<vetor.length; i++) {
            vetor[i] = random.nextInt(vetor.length * 10);
        }
        ocupacao = vetor.length;
    }

    public void bubbleSort(){
        resetTrocas(); 
        for (int i = 1; i < vetor.length; i++){
            for(int j=0; j<vetor.length - i;j++){
                if(vetor[j] > vetor[j+1]){
                    int aux = vetor[j];
                    vetor[j] = vetor[j+1];
                    vetor[j+1] = aux;
                    trocas++; 
                }
            }
        }
    }

    public void selectionSort() {
        resetTrocas();
        comparacoes = 0;
    
        for (int i = 0; i < ocupacao - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < ocupacao; ++j) {
                comparacoes++;
                if (vetor[j] < vetor[min]) {
                    min = j;
                }
            }
    
            if (min != i) {
                int x = vetor[i];
                vetor[i] = vetor[min];
                vetor[min] = x;
                trocas++;
            }
        }
    }
    
    public void insertionSort() {
        comparacoes = 0;
        trocas = 0;
        for (int j = 1; j < ocupacao; ++j) {
            int x = vetor[j];
            int i;
            for (i = j - 1; i >= 0; --i) {
                comparacoes++;
                if (vetor[i] > x) {
                    vetor[i + 1] = vetor[i];
                    trocas++;
                } else {
                    break;
                }
            }
            vetor[i + 1] = x;
        }
    }

    
    @Override
    public String toString() {
        String s = "ocupacao = " + ocupacao + "\n";
        for (int i=0; i < ocupacao; i++) {
            s += vetor[i] + " ";
        }
        return s + "\n";
    }
}	
class VetorVazioException extends RuntimeException {
    public VetorVazioException(String msg) {
        super(msg);
    }
}
class ElementoNaoEncontradoException extends RuntimeException {
    public ElementoNaoEncontradoException(String msg) {
        super(msg);
    }
}