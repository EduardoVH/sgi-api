package com.sgi.assetsapi.services;

import com.sgi.assetsapi.Controllers.dtos.requests.CreateAssetRequest;
import com.sgi.assetsapi.Controllers.dtos.responses.BaseResponse;
import com.sgi.assetsapi.Controllers.dtos.responses.GetAssetResponse;
import com.sgi.assetsapi.entities.Asset;
import com.sgi.assetsapi.repositories.IAssetRepository;
import com.sgi.assetsapi.services.Interfaces.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements IAssetService {

    @Autowired
    private IAssetRepository repository;

    @Override
    public BaseResponse get(Long id) {
        GetAssetResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("asset by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateAssetRequest request) {
       Asset asset = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(asset)))
                .message("asset created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    private Asset from(CreateAssetRequest request){
        Asset asset = new Asset();
        asset.setName(request.getName());
        asset.setUrl(request.getUrl());
        asset.setResponsableEmail(request.getResponsableEmail());
        return asset;
    }

    private GetAssetResponse from(Asset asset){
        GetAssetResponse response = new GetAssetResponse();
        response.setId(asset.getId());
        response.setName(asset.getName());
        response.setUrl(asset.getUrl());
        response.setResponsableEmail(asset.getResponsableEmail());
        return response;
    }
    private GetAssetResponse from(Long id){
        Asset asset =  repository.findById(id).orElseThrow(()->new RuntimeException("Asset do not exist"));
        return from(asset);
    }

    public List<String> getListOfStoredAssets() {
        // Lógica para obtener la lista de activos almacenados desde Google Drive u otro sistema de almacenamiento
        // Retorna la lista de nombres de activos
        return null;
    }
}
