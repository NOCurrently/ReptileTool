package com.xc.sample;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PaChongBean {
    private String urlPrefix;
    @NotBlank(message = "attribute不能为空!")
    private String attribute;
    @NotBlank(message = "xpath不能为空!")
    private String xpath;
    private String subXpath;
}
