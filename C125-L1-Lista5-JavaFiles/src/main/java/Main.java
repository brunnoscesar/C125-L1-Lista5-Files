import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Funcionario[] funcionarios = new Funcionario[50];

        Path caminho = Paths.get("funcionarios.csv");
        Path caminhoArquivoGerado = Paths.get("func_filtrado.csv");

        List<String> conteudo;  //guarda o conteúdo do funcionarios.csv

        String[] str = new String[50];  //recebe os valores de cada linha do funcionarios.csv

        conteudo = Files.readAllLines(caminho);

        for (int i = 0; i < conteudo.size(); i++) {  //lendo linha por linha
            str[i] = conteudo.get(i);
            String vect[] = str[i].split(",");  //separando cada informação do funiconarios.csv

            funcionarios[i] = new Funcionario();  //cria um novo funcionario para cada vez que o for passar

            //atribuindo valor para cada funcionario
            funcionarios[i].setIdentificador(vect[0]);
            funcionarios[i].setFilhos(vect[3]);
            funcionarios[i].setSalario(vect[4]);

        }


        //escrevendo as informações filtradas de cada funcionario no novo arquivo func_filtrado.csv
        int i = 1;
        Files.writeString(caminhoArquivoGerado, "Identificador,Filhos\n");
        while (funcionarios[i] != null) {

            //convertendo a String Filhos de cada funcionario em int para fazer a comparação
            String numFilhosStr = funcionarios[i].getFilhos();
            int numFilhosInt = 0;
            numFilhosInt = Integer.parseInt(numFilhosStr);

            if (numFilhosInt > 0) {

                Files.writeString(caminhoArquivoGerado,
                        funcionarios[i].getIdentificador() + "," + funcionarios[i].getFilhos() + "\n",
                        StandardOpenOption.APPEND);
            }
            i++;

        }
    }
}

