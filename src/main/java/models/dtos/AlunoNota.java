package models.dtos;

import models.enums.Gender;

// AlunoComNota.java — record DTO para a tabela
public record AlunoNota(
        int id,
        String nome,
        Gender genero,
        int idade,
        double media,      // média geral
        double nota        // nota nesta disciplina específica
) {}
