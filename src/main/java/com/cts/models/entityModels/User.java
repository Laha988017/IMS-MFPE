package com.cts.models.entityModels;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;

	@Column(name = "email_id", unique = true, nullable = false)
	private String emailId;

	@Column(name = "user_name", unique = true, nullable = false, length = 100)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "is_admin")
	private boolean admin;

	@Column(name = "date_of_association", nullable = false)
	private Date dateOfAssociation;

	@Column(name = "is_active_user")
	private boolean activeUser;

	@Column(name = "last_login", nullable = true)
	private Date lastLogin;

	@OneToMany(targetEntity = Inward.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Inward> inward;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getDateOfAssociation() {
		return dateOfAssociation;
	}

	public void setDateOfAssociation(Date dateOfAssociation) {
		this.dateOfAssociation = dateOfAssociation;
	}

	public boolean isActiveUser() {
		return activeUser;
	}

	public void setActiveUser(boolean activeUser) {
		this.activeUser = activeUser;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@JsonManagedReference(value = "user-inward")
	public Set<Inward> getInward() {
		return inward;
	}

	public void setInward(Set<Inward> inward) {
		this.inward = inward;
	}
	
	public void addInward(Inward inward) {
		this.inward.add(inward);
	}

}
