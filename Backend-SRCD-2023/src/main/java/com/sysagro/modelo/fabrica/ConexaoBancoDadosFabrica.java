/*
 */
package com.sysagro.modelo.fabrica;

import com.sysagro.util.LogUtil;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

/**
 *
 * @author Pedro
 */
@ApplicationScoped
public class ConexaoBancoDadosFabrica {

    // Variáveis
    @PersistenceUnit(unitName = "sysagroPU")
    private EntityManagerFactory emf;

    // Chamado depois de um @Inject do "EntityManager"
    @Produces
    @RequestScoped
    public EntityManager criar() {
        return emf.createEntityManager();
    }

    // Chamado para fechar o @Inject do @EntityManager
    public void fechar(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

    // Chamado antes de fechar o servidor
    @PreDestroy
    public void fecharFabrica() {
        // Fecha a Fábrica de Conexão com Banco de Dados
        if (emf.isOpen()) {
            emf.close();
        }
    }
    
    // Criação de conexão com o BD
    public Connection criarConexao() {
        Connection conexao = null;
        EntityManager em = null;
        try {
            em = criar();
            conexao = em.unwrap(Session.class)
                .getSessionFactory()
                .getSessionFactoryOptions()
                .getServiceRegistry()
                .getService(ConnectionProvider.class)
                .getConnection();
        } catch (SQLException ex) {
            LogUtil.exibirErro(getClass(), ex);
        } finally {
            fechar(em);
        }
        return conexao;
    }
}