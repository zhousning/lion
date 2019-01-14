package app.models;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private String name;
	private String title;
	private List<String> attrTypes = new ArrayList<String>();
	private List<String> attrNames = new ArrayList<String>();
	private List<String> labels = new ArrayList<String>();
	private List<String> constraints = new ArrayList<String>();
	private List<String> associateTypes = new ArrayList<String>();
	private List<String> associateObjects = new ArrayList<String>();
	private List<String> widgets = new ArrayList<String>();
	private List<String> attrWidgets = new ArrayList<String>();
	private List<String> pluginTypes = new ArrayList<String>();
	private List<String> pluginConditions = new ArrayList<String>();
	
	public Model() {
		super();
	}
	
	public Model(String name, String title) {
		super();
		this.name = name;
		this.title = title;
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

	
	public List<String> getAttrWidgets() {
		return attrWidgets;
	}

	public void setAttrWidgets(List<String> attrWidgets) {
		this.attrWidgets = attrWidgets;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getWidgets() {
		return widgets;
	}

	public void setWidgets(List<String> widgets) {
		this.widgets = widgets;
	}

	public List<String> getPluginTypes() {
		return pluginTypes;
	}

	public void setPluginTypes(List<String> pluginTypes) {
		this.pluginTypes = pluginTypes;
	}

	public List<String> getPluginConditions() {
		return pluginConditions;
	}

	public void setPluginConditions(List<String> pluginConditions) {
		this.pluginConditions = pluginConditions;
	}
	
	
	

	
}
