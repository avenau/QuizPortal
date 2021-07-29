package com.fdm.qualifier.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "trainee")
public class Trainee extends User {
	private LocalDate completionDate;

	@ManyToOne
	@JoinColumn(name = "FK_STREAM")
	private Stream stream;

	@ManyToMany
	private List<Placement> placements;

	@ManyToMany
	private List<Placement> appliedPlacements;
	
	@OneToMany
	private List<Result> results;

	@ManyToMany
//	@JoinColumn(name = "FK_SKILL_LEVEL")
	private List<SkillLevel> skills;

	@ManyToMany
//	@JoinColumn(name = "FK_SKILL_LEVEL")
	private List<SkillLevel> pinnedSkills;

	public Trainee() {
		super();
	}

	public Trainee(String username, String password, LocalDate date, Stream stream) {
		super(username, password);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(String username, String password, boolean isActive, LocalDate date, Stream stream) {
		super(username, password, isActive);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(String username, String password, boolean isActive, String userType, LocalDate date, Stream stream) {
		super(username, password, isActive, userType);
		this.completionDate = date;
		this.stream = stream;
	}

	public Trainee(LocalDate completionDate, Stream stream, List<Placement> placements, List<Result> results,
			List<SkillLevel> skills, List<SkillLevel> pinnedSkills) {
		super();
		this.completionDate = completionDate;
		this.stream = stream;
		this.placements = placements;
		this.results = results;
		this.skills = skills;
		this.pinnedSkills = pinnedSkills;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public Stream getStream() {
		return stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}

	public List<Placement> getPlacements() {
		return placements;
	}

	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public List<SkillLevel> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillLevel> skills) {
		this.skills = skills;
	}

	public List<SkillLevel> getPinnedSkills() {
		return pinnedSkills;
	}

	public void setPinnedSkills(List<SkillLevel> pinnedSkills) {
		this.pinnedSkills = pinnedSkills;
	}

	@Override
	public String toString() {
		return "Trainee [completionDate=" + completionDate + ", stream=" + stream + ", placements=" + placements
				+ ", results=" + results + ", skills=" + skills + ", pinnedSkills=" + pinnedSkills + "]";
	}
}
