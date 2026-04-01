package gerenciador.financeiro.db;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoDB {

    private final JdbcTemplate jdbcTemplate;
    private final BasicDataSource basicDataSource;

    public ConexaoDB() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:h2:mem:db_financeiro");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");

        this.basicDataSource = basicDataSource;
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}