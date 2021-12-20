package funcionariosCiss.funcionariosrestapi.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validarController {

    public boolean validarEmail(String email){
            Pattern p = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
            Matcher m = p.matcher(email);
            return m.matches();
    }
}
