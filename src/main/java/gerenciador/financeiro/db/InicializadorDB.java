package gerenciador.financeiro.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class InicializadorDB {

    public static void inicializar(ConexaoDB conexaoDB) {

        try
            ( InputStream inputStream = InicializadorDB.class
                    .getResourceAsStream("/schema.sql")){

            if (inputStream == null) {
                throw new RuntimeException("Arquivo schema.sql não encontrado!");
            }

            String sql = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));

            String[] comandos = sql.split(";");

            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    conexaoDB.getJdbcTemplate().execute(comando);
                }
            }

            System.out.println("Banco inicializado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}