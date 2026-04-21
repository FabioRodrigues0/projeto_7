package models;

import javafx.collections.ObservableList;

public class Aluno extends Pessoa {

    private ObservableList<NotaDisciplina> notas;
    private double media;

    public ObservableList<NotaDisciplina> getNotas() {
        return notas;
    }

    /**
     * Adiciona ou atualiza uma nota. Se já existir uma nota para a disciplina,
     * atualiza-a; caso contrário, adiciona uma nova. Recalcula a média após a
     * alteração.
     *
     * @param nota
     *            NotaDisciplina a adicionar ou atualizar.
     */
    public void setNotas(NotaDisciplina nota) {
        NotaDisciplina existente = this.notas.stream()
                .filter(n -> n.getNomeDisciplina().equals(nota.getNomeDisciplina())).findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setNota(nota.getNota());
        } else {
            this.notas.add(nota);
        }

        this.media = calcularMedia();
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    /**
     * Calcula a média aritmética de todas as notas do aluno.
     *
     * @return Média das notas, ou 0 se não existirem notas.
     */
    private double calcularMedia() {
        double soma = 0;
        for (NotaDisciplina nota : notas) {
            soma += nota.getNota();
        }
        return this.notas.isEmpty() ? 0 : soma / notas.size();
    }

    @Override
    public String falar() {
        String _fala = super.falar();
        return _fala + " estou inscrito em";
    }
}
