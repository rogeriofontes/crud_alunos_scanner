package org.crud_alunos;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static ArrayList<Aluno> listadeAlunos = new ArrayList<Aluno>();

    public static void main(String[] args) throws IOException {
        Menu();
    }

    public static void Menu() throws IOException {
        tela();

        Integer opcao = 0;
        do {
            System.out.println("Digite a opção:");
            opcao = opcao();

            if (opcao.equals(1)) {
                cadastroAluno();
            } else if (opcao.equals(2)) {
                listaAluno();
            } else if (opcao.equals(3)) {
                alteraAluno();
            } else if (opcao.equals(4)) {
                apagarAluno();
            } else if (opcao.equals(5)) {
                deletarAlunos();
            } else if (opcao.equals(6)) {
                recuperarAluno();
            }
        } while (!opcao.equals("7"));
    }

    private static void cadastroAluno() {
        String nome = entrada("Nome: ");

        Aluno aluno = new Aluno(nome);
        listadeAlunos.add(aluno);
    }

    private static void listaAluno() throws IOException {
        if (listadeAlunos.isEmpty()) {
            saidaDados("Nenhum aluno cadastrado");
            return;
        }
        FileWriter arq = new FileWriter("lista_aluno.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        String relatorio = "";

        gravarArq.printf("------- LISTA DE ALUNOS ------\r\n");

        for (int i = 0; i < listadeAlunos.size(); i++) {
            Aluno Lista = listadeAlunos.get(i);
            gravarArq.printf("\nNome = " + Lista.getNome());
            relatorio += "\nNome: \n" + Lista.getNome() + "\n------------------------------------\n";
        }
        gravarArq.printf("\n------- FIM ------\r\n");
        gravarArq.close();

        System.out.printf(relatorio);
    }

    private static void alteraAluno() {

        if (listadeAlunos.size() == 0) {
            System.out.printf("Nenhum aluno cadastrado");
            return;
        }
        String pesquisar = entrada("Digite o nome do aluno que deseja alterar: ");

        for (int i = 0; i < listadeAlunos.size(); i++) {
            Aluno alterarNome = listadeAlunos.get(i);
            if (pesquisar.equalsIgnoreCase(alterarNome.getNome())) {
                String nomeNovo = entrada("Digite o novo nome do aluno: ");

                alterarNome.setNome(nomeNovo);
                break;
            }
        }
        saidaDados("Nome modificado com sucesso");
    }

    private static void apagarAluno() {
        if (listadeAlunos.size() == 0) {
            System.out.printf("Nenhum aluno cadastrado");
            return;
        }
        String pesquisarNome = entrada("Digite o nome do aluno que deseja deletar: ");

        for (int i = 0; i < listadeAlunos.size(); i++) {
            Aluno alunoProcurado = listadeAlunos.get(i);
            if (pesquisarNome.equalsIgnoreCase(alunoProcurado.getNome())) {
                listadeAlunos.remove(i);
                saidaDados("Aluno deletado com sucesso");
            }
        }

    }

    private static void deletarAlunos() {
        if (listadeAlunos.isEmpty()) {
            saidaDados("Nenhum aluno cadastrado");
            return;
        }
        listadeAlunos.clear();
        saidaDados("Todos os alunos deletados com sucesso");
    }

    private static void recuperarAluno() {
        String exibi = "";
        String nomeArq = "lista_aluno.txt";

        String linha = "";
        File arq = new File(nomeArq);

        if (arq.exists()) {
            exibi = "RELATORIO\n";

            try {
                exibi += "";
                //abrindo arquivo para leitura
                FileReader abrindo = new FileReader(nomeArq);
                //leitor do arquivo
                BufferedReader leitor = new BufferedReader(abrindo);
                while (true) {
                    linha = leitor.readLine();
                    if (linha == null)
                        break;
                    exibi += linha + "\n";
                }
                leitor.close();
            } catch (Exception erro) {
            }
            saidaDadosErro("\nLISTA DE ALUNOS" + "---\n");
            //JOptionPane.showMessageDialog(null, exibi, "\nLISTA DE ALUNOS" + "---\n", 1);
        }
        //Se nao exixtir
        else {
            saidaDadosErro("Arquivo nao " + "existe");
            //JOptionPane.showMessageDialog(null, "Arquivo nao " + "existe", "Erro", 0);
        }
    }

    private static Integer opcao() {
        Scanner in = new Scanner(System.in);

        try {
            int opcao = in.nextInt();

            if (opcao > 0 && opcao <= 7) {
                return opcao;
            } else {
                System.out.println("Selecione uma opção valida.");
                tela();
            }
        } catch (InputMismatchException e) {
            System.err.println("Entre com um numero inteiro:");
            System.err.println("As opções válidas são:");
            tela();
        }

        //return JOptionPane.showInputDialog(entrar);
        return 7;
    }

    private static void tela() {
        System.out.println("Selecione uma opção:");
        String tela = "\t1)Cadastrar aluno" + "\n\t2)Listar alunos" + "\n\t3)Alterar aluno" + "\n\t4)Apagar aluno" + "\n\t5)Deletar todos" + "\n\t6)Recupera informacao" + "\n\t7)Sair";
        System.out.println(tela);
    }

    private static String entrada(String entrar) {
        System.out.println(entrar);
        Scanner in = new Scanner(System.in);
        return in.next();
        //return JOptionPane.showInputDialog(entrar);
    }

    private static void saidaDados(String saida) {
        System.out.println(saida);
        //JOptionPane.showMessageDialog(null, saida);
    }

    private static void saidaDadosErro(String saida) {
        System.err.println(saida);
        //JOptionPane.showMessageDialog(null, saida);
    }


}