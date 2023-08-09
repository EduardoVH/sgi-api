package com.sgi.assetsapi.Controllers;

import com.sgi.assetsapi.Controllers.dtos.requests.CreateAssetRequest;
import com.sgi.assetsapi.Controllers.dtos.responses.BaseResponse;
import com.sgi.assetsapi.services.AssetServiceImpl;
import com.sgi.assetsapi.services.Interfaces.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/asset")
@CrossOrigin("*")
public class AssetController {

    @Autowired
    private IAssetService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateAssetRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAsset(@RequestParam("file") MultipartFile file) {
        // Lógica para guardar el archivo en Google Drive u otro sistema de almacenamiento
        // Puedes usar librerías como Google Drive API o Google Cloud Storage
        // Retorna una respuesta adecuada (éxito o error)
        return null;
    }

    @GetMapping("/view")
    public ResponseEntity<byte[]> viewPDF(@RequestParam("url") String pdfUrl) {
        try {
            URL url = new URL(pdfUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            byte[] pdfBytes = inputStream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "file.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private final AssetServiceImpl assetService;

    @Autowired
    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/list")
    public List<String> listAssets() {
        return assetService.getListOfStoredAssets();
    }

}
