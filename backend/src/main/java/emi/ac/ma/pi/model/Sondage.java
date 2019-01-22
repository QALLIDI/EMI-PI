package emi.ac.ma.pi.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Sondage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String titre;
	@NotNull
	private String sondage;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Domaine domaine;
	private Instant timeToStart;
	@NotNull
	private Long timeByHours;
	private Instant timeToFinish;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Proposition> propositions = new TreeSet<Proposition>();

	public Sondage() {
		super();
	}

	public Sondage(String titre, String sondage, Domaine domaine, Long timeByHours) {
		this.titre = titre;
		this.sondage = sondage;
		this.domaine = domaine;
		this.setTimeByHours(timeByHours);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getSondage() {
		return sondage;
	}

	public void setSondage(String sondage) {
		this.sondage = sondage;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public Instant getTimeToStart() {
		return timeToStart;
	}

	public void setTimeToStart(Instant timeToStart) {
		this.timeToStart = timeToStart;
	}

	public @NotNull Long getTimeByHours() {
		return timeByHours;
	}

	public void setTimeByHours(@NotNull Long timeByHours) {
		this.timeByHours = timeByHours;
		this.timeToStart = Instant.now();
		this.timeToFinish = this.timeToStart.plus(timeByHours, ChronoUnit.HOURS);
	}

	public Instant getTimeToFinish() {
		return timeToFinish;
	}

	public void setTimeToFinish(Instant timeToFinish) {
		this.timeToFinish = timeToFinish;
	}

	public Set<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(Set<Proposition> propositions) {
		this.propositions = propositions;
	}

	public boolean add(Proposition e) {
		return propositions.add(e);
	}

	public boolean remove(Object o) {
		return propositions.remove(o);
	}

	public int size() {
		return propositions.size();
	}
}
