package acme.entities.toolkit;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.quantity.Quantity;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	//Serialisation identifier     ---------------------------------------------
	
	protected static final long	serialVersionUID= 1L;
	
	// Attributes
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="(\\w{3})-(\\d{3})")
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String descripcion;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String assemblyNotes;
	
	@URL
	protected String link;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Inventor inventor;
	
	@Valid
	@OneToMany(mappedBy="toolkit", fetch = FetchType.EAGER)
	protected List<Quantity> quantity;
	
}