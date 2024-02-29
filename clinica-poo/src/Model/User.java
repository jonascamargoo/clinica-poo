package Model;

import java.util.Optional;

import enums.Type;

public class User implements Authenticatable {

	// private static long codigo = 0;
	private long id;
	private String name;
	private String loginName;
	private String password;
	private Type type;

	private User(String name, String loginName, String password, Type type, boolean isCopy) {
		this.name = name;
		this.loginName = loginName;
		this.password = password;
		this.type = type;
	}

	// Copy constructor - esse padrao eh utilizado para criar uma nova instancia de uma classe com base em uma instancia existente. Ele eh util para criar copias imutaveis de objetos
	public User(User user) {
		this(user.name, user.loginName, user.password, Type.valueOf(user.getType().toString()), true);

		long id = user.getId();
		this.setId(id);
	}

	public static Optional<User> getInstance(String name, String loginName, String password, Type type) {
        return (name != null && loginName != null && password != null && type != null)
                ? Optional.of(new User(name, loginName, password, type, false))
                : Optional.empty();
    }

	public String getName() {
		return this.name;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Type getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof User) && (id == ((User) obj).id);
	}

	public boolean auth(String loginName, String password) {
		return this.password.equals(password) &&
				this.loginName.equals(loginName);
	}

}
