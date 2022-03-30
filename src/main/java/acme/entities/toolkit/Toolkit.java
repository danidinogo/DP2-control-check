package acme.entities.toolkit;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.component.Component;
import acme.entities.tool.Tool;
import acme.framework.entities.AbstractEntity;
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
	
	@OneToOne
	@Valid
	protected Tool tool;
	
	@OneToMany
	protected List<Component> component;
}