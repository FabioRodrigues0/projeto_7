---
tags:
  - contexto/PC
  - tipo/trabalho_pratico
  - conceito/java
  - area/programacao
data: 21-04-2026
disciplina:
  - PC
---
# projeto_7

## Descrição

projeto_7

## Conceitos abordados

- [[variavel|Variáveis]] e [[tipo_primitivo|tipos de dados]]
- 

## Estrutura do Projeto

- `src/main/java/App.java` - Classe principal
- `build.gradle` - Configuração Gradle
- `config/checkstyle/sun_checks.xml` - Regras Checkstyle (Sun/Oracle)
- `config/formatter/eclipse-formatter.xml` - Formatação automática

## Como executar

```bash
# Compilar
./gradlew build

# Executar
./gradlew run

# Formatar código automaticamente
./gradlew spotlessApply

# Verificar estilo (Checkstyle)
./gradlew checkstyleMain

# Limpar build
./gradlew clean
```

## Dependências

- **Java 25** - Versão do JDK
- **Checkstyle 13.3.0** - Verificação de estilo (Sun/Oracle conventions)
- **Spotless 8.3.0** - Formatação automática de código

## Relações

- [[variavel]]
- [[tipo_primitivo]]
