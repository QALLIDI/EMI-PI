package emi.ac.ma.pi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Domaine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String domaine;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Sondage> sondages = new TreeSet<Sondage>();

	public Domaine() {
		super();
	}

	public Domaine(@NotNull String domaine) {
		super();
		this.domaine = domaine;
	}

	public Domaine(Long id, @NotNull String domaine, Set<Sondage> sondages) {
		super();
		this.id = id;
		this.domaine = domaine;
		this.sondages = sondages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public Set<Sondage> getSondages() {
		return sondages;
	}

	public void setSondages(Set<Sondage> sondages) {
		this.sondages = sondages;
	}

	public boolean add(Sondage e) {
		return sondages.add(e);
	}

	public boolean remove(Object o) {
		return sondages.remove(o);
	}

	public int size() {
		return sondages.size();
	}

	public boolean addAll(Collection<? extends Sondage> arg0) {
		return sondages.addAll(arg0);
	}
	
}
