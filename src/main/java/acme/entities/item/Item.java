package acme.entities.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{
	
	@NotBlank
	@Length(max = 100)
	protected String name;
	
	@Column(unique=true)
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(max = 100)
	protected String technology;
	
	@NotBlank
	@Length(max = 255)
	protected String description;
	
	@NotNull
	protected Money retailPrice;
	
	protected String info;
	
	@NotNull
	protected Status status;
	
	@NotNull
	protected ItemType type;
	
	@ManyToOne
	@Valid
	protected Inventor inventor;
	

}
