package cgr.cgfsdam.pustakalaya.model.users;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Transient;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "user_id")
	private long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido1;
	
	@Column(nullable = true)
	private String apellido2;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_tipo_documento")
	private TipoDocumento tipoDocumento;

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false, unique = true)
	private String username;
	
	@Transient
	private String password;
	
	private int active;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(name = "permisos", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;
	
	private String telefono;
	
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVigor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaVigor() {
		return fechaVigor;
	}

	public void setFechaVigor(Date fechaVigor) {
		this.fechaVigor = fechaVigor;
	}

	public Usuario() { }

	public Usuario(String nombre, String apellido1, String apellido2, TipoDocumento tipoDocumento, String documento,
			String username, String password, int active, Set<Role> roles, Direccion direccion, String telefono,
			String email, Date fechaAlta, Date fechaVigor) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.fechaVigor = fechaVigor;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", tipoDocumento=" + tipoDocumento + ", documento=" + documento + ", username=" + username
				+ ", password=" + password + ", active=" + active + ", roles=" + roles + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", email=" + email + ", fechaAlta=" + fechaAlta + ", fechaVigor="
				+ fechaVigor + "]";
	}
}
