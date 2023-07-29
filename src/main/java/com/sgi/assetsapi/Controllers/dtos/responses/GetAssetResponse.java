package com.sgi.assetsapi.Controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAssetResponse {
    private Long id;

    private String name;

    private String url;

    private String responsableEmail;
}
