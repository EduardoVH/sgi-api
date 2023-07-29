package com.sgi.assetsapi.Controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateAssetRequest {
    private String name;

    private String url;

    private String responsableEmail;
}
