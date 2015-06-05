package org.brunocunha.dbparser.vo;

public class Field {

    private String name;
    private String type;
    private String description;
    private String format;
    private String initial;
    private String label;
    private int position;
    private int maxWidth;
    private String columnLabel;
    private String help;
    private int order;
    private boolean mandatory;
    private String validation;
    private String validationMessage;
    private boolean extent;
    private int extentValue;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        if (description == null) {
            description = "";
        }

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getInitial() {
        if (initial == null) {
            initial = "";
        }

        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public boolean isExtent() {
		return extent;
	}

	public void setExtent(boolean extent) {
		this.extent = extent;
	}

	public int getExtentValue() {
		return extentValue;
	}

	public void setExtentValue(int extentValue) {
		this.extentValue = extentValue;
	}


	
    
}
