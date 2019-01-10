package app.models;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private String name;
	private List<String> attrTypes = new ArrayList<String>();
	private List<String> attrNames = new ArrayList<String>();
	private List<String> labels = new ArrayList<String>();
	private List<String> constraints = new ArrayList<String>();
	private List<String> associateTypes = new ArrayList<String>();
	private List<String> associateObjects = new ArrayList<String>();
	
	public Model() {
		super();
	}
	
	public Model(String name, List<String> attrTypes, List<String> attrNames, List<String> labels,
			List<String> constraints, List<String> associateTypes, List<String> associateObjects) {
		super();
		this.name = name;
		this.attrTypes = attrTypes;
		this.attrNames = attrNames;
		this.labels = labels;
		this.constraints = constraints;
		this.associateTypes = associateTypes;
		this.associateObjects = associateObjects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAttrTypes() {
		return attrTypes;
	}

	public void setAttrTypes(List<String> attrTypes) {
		this.attrTypes = attrTypes;
	}

	public List<String> getAttrNames() {
		return attrNames;
	}

	public void setAttrNames(List<String> attrNames) {
		this.attrNames = attrNames;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	public List<String> getAssociateTypes() {
		return associateTypes;
	}

	public void setAssociateTypes(List<String> associateTypes) {
		this.associateTypes = associateTypes;
	}

	public List<String> getAssociateObjects() {
		return associateObjects;
	}

	public void setAssociateObjects(List<String> associateObjects) {
		this.associateObjects = associateObjects;
	}
	

	
}