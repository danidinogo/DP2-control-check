package acme.entities.patronage;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.patronagereport.PatronageReport;
import acme.enums.Status;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	protected static final long serialVersionUID = 1L;
	
	@NotNull
	protected Status status;
	
	@Pattern(regexp = "(\\w{3})-(\\d{3})")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Size(min = 1, max = 255)
	protected String legalStuff;
	 
	@NotNull
	protected Money budget;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startsAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishesAt;
	
	@URL
	protected String link;
	
	@ManyToOne
	@Valid
	protected Patron patron;
	
	@ManyToOne
	@Valid
	protected Inventor inventor;
	
	@OneToMany
	protected List<PatronageReport> patronageReports;
	
}
