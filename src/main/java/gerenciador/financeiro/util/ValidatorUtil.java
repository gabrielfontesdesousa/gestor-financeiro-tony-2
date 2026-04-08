package gerenciador.financeiro.util;

import gerenciador.financeiro.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidatorUtil {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validar(T obj) {
        Set<ConstraintViolation<T>> erros = validator.validate(obj);
        if (!erros.isEmpty()) {
            StringBuilder mensagem = new StringBuilder("Erros de validação:\n");
            for (ConstraintViolation<T> erro : erros) {
                mensagem.append(erro.getMessage())
                        .append("\n");
            }
            throw new ValidationException(mensagem.toString());
        }
    }
}
