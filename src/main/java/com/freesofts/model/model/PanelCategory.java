package com.freesofts.model.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PanelCategory implements Serializable {

    private String id;

    private String categoryId;

    private String panelId;

}
