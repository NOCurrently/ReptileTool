package com.xc.sample;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class CommonBean {

    private String urlPrefix;
    @Valid
    List<PaChongBean> dataConfig;
    @Valid
    List<PaChongBean> subAddress;

}
