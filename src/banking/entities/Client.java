package banking.entities;

import banking.enums.ClientStatus;

public class Client {

	private String name;
	private String cpf;
	private String email;
	private ClientStatus status;
	
	//constructores
	public Client() {
	}
	
	public Client(String name, String cpf, String email, ClientStatus status) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.status = status = ClientStatus.ACTIVE;
	}
	
	//getters
	//aqui não serão necessários os setters
	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public ClientStatus getStatus() {
		return status;
	}
	
	//methods
	//settando o valor de status de acordo com os valores listados no enum ClientStatus
	public void activate() {
		status = ClientStatus.ACTIVE;
	}
	
	public void block() {
		status = ClientStatus.BLOCKED;
	}
	
}
