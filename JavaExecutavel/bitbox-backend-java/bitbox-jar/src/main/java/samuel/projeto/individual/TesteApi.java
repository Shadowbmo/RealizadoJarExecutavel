package samuel.projeto.individual;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.awt.GraphicsEnvironment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import livia.prototipo.bitbox.Conexao;
import livia.prototipo.bitbox.ConexaoDocker;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TesteApi {

    public TesteApi() {
    }

    private static void AtualizaDados(String login, String senha) {
        Conexao conexao = new Conexao();
        ConexaoDocker conexao2 = new ConexaoDocker();
        JdbcTemplate con = conexao.getConnection()
                ;
        JdbcTemplate con2 = conexao2.getConnection();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Looca looca = new Looca();
                Sistema sistema = new Sistema();
                Memoria memoria = new Memoria();
                Processador processador = new Processador();

//              con.update("insert into Registro(cpu_uso, ram_uso, ram_disponivel, disco_capacidade_disponivel) values (?, ?, ?, GETDATE(), ?,? )", processador.getUso(), memoria.getEmUso(), temperatura.getTemperatura(), numeroDeIdentificacaoDoTotem, login);
                con2.update("insert into  Registro(cpu_uso, ram_uso, ram_disponivel,  momento, fkMaquina, fkMaquinaEmpresa)  values (?, ?, ?, now(), 1, 1)", processador.getUso(), memoria.getEmUso(), memoria.getDisponivel());
                System.out.println("Dados inseridos no banco...");

                System.out.println("Sistema");
                System.out.println("-------------------------------------------------------------------");

                System.out.println(sistema);
                System.out.println("-------------------------------------------------------------------");

                System.out.println("Memoria Em uso");
                System.out.println("-------------------------------------------------------------------");
                System.out.println(memoria.getEmUso());
                System.out.println("-------------------------------------------------------------------");

                System.out.println("Memoria Disponivel");
                System.out.println("-------------------------------------------------------------------");
                System.out.println(memoria.getDisponivel());
                System.out.println("-------------------------------------------------------------------");
                
                System.out.println("Processador");
                System.out.println("-------------------------------------------------------------------");
                System.out.println(processador.getUso());
                System.out.println("-------------------------------------------------------------------");

            }
        }, 0, 30000);
    }

    public static void main(String login, String senha) {
        if (GraphicsEnvironment.isHeadless()) {
            AtualizaDados(login, senha);
        } else {
            new Runnable() {
                public void run() {
                    new TesteApi().setVisible(true);
                }
            };
            AtualizaDados(login, senha);
        }
    }

    private void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
}
