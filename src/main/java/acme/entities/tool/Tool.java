package acme.entities.tool;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {
	//Serialisation identifier  ---------------------------------------------
	
	protected static final long	serialVersionUID= 1L;
	
	// Attributes
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String name;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String technology;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="(\\w{3})-(\\d{3})")
	protected String code;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String description;

	protected Money retailprice;
	
	@URL
	protected String link;
	
	//@OneToOne
	//@Valid
	//protected Toolkit toolkit;
	
}