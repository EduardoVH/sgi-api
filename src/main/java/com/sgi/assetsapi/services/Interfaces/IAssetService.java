package com.sgi.assetsapi.services.Interfaces;

import com.sgi.assetsapi.Controllers.dtos.requests.CreateAssetRequest;
import com.sgi.assetsapi.Controllers.dtos.responses.BaseResponse;

public interface IAssetService {
    BaseResponse get(Long id);

    BaseResponse create(CreateAssetRequest request);

    void delete(Long id);
}
