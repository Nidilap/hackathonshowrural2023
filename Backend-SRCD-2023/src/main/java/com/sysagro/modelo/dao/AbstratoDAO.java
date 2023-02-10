/*
 */
package com.sysagro.modelo.dao;

import com.sysagro.enumeracao.TipoOperacaoBDEnum;
import com.sysagro.util.LogUtil;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import static java.util.Objects.isNull;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

/**
 *
 * @author Pedro
 * @param <T>
 */
public abstract class AbstratoDAO<T> {

    // Variáveis
    @Inject
    protected EntityManager em;

    // Geral
    public T inicializarObjeto(Object objeto) {
        return (T) Hibernate.unproxy(objeto);
    }

    public T buscarPorId(Object id, Class<T> classe) {
        return (T) em.find(classe, id);
    }

    private Object retornarId(T obj) {
        return em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(obj);
    }

    // API
    public boolean operar(T obj, TipoOperacaoBDEnum tipoOperacaoBD) {
        if (isNull(tipoOperacaoBD)) {
            return false;
        } else {
            switch (tipoOperacaoBD) {
                case INS:
                case UPD:
                    return salvar(obj);
                case DEL:
                    return excluir(obj);
                default:
                    return false;
            }
        }
    }

    public boolean operarSemTransacao(T obj, TipoOperacaoBDEnum tipoOperacaoBD) {
        if (isNull(tipoOperacaoBD)) {
            return false;
        } else {
            switch (tipoOperacaoBD) {
                case INS:
                case UPD:
                    return salvarSemTransacao(obj);
                case DEL:
                    return excluirSemTransacao(obj);
                default:
                    return false;
            }
        }
    }

    @Transactional(value = TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvar(T obj) {
        try {
            salvarSemTransacao(obj);
            return true;
        } catch (Exception ex) {
            LogUtil.exibirErro(getClass(), ex);
            return false;
        }
    }

    @Transactional(value = Transactional.TxType.MANDATORY, rollbackOn = {Exception.class})
    public boolean salvarSemTransacao(T obj) {
        try {
            Object id = retornarId(obj);
            // Se o "id" ou o "objeto" for nulo, aí o registro é persistido (é novo)
            if (isNull(id) || isNull(em.find(obj.getClass(), id))) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean salvarLista(List<T> lista) {
        try {
            salvarListaSemTransacao(lista);
            return true;
        } catch (Exception ex) {
            LogUtil.exibirErro(getClass(), ex);
            return false;
        }
    }

    @Transactional(value = Transactional.TxType.MANDATORY, rollbackOn = {Exception.class})
    public boolean salvarListaSemTransacao(List<T> lista) {
        try {
            if (CollectionUtils.isNotEmpty(lista)) {
                lista.forEach(obj -> salvarSemTransacao(obj));
            }
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional(value = TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean excluir(T obj) {
        try {
            excluirSemTransacao(obj);
            return true;
        } catch (Exception ex) {
            LogUtil.exibirErro(getClass(), ex);
            return false;
        }
    }

    @Transactional(value = TxType.MANDATORY, rollbackOn = {Exception.class})
    public boolean excluirSemTransacao(T obj) {
        try {
            em.remove(em.contains(obj) ? obj : em.merge(obj));
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional(value = TxType.REQUIRED, rollbackOn = {Exception.class})
    public boolean excluirLista(List<T> lista) {
        try {
            excluirListaSemTransacao(lista);
            return true;
        } catch (Exception ex) {
            LogUtil.exibirErro(getClass(), ex);
            return false;
        }
    }

    @Transactional(value = TxType.MANDATORY, rollbackOn = {Exception.class})
    public boolean excluirListaSemTransacao(List<T> lista) {
        try {
            if (CollectionUtils.isNotEmpty(lista)) {
                lista.forEach(obj -> excluirSemTransacao(obj));
            }
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
