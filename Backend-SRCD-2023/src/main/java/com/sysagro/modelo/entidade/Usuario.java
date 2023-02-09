package com.sysagro.modelo.entidade;

import com.sysagro.util.RegistroUtil;
import static com.sysagro.util.TextoUtil.converterMaiusculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;
import javax.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;

/**
 * @author Pedro
 */
@Audited
@Entity
@Table(name = "usuario")
public class Usuario extends AbstratoEntidade implements Serializable {

    private static final long serialVersionUID = 2741892478912L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = 0L;
    
    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;
    
    @Column(name = "is_ativo", nullable = false)
    private boolean isAtivo = true;
    
    @Column(name = "login", length = 20, unique = true, nullable = false)
    private String login;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha", length = 20, nullable = false)
    private String senha;

    @JoinColumn(name = "id_foto", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario_foto"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Anexo foto;

    // Mapped by
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioPerfil> perfisUsuario;

    // Transients
    @Transient
    private String confirmacaoSenhaTR;

    // Construtor
    public Usuario() {
    }

    public Usuario(boolean isAtivo, String login, String nome, String email, String senha, Anexo foto, String confirmacaoSenhaTR) {
        this.isAtivo = isAtivo;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.confirmacaoSenhaTR = confirmacaoSenhaTR;
    }
    
    // Listeners
    @PostLoad
    public void postLoad() {
        definirConfirmacaoSenhaTR();
    }
    
    @PrePersist
    public void prePersistEntidade() {
        converterAtributosMaiusculo();
    }
    
    @PreUpdate
    public void preUpdateEntidade() {
        converterAtributosMaiusculo();
    }

    // Geral
    public boolean isSenhaNaoConfirmada() {
        return nonNull(senha)
            && nonNull(confirmacaoSenhaTR)
            && !senha.equals(confirmacaoSenhaTR);
    }
    
    public String retornarAtivoCSS() {
        return isAtivo ? "circulo-verde" : "circulo-vermelho";
    }
    
    public String retornarUsuarioCompleto() {
        return "Login: " + login + " | Nome: " + nome;
    }
    
    public Usuario processarParaQuery() {
        return RegistroUtil.isNovo(id) ? null : this;
    }
    
    public void definirConfirmacaoSenhaTR() {
        confirmacaoSenhaTR = senha;
    }
    
    private void converterAtributosMaiusculo() {
        nome = converterMaiusculo(nome);
    }
    
    // Processamentos de listas
    public void limparPerfisUsuario() {
        perfisUsuario = new ArrayList<>();
    }

    // Getters && Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersao() {
        return versao;
    }

    private void setVersao(Integer versao) {
        this.versao = versao;
    }

    public boolean isIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Anexo getFoto() {
        return foto;
    }

    public void setFoto(Anexo foto) {
        this.foto = foto;
    }

    public List<UsuarioPerfil> getPerfisUsuario() {
        return perfisUsuario;
    }

    public void setPerfisUsuario(List<UsuarioPerfil> perfisUsuario) {
        this.perfisUsuario = perfisUsuario;
    }

    public String getConfirmacaoSenhaTR() {
        return confirmacaoSenhaTR;
    }

    public void setConfirmacaoSenhaTR(String confirmacaoSenhaTR) {
        this.confirmacaoSenhaTR = confirmacaoSenhaTR;
    }

    // Equals && Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
