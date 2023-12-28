package Model;

public class Usuario implements Autenticavel{

    //private static long codigo = 0;
    private long id;
    private String nome;
    private String nomeLogin;
    private String senha;
    private Tipo tipo;

    private Usuario(String nome, String nomeLogin, String senha, Tipo tipo, boolean ehCopia) {
        this.nome = nome;
        this.nomeLogin = nomeLogin;
        this.senha = senha;
        this.tipo = tipo;
    }

     // construtor de copia
     public Usuario(Usuario u) {
        this(u.nome, u.nomeLogin, u.senha, Tipo.valueOf(u.getTipo().toString()), true);
        long id = u.getId();
        this.setId(id);
    }


    public static Usuario getInstance(String nome, String nomeLogin, String senha, Tipo tipo) {
        if (nome != null && nomeLogin != null && senha != null && tipo != null) {
            return new Usuario(nome, nomeLogin, senha, tipo, false);
        }
        return null;
    }

    public String getNomeLogin() {
        return this.nomeLogin;
    }

    public String getNome() {
        return this.nome;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public boolean autenticaSenha(String senha) {
        if (this.senha.equals(senha)) {
            return true;
        }
        return false;
    }

    @Override
	public boolean equals(Object obj) {
		// instanceof operador que testa o tipo da classe do objeto
		if (obj instanceof Usuario) {
			Usuario u = (Usuario) obj; // casting de Object para Usuario
			if (id == u.id)
				return true;
			else
				return false;
		} else
			return false;
	}
   
    public boolean autentica(String nomeLogin, String senha) {
        if (this.senha.equals(senha) && this.nomeLogin.equals(nomeLogin)) {
            return true;
        }
        return false;
    }

}
