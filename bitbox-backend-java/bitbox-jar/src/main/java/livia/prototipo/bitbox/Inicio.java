/*
 * The MIT License
 *
 * Copyright 2023 dssilva21.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package livia.prototipo.bitbox;

import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author dssilva21
 */
public class Inicio {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            Conexao conexao = new Conexao();
            JdbcTemplate con = conexao.getConnection();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Informe seu Email");
            String email = scanner.nextLine();

            System.out.println("Informe sua senha");
            String senha = scanner.nextLine();

            List<Usuario> usuarios = con.query("select * from usuario where email = ? and senha = ?",
                    new UsuarioRowMapper(), email, senha);

            System.out.println("Autenticando. Aguarde...");

            System.out.println(usuarios);
            if (usuarios.size() > 0) {
                samuel.projeto.individual.TesteApi teste = new samuel.projeto.individual.TesteApi();
                teste.main(email, senha);
            } else {
                System.out.println("Acesso negado!");
            }
        } else {
            System.out.println("Possui ambiente grafico");
            Login tela = new Login();
            tela.main(args);
        }
    }

}
